package com.example.myapplication;

public class DataKas {
    private String id_kas;
    private String nominal;
    private String tipe_kas;
    private String keterangan;
    private String tanggal;

    public DataKas(String id_kas, String tipe_kas, String nominal, String keterangan, String tanggal) {
        this.id_kas = id_kas;
        this.tipe_kas = tipe_kas;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public DataKas(String tipe_kas, String nominal, String keterangan, String tanggal) {
        this.tipe_kas = tipe_kas;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public String getId_kas() {
        return id_kas;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getTipe_kas() {
        return tipe_kas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setId_kas(String id_kas) {
        this.id_kas = id_kas;
    }

    public void setTipe_kas(String tipe_kas) {
        this.tipe_kas = tipe_kas;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
