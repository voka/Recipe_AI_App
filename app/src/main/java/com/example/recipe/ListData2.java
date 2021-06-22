package com.example.recipe;

public class ListData2 {
    private int imageView;
    private String textView;
    private String textView2;

    public ListData2(int imageView, String textView, String textView2){
        this.imageView = imageView;
        this.textView = textView;
        this.textView2 = textView2;
    }

    public int getImageView()
    {
        return this.imageView;
    }

    public String getTextView()
    {
        return this.textView;
    }

    public String getTextView2()
    {
        return this.textView2;
    }
}
