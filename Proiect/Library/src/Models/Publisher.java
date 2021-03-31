package Models;

public class Publisher {
    private static int count = 1;
    private final String id;
    private String name;
    private String country;


    public Publisher(String name, String country) {
        this.name = name;
        this.country = country;
    }

    {
        this.id = "p" + count;
        count++;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nCountry: " + country;
    }
}
