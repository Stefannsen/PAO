package Models;
import java.util.Scanner;

public class Section {
    private static int count = 0;
    private final String id;
    private String name;

    public Section(String name) {
        this.name = name;
    }

    public Section(){
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
    }

    {
        count++;
        this.id = "s" + count;
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
}
