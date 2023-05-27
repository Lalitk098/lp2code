import java.util.*;

public class TranspositionCipher {

    public static String encrypt(String plaintext, int key) {
        // Remove spaces from plaintext
        plaintext = plaintext.replaceAll("\\s", "");

        int numRows = (int) Math.ceil((double) plaintext.length() / key);
        char[][] grid = new char[numRows][key];

        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key; j++) {
                if (index < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(index++);
                } else {
                    grid[i][j] = 'X'; // Padding with 'X' for incomplete cells
                }
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < numRows; i++) {
                ciphertext.append(grid[i][j]);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int key) {
        int numRows = (int) Math.ceil((double) ciphertext.length() / key);
        char[][] grid = new char[numRows][key];

        int index = 0;
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < numRows; i++) {
                if (index < ciphertext.length()) {
                    grid[i][j] = ciphertext.charAt(index++);
                }
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key; j++) {
                plaintext.append(grid[i][j]);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        int key = 3;

        System.out.println("Plain Text: " + plaintext);
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
        sc.close();
    }
}


// Here's a step-by-step explanation of how the code works:

// The TranspositionCipher class is defined, which contains three methods: encrypt, decrypt, and main. The encrypt and decrypt methods perform encryption and decryption operations, respectively, while the main method is the entry point of the program.

// The encrypt method takes two parameters: the plaintext message and the key (an integer value). It returns the encrypted ciphertext as a string.

// In the encrypt method, spaces are removed from the plaintext using the replaceAll method with a regular expression \\s. This step eliminates spaces from the plaintext.

// The number of rows (numRows) is calculated based on the length of the plaintext and the key. It determines the number of rows required to construct the encryption grid. The formula Math.ceil((double) plaintext.length() / key) calculates the minimum number of rows needed to accommodate all characters of the plaintext.

// A 2D character array grid is created to represent the encryption grid. It has numRows rows and key columns.

// The index variable keeps track of the position in the plaintext string.

// Two nested loops iterate through the grid and fill it with characters from the plaintext. If there are still characters remaining in the plaintext, they are added to the grid. Otherwise, the remaining cells are padded with the character 'X'.

// After constructing the grid, a StringBuilder named ciphertext is created to store the encrypted text.

// Two nested loops iterate through the grid in a column-wise manner, appending the characters to the ciphertext StringBuilder. This process ensures that the ciphertext is formed by reading the characters row by row from the grid.

// Finally, the encrypted ciphertext is returned as a string.

// The decrypt method takes two parameters: the ciphertext and the key. It returns the decrypted plaintext message.

// In the decrypt method, a similar process is followed as in the encrypt method. The ciphertext is divided into rows and columns, forming the decryption grid.

// The plaintext StringBuilder is created to store the decrypted text.

// Two nested loops iterate through the grid row by row and append the characters to the plaintext StringBuilder. This process reconstructs the original plaintext message.

// Finally, the decrypted plaintext is returned as a string.

// The main method is executed when the code is run. It demonstrates the usage of the encrypt and decrypt methods.

// A plaintext message, "Hello, World!", is defined.
// The key is set to 3, indicating that the encryption and decryption operations will use a key of 3.
// The plaintext is encrypted using the encrypt method, and the encrypted ciphertext is stored in the encryptedText variable.
// The encrypted ciphertext is printed.
// The decrypt method is called with the encryptedText and the key as inputs to retrieve the original plaintext message.
// The decrypted plaintext is printed.
// The Transposition Cipher algorithm encrypts the plaintext by rearranging its characters based on the key and then decrypts it by reversing the process.