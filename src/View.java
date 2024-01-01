import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {

    public JFrame frame;
    private JPanel panelMenu, panelJumlah, panelDiskon, panelTotal;
    private JComboBox<String> comboMenu;
    private JTextField txtJumlah, txtDiskon;
    private JLabel lblTotal;

    public View() {
        // Buat jendela GUI
        frame = new JFrame("Kasir Warung Makan");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buat panel menu
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());
        comboMenu = new JComboBox<>();
        for (Menu menu : Kasir.getInstance().getDaftarMenu()) {
            comboMenu.addItem(menu.getNama());
        }
        panelMenu.add(comboMenu);

        // Buat panel jumlah item
        panelJumlah = new JPanel();
        panelJumlah.setLayout(new FlowLayout());
        JLabel lblJumlah = new JLabel("Jumlah Item:");
        txtJumlah = new JTextField(10);
        panelJumlah.add(lblJumlah);
        panelJumlah.add(txtJumlah);

        // Buat panel diskon
        panelDiskon = new JPanel();
        panelDiskon.setLayout(new FlowLayout());
        JLabel lblDiskon = new JLabel("Diskon (%):");
        txtDiskon = new JTextField(10);
        panelDiskon.add(lblDiskon);
        panelDiskon.add(txtDiskon);

        // Buat panel total
        panelTotal = new JPanel();
        panelTotal.setLayout(new FlowLayout());
        lblTotal = new JLabel("Total: Rp. 0");
        lblTotal.setFont(new Font("Serif", Font.BOLD, 16));
        panelTotal.add(lblTotal);

        // Tambahkan panel-panel ke jendela GUI
        frame.add(panelMenu, BorderLayout.NORTH);
        frame.add(panelJumlah, BorderLayout.CENTER);
        frame.add(panelDiskon, BorderLayout.SOUTH);
        frame.add(panelTotal, BorderLayout.EAST);

        // Buat event handler untuk tombol hitung
        JButton btnHitung = new JButton("Hitung");
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dapatkan data dari inputan pengguna
                String menu = comboMenu.getSelectedItem().toString();
                int jumlah = Integer.parseInt(txtJumlah.getText());
                int diskon = Integer.parseInt(txtDiskon.getText());

                // Hitung total pembelian
                Kasir kasir = Kasir.getInstance();
                kasir.setJumlahItem(jumlah);
                kasir.setDiskon(diskon);
                int total = kasir.hitungTotal();

                // Tampilkan total pembelian
                lblTotal.setText("Total: Rp. " + total);

                // Tampilkan pesan notifikasi
                JOptionPane.showMessageDialog(frame, "Total pembelian: Rp. " + total);
            }
        });
        frame.add(btnHitung, BorderLayout.WEST);

        // Tampilkan jendela GUI
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
