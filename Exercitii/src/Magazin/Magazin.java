package Magazin;

public class Magazin {

    private String nume;
    private MagazinService produse;

    public Magazin(String nume, MagazinService produse) {
        this.nume = nume;
        this.produse = produse;
    }

    public Magazin() {
        this("Nan", null);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public MagazinService getProduse() {
        return produse;
    }

    public void setProduse(MagazinService produse) {
        this.produse = produse;
    }

    @Override
    public String toString() {
        return "Magazin " + nume  +
                "\nProduse: " + produse.toString();
    }

    public double getTotalMagazin() {
        double total = 0;
        for(Produs produs : produse.produse)
            total += produs.getTotalProdus();
        return total;

    }
}




class MagazinMain {
    public static void main(String[] args) {
        /*Produs[] produse = new Produs[3];
        produse[0] = new Produs("Faina", 3, 5);
        produse[1] = new Produs("Zahar", 4,7);
        produse[2] = new Produs("Sare", 3,10);

        Magazin magazin1 = new Magazin("Kaufland", produse);
        System.out.println(magazin1.toString());
        System.out.println(magazin1.getTotalMagazin());*/

        /*Magazin magazin2 = new Magazin("Profi", new Produs[]{new Produs("Paine", 1, 100)
        , new Produs("Ciocolata", 20, 50)});
        System.out.println(magazin2.toString());*/

        Magazin magazin = new Magazin("Penny", new MagazinService(10));
        magazin.getProduse().addProdus(new Produs("Carnati", 15, 20));
        System.out.println(magazin.toString());





    }
}
