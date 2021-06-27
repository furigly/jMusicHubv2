package musichub.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Log {
    private FileWriter logFile;

    public Log() {
        // créé le fichier s'il n'existe pas
        try {
            File logFile = new File("log.txt");
            logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred creating (or opening if it already exists) the log file.");
        }
        // ouvre le filewriter
        try {
            logFile = new FileWriter("log.txt");
        } catch (IOException e) {
            System.out.println("An error occurred opening the log file.");
        }
    }

    public void writeError(String string) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        try {
            logFile.write(formatter.format(date) + " : " + string);
        } catch (IOException e) {
            System.out.println("An error occurred writing in the log file.");
        }
    }

    public void close() {
        try {
            logFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred closing the log file.");
        }
    }
}
