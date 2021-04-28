package IO;

import Models.Author;
import Models.Publisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingletonPublisher {
    private static SingletonPublisher instance;
    private ArrayList<Publisher> publishers;


    private SingletonPublisher() {
        System.out.println("First time the constructor is being called.");
    }

    public static SingletonPublisher getInstance() {
        if (instance == null) {
            instance = new SingletonPublisher();
        }
        return instance;
    }

    public ArrayList<Publisher> getPublishers() {
        return publishers;
    }


    public void ReadPublisher(String filePath) {
        String line = "";
        String splitBy = ",";
        ArrayList<Publisher> personList = new ArrayList<Publisher>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    String[] parameters = line.split(splitBy);    // use comma as separator
                    List<String> aux = Arrays.stream(parameters).map(String::trim).collect(Collectors.toList());
                    personList.add(new Publisher(aux.get(0), aux.get(1)));
                    //System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.publishers =  personList;
    }

    public void WritePublisher(String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);
        for(Publisher p : publishers){
            csvWriter.append(p.getId()).append(",").append(p.getName()).append(",").append(p.getCountry());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
