import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class DESExample {
    private static final String ALGORITHM = "DES";
    // This constant specifies the encryption algorithm being used, which in this case is DES (Data Encryption Standard). DES is a symmetric encryption algorithm that operates on blocks of data.

    private static final String SECRET_KEY = "ThisIsASecretKey";
    // This constant represents the secret key used for encryption and decryption. In DES, the secret key is required to be 8 bytes long.

    private static final String CIPHER_INSTANCE = "DES";
    // This constant specifies the cipher instance used for encryption and decryption. It defines the combination of the encryption algorithm, mode of operation, and padding scheme

    public static String encrypt(String plaintext) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String plaintext = sc.nextLine();
            System.out.println("Plain Text: " + plaintext);

            String encryptedText = encrypt(plaintext);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}




// The code begins by importing the necessary classes for encryption and decryption.

// The ALGORITHM variable is set to "DES", indicating that we will be using the DES encryption algorithm.

// The SECRET_KEY variable is set to "ThisIsASecretKey", which will be used as the secret key for encryption and decryption. In a real-world scenario, you should use a strong and secure key.

// The CIPHER_INSTANCE variable is set to "DES/ECB/PKCS5Padding", specifying that we will be using DES encryption in ECB (Electronic Codebook) mode with PKCS5 padding.

// The encrypt method takes a plaintext string as input and returns the encrypted ciphertext as a Base64-encoded string. Here's how the encryption process works:

// The SECRET_KEY is transformed into a DESKeySpec object.
// A SecretKeyFactory is used to generate a SecretKey object from the DESKeySpec.
// A Cipher object is initialized with the CIPHER_INSTANCE and the SecretKey for encryption.
// The plaintext string is converted into bytes using UTF-8 encoding.
// The Cipher encrypts the plaintext bytes and returns the encrypted bytes.
// The encrypted bytes are then encoded as a Base64 string and returned.
// The decrypt method takes a ciphertext string as input and returns the decrypted plaintext string. Here's how the decryption process works:

// Similar to the encryption process, the SECRET_KEY is transformed into a DESKeySpec object and a SecretKey object is generated.
// A Cipher object is initialized with the CIPHER_INSTANCE and the SecretKey for decryption.
// The ciphertext string is decoded from Base64 to obtain the encrypted bytes.
// The Cipher decrypts the encrypted bytes and returns the decrypted bytes.
// The decrypted bytes are converted back to a plaintext string using UTF-8 encoding and returned.
// The main method demonstrates the usage of the encrypt and decrypt methods. It encrypts the plaintext string "Hello, World!", prints the encrypted ciphertext, then decrypts the ciphertext and prints the decrypted plaintext.
