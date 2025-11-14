package model;

public class Transaction {
    private Integer id;
    private String jenis;
    private double jumlah;
    private String detail;
    private String waktu; // store as ISO string, e.g. 2025-11-14T16:45:00

    public Transaction() {}

    public Transaction(Integer id, String jenis, double jumlah, String detail, String waktu) {
        this.id = id;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.detail = detail;
        this.waktu = waktu;
    }

    public Transaction(String jenis, double jumlah, String detail, String waktu) {
        this(null, jenis, jumlah, detail, waktu);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }
}
