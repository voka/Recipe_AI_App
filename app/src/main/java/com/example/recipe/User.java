package com.example.recipe;

public class User {
    public int id;
    public String food;
    public String link;
    public String blog_url;

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setFood(String food){
        this.food = food;
    }
    public String getFood(){
        return this.food;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }
    public void setBlog_url(String blog_url){
        this.blog_url = blog_url;
    }
    public String getBlog_url() { return this.blog_url;}

    @Override
    public String toString(){
        return "Data  id = " +id + " food = "+food+"  Link = "+link +"\n";
    }
}
