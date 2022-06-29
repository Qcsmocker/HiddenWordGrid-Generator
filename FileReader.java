import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileReader {

    /**
     * Lit le fichier dicitionaire et renvoie une liste de mots.
     * 
     * @param fileName
     * @return La dictionaire de recherche
     * @throws IOException
     */
    public static ArrayList readDictionary(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        ArrayList<String> dictionnaire = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            dictionnaire.add(line);
        }

        return dictionnaire;
    }

}
