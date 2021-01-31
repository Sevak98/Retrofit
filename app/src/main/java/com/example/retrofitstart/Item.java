package com.example.retrofitstart;

public class Item {

    int id;
    String title;
    int albumId;
    String url;

    public  Item (){

    }

    public Item(int id, String title, int albumId, String url) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.url = url;
    }
}
