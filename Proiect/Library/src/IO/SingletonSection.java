package IO;

import Models.Author;
import Models.Section;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingletonSection {
    private static SingletonSection instance;
    private ArrayList<Section> sections;


    private SingletonSection() {
        System.out.println("First time the constructor is being called.");
    }

    public static SingletonSection getInstance() {
        if (instance == null) {
            instance = new SingletonSection();
        }
        return instance;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }


    public void ReadSection(String filePath) {
        String line = "";
        String splitBy = ",";
        ArrayList<Section> personList = new ArrayList<Section>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    String[] parameters = line.split(splitBy);    // use comma as separator
                    List<String> aux = Arrays.stream(parameters).map(String::trim).collect(Collectors.toList());
                    personList.add(new Section(aux.get(0)));
                    //System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.sections =  personList;
    }

    public void WriteSection(String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);
        for(Section p : sections){
            csvWriter.append(p.getId()).append(",").append(p.getName());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
