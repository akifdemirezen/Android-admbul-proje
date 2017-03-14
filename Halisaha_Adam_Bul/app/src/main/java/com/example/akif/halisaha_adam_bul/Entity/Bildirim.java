package com.example.akif.halisaha_adam_bul.Entity;

/**
 * Created by akif on 8.3.2017.
 */

public class Bildirim {
    private String kimden;
    private String tarih;
    private String saat;
    private String il_ilce;
    private String bildirim_aciklama;

    public String getKimden() {
        return kimden;
    }

    public void setKimden(String kimden) {
        this.kimden = kimden;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getIl_ilce() {
        return il_ilce;
    }

    public void setIl_ilce(String il_ilce) {
        this.il_ilce = il_ilce;
    }

    public String getBildirim_aciklama() {
        return bildirim_aciklama;
    }

    public void setBildirim_aciklama(String bildirim_aciklama) {
        this.bildirim_aciklama = bildirim_aciklama;
    }

    public Bildirim(String kimden, String bildirim_aciklama, String il_ilce, String saat, String tarih) {
        this.kimden = kimden;
        this.bildirim_aciklama = bildirim_aciklama;
        this.il_ilce = il_ilce;
        this.saat = saat;
        this.tarih = tarih;
    }

}
