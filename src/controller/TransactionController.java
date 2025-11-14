package controller;

import model.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionController {
    private TransactionDAO dao;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TransactionController() {
        dao = new TransactionDAO();
    }

    public boolean save(String jenis, double jumlah, String detail) {
        String now = LocalDateTime.now().format(FORMAT);
        Transaction t = new Transaction(jenis, jumlah, detail, now);
        return dao.insert(t);
    }

    public boolean update(Integer id, String jenis, double jumlah, String detail) {
        String now = LocalDateTime.now().format(FORMAT); // update waktu saat edit
        Transaction t = new Transaction(id, jenis, jumlah, detail, now);
        return dao.update(t);
    }

    public boolean delete(Integer id) {
        if (id == null) return false;
        return dao.delete(id);
    }

    public List<Transaction> listAll() {
        return dao.getAll();
    }

    // helper: parse jumlah safely
    public double parseJumlah(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            return 0d;
        }
    }
}
