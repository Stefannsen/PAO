package Magazin;

public class Produs {

    private String nume;
    private double pret;
    private int cantitate;

    public Produs(String nume, double pret, int cantitate) {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Produs() {
        this("Nan", 0, 0);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Produs: " + nume + "  " + pret + " lei  " + cantitate + " kg";
    }

    public double getTotalProdus() {
        return this.cantitate * this.pret;
    }
}


class ProdusTest {

    public static void main (String[] args) {
        Produs produs1 = new Produs("Faina", 20, 5);
        System.out.println(produs1.toString());
    }
}