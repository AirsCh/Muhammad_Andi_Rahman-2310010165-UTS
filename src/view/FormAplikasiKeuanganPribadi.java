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
        cmbJenis.removeAllItems();
        cmbJenis.addItem("Pilih -");
        cmbJenis.addItem("Pemasukan");
        cmbJenis.addItem("Pengeluaran");
        cmbJenis.setSelectedIndex(0);
        
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
        btnSimpan.addActionListener(e -> onSave());
        btnEdit.addActionListener(e -> onEdit());
        btnHapus.addActionListener(e -> onDelete());

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
                    cmbJenis.setSelectedItem(jenis);
                    txtJumlah.setText(String.valueOf(jumlah));
                    txtaDetail.setText(detail);
                }
            }
        });

        // Clear selection when form gains focus
        txtJumlah.addFocusListener(new FocusAdapter() {
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
        String jenis = (String) cmbJenis.getSelectedItem();
        String jumlahStr = txtJumlah.getText().trim();
        double jumlah = controller.parseJumlah(jumlahStr);
        String detail = txtaDetail.getText().trim();

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
        String jenis = (String) cmbJenis.getSelectedItem();
        String jumlahStr = txtJumlah.getText().trim();
        double jumlah = controller.parseJumlah(jumlahStr);
        String detail = txtaDetail.getText().trim();

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
        cmbJenis.setSelectedIndex(0);
        txtJumlah.setText("");
        txtaDetail.setText("");
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
        lblJenis = new javax.swing.JLabel();
        cmbJenis = new javax.swing.JComboBox<>();
        lblJumlah = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        scrlDetail = new javax.swing.JScrollPane();
        txtaDetail = new javax.swing.JTextArea();
        lblText = new javax.swing.JLabel();
        PanelButton = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Keungan Pribadi");

        PanelJudul.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setText("Aplkasi Keuangan Pribadi");

        javax.swing.GroupLayout PanelJudulLayout = new javax.swing.GroupLayout(PanelJudul);
        PanelJudul.setLayout(PanelJudulLayout);
        PanelJudulLayout.setHorizontalGroup(
            PanelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJudulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblJudul)
                .addGap(248, 248, 248))
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
                .addComponent(scrlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTabelLayout.setVerticalGroup(
            PanelTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblJenis.setText("Jenis Laporan");

        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblJumlah.setText("Jumlah Uang");

        txtaDetail.setColumns(20);
        txtaDetail.setRows(5);
        scrlDetail.setViewportView(txtaDetail);

        lblText.setText("Detail Laporan");

        javax.swing.GroupLayout PanelInputLayout = new javax.swing.GroupLayout(PanelInput);
        PanelInput.setLayout(PanelInputLayout);
        PanelInputLayout.setHorizontalGroup(
            PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJenis)
                    .addComponent(lblJumlah)
                    .addComponent(lblText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtJumlah)
                    .addComponent(cmbJenis, 0, 187, Short.MAX_VALUE)
                    .addComponent(scrlDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelInputLayout.setVerticalGroup(
            PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenis)
                    .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlah)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputLayout.createSequentialGroup()
                        .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrlDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSimpan.setText("Simpan");

        btnEdit.setText("Edit");

        btnHapus.setText("Hapus");

        javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
        PanelButton.setLayout(PanelButtonLayout);
        PanelButtonLayout.setHorizontalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnSimpan)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnHapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelButtonLayout.setVerticalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(PanelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelTabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblText;
    private javax.swing.JScrollPane scrlDetail;
    private javax.swing.JScrollPane scrlPanel;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextArea txtaDetail;
    // End of variables declaration//GEN-END:variables
}
