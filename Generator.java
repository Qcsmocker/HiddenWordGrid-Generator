import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Generate a square grid of size N of random characters.

public class Generator {
    
    // Generate a square grid of size N of random characters.
    public Generator(int n) {

        int N = n;

        char[][] grid = getRandomGrid(N);

        try {
            ArrayList<String> dictionnaire = FileReader.readDictionary("/home/metacrib/Documents/GitHub/MotsCacheGenerateur/dictionnaire.txt");
            
            // Print the dictionary.
            for (String word : dictionnaire) {
                grid = addWord(grid, word);
            }
            writeGrid(grid);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        
        
    }

    //Add a random word from the dictionnary to the grid.
    public char[][] addWord(char[][] grid, String word) {

        // pick a random position for the word
        int x = (int) (Math.random() * grid.length);
        int y = (int) (Math.random() * grid[0].length);

        // pick a random direction for the word (8)
        int direction = (int) (Math.random() * 8);

        // switch on the 8 possible direction and check if the word would fit in the grid and add it once, else regenerate the direction.

        switch (direction) {
            case 0:
                // check if it can add the word up, if it can, add it.
                if (y - word.length() >= 0) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x][y - i] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 1:
                // check if it can add the word down
                if (y + word.length() < grid[0].length) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x][y + i] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 2:
                // check if it can put the word to the left
                if (x - word.length() >= 0) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x - i][y] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 3:
                // check if the word can be put to the right
                if (x + word.length() < grid.length) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x + i][y] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 4:
                //check if it can add the word up and to the left
                if (y - word.length() >= 0 && x - word.length() >= 0) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x - i][y - i] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 5:
                //check if it can add the word up and to the right
                if (y - word.length() >= 0 && x + word.length() < grid.length) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x + i][y - i] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 6:
                // check if it can add the word down and to the left
                if (y + word.length() < grid[0].length && x - word.length() >= 0) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x - i][y + i] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
            case 7:
                // check if it can add the word down and to the right
                if (y + word.length() < grid[0].length && x + word.length() < grid.length) {
                    for (int i = 0; i < word.length(); i++) {
                        grid[x + i][y + i] = word.charAt(i);
                    }
                    break;
                } else {
                    return addWord(grid, word);
                }
        }
        
        // print the information about the word added, the direction and the position
        System.out.println("Word added: " + word+"\nDirection: " + direction + "\nPosition: (" + x + "," + y + ")");

        return grid;
    }

    public char[][] getRandomGrid(int N) {

        char[][] grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = (char) (Math.random() * 26 + 'a');
            }
        }

        return grid;
    }

    public void writeGrid(char[][] grid) {

        int N = grid.length;

        // write the grid in a text file with a space between each character except the last one 
        try {
            FileWriter writer = new FileWriter("grid.txt");
            // the first line is the size of the grid
            writer.write(N + "\n");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    writer.write(grid[i][j]);
                    if (j != N - 1) {
                        writer.write(' ');
                    }
                }
                // only add an empty line if it's not the last line
                if (i != N - 1) {
                    writer.write('\n');
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
