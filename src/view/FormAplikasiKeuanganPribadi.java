package view;

import controller.TransactionController;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FormAplikasiKeuanganPribadi extends javax.swing.JFrame {

    private TransactionController controller;
    private DefaultTableModel tableModel;
    private Integer selectedId = null;
    
    public FormAplikasiKeuanganPribadi() {
        initComponents();
        controller = new TransactionController();
        initCustom();
        loadTableData();
    }

    private void initCustom() {
        // atur combobox dengan jenis laporan
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Pemasukan");
        jComboBox1.addItem("Pengeluaran");

        // set table model dengan kolom ID, Jenis, Jumlah, Detail, Waktu
        tableModel = new DefaultTableModel(new Object[]{"ID", "Jenis", "Jumlah", "Detail", "Waktu"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // non-editable langsung di tabel
            }
        };
        TableInformasi.setModel(tableModel);
        TableInformasi.removeColumn(TableInformasi.getColumnModel().getColumn(0)); // sembunyikan kolom ID dari tampilan

        // event tombol
        jButton1.addActionListener(e -> onSave());
        jButton2.addActionListener(e -> onEdit());
        jButton3.addActionListener(e -> onDelete());

        // saat klik baris tabel, isi form untuk edit
        TableInformasi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewRow = TableInformasi.getSelectedRow();
                if (viewRow >= 0) {
                    // karena kolom ID disembunyikan, model index 0 ada di model
                    int modelRow = TableInformasi.convertRowIndexToModel(viewRow);
                    Integer id = (Integer) tableModel.getValueAt(modelRow, 0);
                    String jenis = (String) tableModel.getValueAt(modelRow, 1);
                    Double jumlah = (Double) tableModel.getValueAt(modelRow, 2);
                    String detail = (String) tableModel.getValueAt(modelRow, 3);
                    selectedId = id;
                    jComboBox1.setSelectedItem(jenis);
                    jTextField1.setText(String.valueOf(jumlah));
                    jTextArea1.setText(detail);
                }
            }
        });

        // Clear selection when form gains focus
        jTextField1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                // nothing for now
            }
        });
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Transaction> list = controller.listAll();
        for (Transaction t : list) {
            // simpan ID di kolom pertama (tetap ada di model meski disembunyikan)
            Object[] row = new Object[]{
                    t.getId(),
                    t.getJenis(),
                    t.getJumlah(),
                    t.getDetail(),
                    t.getWaktu()
            };
            tableModel.addRow(row);
        }
    }

    private void onSave() {
        String jenis = (String) jComboBox1.getSelectedItem();
        String jumlahStr = jTextField1.getText().trim();
        double jumlah = controller.parseJumlah(jumlahStr);
        String detail = jTextArea1.getText().trim();

        if (jenis == null || jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Pastikan jenis dipilih dan jumlah > 0", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = controller.save(jenis, jumlah, detail);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
            clearForm();
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onEdit() {
        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi yang akan diedit dari tabel.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String jenis = (String) jComboBox1.getSelectedItem();
        String jumlahStr = jTextField1.getText().trim();
        double jumlah = controller.parseJumlah(jumlahStr);
        String detail = jTextArea1.getText().trim();

        if (jenis == null || jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Pastikan jenis dipilih dan jumlah > 0", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = controller.update(selectedId, jenis, jumlah, detail);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
            clearForm();
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onDelete() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus transaksi ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi yang akan dihapus dari tabel.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        boolean ok = controller.delete(selectedId);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            clearForm();
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        jComboBox1.setSelectedIndex(0);
        jTextField1.setText("");
        jTextArea1.setText("");
        selectedId = null;
        TableInformasi.clearSelection();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelJudul = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        PanelTabel = new javax.swing.JPanel();
        scrlPanel = new javax.swing.JScrollPane();
        TableInformasi = new javax.swing.JTable();
        PanelInput = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        PanelButton = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelJudul.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblJudul.setText("Aplkasi Keuangan Pribadi");

        javax.swing.GroupLayout PanelJudulLayout = new javax.swing.GroupLayout(PanelJudul);
        PanelJudul.setLayout(PanelJudulLayout);
        PanelJudulLayout.setHorizontalGroup(
            PanelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJudulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblJudul)
                .addGap(218, 218, 218))
        );
        PanelJudulLayout.setVerticalGroup(
            PanelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelJudulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJudul, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelTabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TableInformasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrlPanel.setViewportView(TableInformasi);

        javax.swing.GroupLayout PanelTabelLayout = new javax.swing.GroupLayout(PanelTabel);
        PanelTabel.setLayout(PanelTabelLayout);
        PanelTabelLayout.setHorizontalGroup(
            PanelTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTabelLayout.setVerticalGroup(
            PanelTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Jenis Laporan");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Jumlah Uang");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Detail Laporan");

        javax.swing.GroupLayout PanelInputLayout = new javax.swing.GroupLayout(PanelInput);
        PanelInput.setLayout(PanelInputLayout);
        PanelInputLayout.setHorizontalGroup(
            PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelInputLayout.setVerticalGroup(
            PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Simpan");

        jButton2.setText("Edit");

        jButton3.setText("Hapus");

        javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
        PanelButton.setLayout(PanelButtonLayout);
        PanelButtonLayout.setHorizontalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PanelButtonLayout.setVerticalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAplikasiKeuanganPribadi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelJudul;
    private javax.swing.JPanel PanelTabel;
    private javax.swing.JTable TableInformasi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JScrollPane scrlPanel;
    // End of variables declaration//GEN-END:variables
}
