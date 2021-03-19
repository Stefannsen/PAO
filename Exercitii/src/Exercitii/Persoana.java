package Exercitii;

import java.util.Objects;

enum gender{
    M,
    F
}

public class Persoana {

    private String nume;
    private String prenume;
    private int varsta;
    private final long id;
    private gender gen;
    static long numberOfPersons = 0;

    // Constructor cu parametrii
    public Persoana(String nume, String prenume, int varsta, gender gen){
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.id = ++ numberOfPersons;
        this.gen = gen;

    }

    // Constructor fara parametrii
    public Persoana(){
        this("","",0,null);
    }

    // setters si getters
    public String getNume(){
        return this.nume;
    }

    public String getPrenume(){
        return this.prenume;
    }

    public int getVarsta(){
        return this.varsta;
    }

    public gender getGen(){
        return this.gen;
    }

    public long getId(){
        return this.id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setGen(gender gen) {
        this.gen = gen;
    }

    // toString
    @Override
    public String toString() {
        return "Persoana #" + id +
                "\n------------" +
                "\nnume: " + nume +
                "\nprenume: " + prenume +
                "\nvarsta: " + varsta +
                "\ngen: " + gen +
                "\n------------";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return id == persoana.id;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, varsta, id, gen);
    }
}

class MainPersoana {

    public static void main (String[] args) {

        Persoana persoana1 = new Persoana("Popescu", "Maria", 25, gender.F);
        System.out.println(persoana1.getId());
        persoana1.setNume("Ionescu");
        System.out.println(persoana1.getPrenume() + " " + persoana1.getNume());

        Persoana persoana2 = new Persoana();
        System.out.println(persoana2.getId());
        System.out.println(persoana1.toString());

        Persoana p1 = new Persoana("Pop", "Alexandru", 32, gender.M);
        Persoana p2 = new Persoana("Pop", "Alexandru", 32, gender.M);
        Persoana p3 = p1;

        System.out.println(p1.equals(p2));  // id diferit
        System.out.println(p1.equals(p3));  // always true


    }
}

