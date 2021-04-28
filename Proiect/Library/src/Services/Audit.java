package Services;

import IO.SingletonSection;
import Models.Section;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Audit {
    private static String filePath = "src/ioFiles/Audit.csv";
    private static Audit instance;


    private Audit() {}

    public static Audit getInstance() {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    public void writeInfo() throws IOException {

        FileWriter csvWriter = new FileWriter(filePath, true);
        String methodName = new Throwable().getStackTrace()[1].getMethodName();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        csvWriter.append(methodName).append(",").append(String.valueOf(timestamp));
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();

    }

}
