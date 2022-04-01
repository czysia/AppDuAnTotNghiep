package org.sonnnph12414.appduantotnghiep.model;

public class GioHang {
    int idsp;
    String tensp;
    long giasp;
    String hinhsp;
    int soluong;

    public GioHang() {
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp(long gia) {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluong(int soluong) {
        return this.soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
