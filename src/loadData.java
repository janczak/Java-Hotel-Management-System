/*
import java.io.*;

public class loadData {
    public void readFile(String[] FILENAME){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line = reader.readLine();
            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
*/