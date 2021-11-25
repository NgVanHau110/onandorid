package tin.ngolamquang.onandroid;

import java.io.Serializable;
import java.util.Date;

public class NhanVien implements Serializable {
    private int id;
    private String tenNV;
    private boolean phai;
    private Date date;

    public NhanVien(int id, String tenNV, boolean phai, Date date) {
        this.id = id;
        this.tenNV = tenNV;
        this.phai = phai;
        this.date = date;
    }

    public NhanVien() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public boolean isPhai() {
        return phai;
    }

    public void setPhai(boolean phai) {
        this.phai = phai;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String gioiTinh = this.phai ? "Nam": "Nữ";

        return this.id + " - " + this.tenNV + " - " + gioiTinh;
    }
}
