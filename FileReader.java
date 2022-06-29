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

        // Ouvre le file en lecture
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));

        // crée une liste pour stocker les mots
        ArrayList<String> dictionnaire = new ArrayList<String>();

        // Lit le fichier ligne par ligne et ajoute les mots à la liste
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            dictionnaire.add(line);
        }

        return dictionnaire;
    }

    /**
     * Lit le fichier de mots croisés et renvoie la grille de mots croisés.
     * 
     * @param fileName
     * @return La grille de mots croisés
     * @throws IOException
     */
    public static char[][] readGrid(String fileName) throws IOException {

        // Lit le fichier de mots croisés.
        StringBuilder chaine = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;

        // the first line is the gridSize in integer
        // convert string to integer

        int gridSize = Integer.parseInt(bufferedReader.readLine());

        // Récupère tous les caractères de la grille.
        while ((line = bufferedReader.readLine()) != null) {
            chaine.append(line);
        }

        bufferedReader.close();
        fileInputStream.close();

        return fillGrid(chaine.toString(), gridSize);
    }

    /**
     * Rempli la grille de mots croisés de lettres.
     * 
     * @param chaine
     * @param gridSize
     * @return La grille de mots croisés
     */
    public static char[][] fillGrid(String chaine, int gridSize) {

        // Enlève les espaces de la chaine
        String chaineWithoutSpaces = chaine.toString().replaceAll(" ", "");

        int counter = 0;

        char[][] grille = new char[gridSize][gridSize];

        // double for loop to print the grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grille[i][j] = chaineWithoutSpaces.charAt(counter);
                counter++;
            }
        }

        return grille;
    }

}
