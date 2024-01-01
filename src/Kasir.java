import java.util.ArrayList;
public class Kasir  {
    private static Kasir instance;

    private ArrayList<Menu> daftarMenu;
    private int jumlahItem;
    private int diskon;

    private Kasir() {
        daftarMenu = new ArrayList<>();
        daftarMenu.add(new Menu("Ayam Geprek Matah (14K)", 14000));
        daftarMenu.add(new Menu("Ayam Goreng Kremes (15K)", 15000));
        daftarMenu.add(new Menu("Nasi Goreng (10K)", 10000));
        daftarMenu.add(new Menu("Soto Ayam (12K)", 12000));
    }

    public static Kasir getInstance() {
        if (instance == null) {
            instance = new Kasir();
        }
        return instance;
    }

    public void setJumlahItem(int jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public int hitungTotal() {
        int total = 0;
        for (Menu menu : daftarMenu) {
            total += menu.getHarga() * jumlahItem;
        }
        if (diskon > 0) {
            total = total - (total * diskon / 100);
        }
        return total;
    }

    public ArrayList<Menu> getDaftarMenu() {
        return daftarMenu;
    }
}
