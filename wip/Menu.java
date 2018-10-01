import java.io.*;
import java.util.*;

public class Menu {

    public static void welcome() {
        System.out.println("\n\nHELLO! And welcome to FORGOTTEN QUEST! (v 0.02.0)");
        System.out.println("For a list of commands available Please type help\n");
    }

    public static String[] helpReader() throws FileNotFoundException {
        HelpFile text = new HelpFile();

        String line = null;

        try {
            FileReader fileReader = new FileReader("help.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                text.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file 'help.txt'");
        } catch (IOException ex) {
            System.out.println("Unable to open file 'help.txt'");
        }

        return text.text;
    }
}

class HelpFile {
    String[] text;
    int timesCalled;

    public HelpFile() {
        text = null;
        timesCalled = 0;
    }

    public String[] add(String toAdd) {
        if (text == null) {
            text = new String[] { toAdd };
        } else if (text.length >= timesCalled) {
            String[] newText = new String[timesCalled * 2];
            int counter = 0;
            while (counter < timesCalled) {
                newText[counter] = text[counter];
                ++counter;
            }
            text = newText;
            text[timesCalled] = toAdd;
        } else {
            text[timesCalled] = toAdd;
        }
        ++timesCalled;
        return text;
    }
}
