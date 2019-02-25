package com.example.irfanfahmiwijaya.humassmartclickapp.Model;

import java.io.Serializable;

public class GalleryFoto implements Serializable {
    public String nama_foto;
    public String nama_file;

    public GalleryFoto(String nama_foto, String nama_file) {
        this.nama_foto = nama_foto;
        this.nama_file = nama_file;
    }

    public String getNama_foto() {
        return nama_foto;
    }

    public void setNama_foto(String nama_foto) {
        this.nama_foto = nama_foto;
    }

    public String getNama_file() {
        return nama_file;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }


    public GalleryFoto() {
    }

}
