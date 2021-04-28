package Models;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(nationality, author.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nationality);
    }
}
