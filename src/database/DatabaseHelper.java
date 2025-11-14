package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:keuangan_pribadi.db";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL);
        }
        return conn;
    }

    // create table if not exists
    public static void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS transaksi ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "jenis TEXT NOT NULL,"            // Pemasukan / Pengeluaran
                + "jumlah REAL NOT NULL,"
                + "detail TEXT,"
                + "waktu TEXT NOT NULL"            // ISO datetime string
                + ");";
        try (Connection c = getConnection(); Statement st = c.createStatement()) {
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
