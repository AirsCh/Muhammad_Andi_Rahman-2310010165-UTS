package controller;

import database.DatabaseHelper;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public TransactionDAO() {
        DatabaseHelper.initDatabase();
    }

    public boolean insert(Transaction t) {
        String sql = "INSERT INTO transaksi (jenis, jumlah, detail, waktu) VALUES (?, ?, ?, ?)";
        try (Connection c = DatabaseHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getJenis());
            ps.setDouble(2, t.getJumlah());
            ps.setString(3, t.getDetail());
            ps.setString(4, t.getWaktu());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(Transaction t) {
        if (t.getId() == null) return false;
        String sql = "UPDATE transaksi SET jenis = ?, jumlah = ?, detail = ?, waktu = ? WHERE id = ?";
        try (Connection c = DatabaseHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getJenis());
            ps.setDouble(2, t.getJumlah());
            ps.setString(3, t.getDetail());
            ps.setString(4, t.getWaktu());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM transaksi WHERE id = ?";
        try (Connection c = DatabaseHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getAll() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT id, jenis, jumlah, detail, waktu FROM transaksi ORDER BY waktu DESC";
        try (Connection c = DatabaseHelper.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getInt("id"),
                        rs.getString("jenis"),
                        rs.getDouble("jumlah"),
                        rs.getString("detail"),
                        rs.getString("waktu")
                );
                list.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
