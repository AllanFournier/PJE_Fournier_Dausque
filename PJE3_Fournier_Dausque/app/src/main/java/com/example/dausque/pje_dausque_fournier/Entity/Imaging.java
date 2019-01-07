package com.example.dausque.pje_dausque_fournier.Entity;

public class Imaging {
    String Uri, fileName;

    public Imaging(String uri, String fileName) {
        Uri = uri;
        this.fileName = fileName;
    }

    public String getUri() {
        return Uri;
    }

    public String getFileName() {
        return fileName;
    }
}
