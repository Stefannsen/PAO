package Models;


public class Author {
    public static int count = 1;
    public final String id;
    public String name;
    public String nationality;
    // public Book[] books;

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    {
        this.id = "a" + count;
        count++;
    }

    @Override
    public String toString() {
        return "Author{" +
                "Name:: " + name +
                "\nCountry" + nationality;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
