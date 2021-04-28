package IO;
import Models.Author;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingletonAuthor {
    private static SingletonAuthor instance;
    private ArrayList<Author> authors;


    private SingletonAuthor() {
        System.out.println("First time the constructor is being called.");
    }

    public static SingletonAuthor getInstance() {
        if (instance == null) {
            instance = new SingletonAuthor();
        }
        return instance;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }


    public void ReadAuthor(String filePath) {
        String line = "";
        String splitBy = ",";
        ArrayList<Author> personList = new ArrayList<Author>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    String[] parameters = line.split(splitBy);    // use comma as separator
                    List<String> aux = Arrays.stream(parameters).map(String::trim).collect(Collectors.toList());
                    personList.add(new Author(aux.get(0), aux.get(1)));
                    //System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.authors =  personList;
    }

    public void WriteAuthor(String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);
        for(Author p : authors){
            csvWriter.append(p.getId()).append(",").append(p.getName()).append(",").append(p.getNationality());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}


class Testareeee{
    public static void main(String[] args) throws IOException {
        SingletonAuthor singleton = SingletonAuthor.getInstance();
        SingletonAuthor singleton1 = SingletonAuthor.getInstance();

        if (singleton == singleton1) {
            System.out.println("MySingleton is singleton.");
        }

        SingletonAuthor.getInstance().ReadAuthor("C:\\Users\\Stefan\\IdeaProjects\\Library\\src\\Tests\\file1.csv");
        for(Author p : SingletonAuthor.getInstance().getAuthors())
            System.out.println(p.toString());
        SingletonAuthor.getInstance().WriteAuthor("C:\\Users\\Stefan\\IdeaProjects\\Library\\src\\Tests\\output.csv");
    }
}
