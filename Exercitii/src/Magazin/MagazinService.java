package Magazin;

public class MagazinService {
    Produs[] produse;
    private int lastIndex;
    private int maximNrProduse;

    public MagazinService(int maximNrProduse) {
        this.produse = new Produs[maximNrProduse];
        this.lastIndex = 0;
        this.maximNrProduse = maximNrProduse;
    }

    public void addProdus(Produs produs) {
        if(lastIndex < produse.length) {
            produse[lastIndex] = produs;
            System.out.println("Add produs " +
                    produs.getClass().getSimpleName() +
                    " at index " + lastIndex++);
        }
    }

    @Override
    public String toString() {
        String string = "";
        for(int i = 0; i < lastIndex; i++)
            string += produse[i].toString();
        return string;

    }

}

class MagazinServiceMain {
    public static void main(String[] args){
        MagazinService service = new MagazinService(3);
        service.addProdus(new Produs("Mere", 3, 10));
        service.addProdus(new Produs("Banane", 5, 7));
        service.addProdus(new Produs("Portocale",5,5));
        System.out.println(service.toString());


    }
}
