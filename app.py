import io
import json
import numpy as np
from PIL import Image
import tensorflow as tf
from keras.preprocessing import image
from flask import Flask, request, redirect, flash
from werkzeug.serving import WSGIRequestHandler
from tensorflow.keras.metrics import top_k_categorical_accuracy
app = Flask(__name__)
model = None


def top_5_accuracy(true, pred):
    return top_k_categorical_accuracy(true, pred, k=5)


def load_model():
    model_Path = "./DenseNet_fe-0001-1.42.h5"
    global model
    model = tf.keras.models.load_model(
        model_Path, custom_objects={'top_5_accuracy': top_5_accuracy})


def prepare_image(img, target):
    if img.mode != "RGB":
        img = image.convert("RGB")
    img = img.resize(target)  # target size로 바꾸기
    img = image.img_to_array(img)  # img -> array
    img = np.expand_dims(img, axis=0)  # 1차원 추가
    img /= 255
    return img


@app.route('/', methods=['GET', 'POST'])
def predict():
    cls_pred_name = []
    if request.method == "POST":
        print(request.files)
        if 'files' not in request.files:
            flash('No image part')
            return redirect(request.url)
        if request.files.get('files'):
            img = request.files['files'].read()  # 이미지 받기
            img = io.BytesIO(img)
            img = Image.open(img)
            # 이미지 전처리
            img = prepare_image(img, target=(224, 224))
            result = model.predict(img)  # 예측
            cls_pred_name = result.argsort(axis=1)[0][-5:][::-1]  # 상위 5개 index
            cls_pred_name = cls_pred_name.tolist()
            M = dict(zip(range(1, len(cls_pred_name) + 1), cls_pred_name))
            M = json.dumps(M)
            print(M)
            return M


host_addr = "0.0.0.0"

port_num = "5000"

if __name__ == "__main__":
    WSGIRequestHandler.protocol_version = "HTTP/1.1"
    print(("* Loading Keras model and Flask staring server...peases wait"))
    load_model()
    app.run(host=host_addr, port=port_num, debug=True)

