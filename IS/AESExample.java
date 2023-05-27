import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {
    private static final String ALGORITHM = "AES";
    // This constant specifies the encryption algorithm being used, which is AES (Advanced Encryption Standard). AES is a symmetric encryption algorithm widely used for secure encryption of sensitive data.

    private static final String SECRET_KEY = "ThisIsASecretKey";
    // This constant represents the secret key used for encryption and decryption. In AES, the secret key can have different lengths, such as 128 bits, 192 bits, or 256 bits, depending on the AES variant being used

    private static final String CIPHER_INSTANCE = "AES";
    // This constant specifies the cipher instance used for encryption and decryption. It defines the combination of the encryption algorithm, mode of operation, and padding scheme.

    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
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



// Let's go through the code step by step:

// The import statements include the necessary classes from the javax.crypto package for encryption and decryption, as well as the Base64 class from java.util for encoding and decoding data.

// The class AESExample contains two constants: ALGORITHM, which specifies the encryption algorithm (AES), and SECRET_KEY, which is the key used for encryption and decryption. In this example, the key is set to "ThisIsASecretKey". It's important to note that in real-world scenarios, a secure key generation process should be used.

// The CIPHER_INSTANCE constant specifies the transformation or algorithm mode to be used by the Cipher class.

// The encrypt method takes a plaintext string as input and returns the corresponding encrypted text. It follows these steps:
// a. The SecretKeySpec class is used to create a secret key object from the SECRET_KEY string.
// b. An instance of the Cipher class is obtained using the CIPHER_INSTANCE.
// c. The cipher is initialized in encryption mode (Cipher.ENCRYPT_MODE) with the secret key.
// d. The plaintext string is converted to bytes using UTF-8 encoding (StandardCharsets.UTF_8).
// e. The doFinal method is called on the cipher object to perform the encryption, returning the encrypted bytes.
// f. The encrypted bytes are encoded using Base64 and returned as a string.

// The decrypt method takes a ciphertext string as input and returns the corresponding decrypted text. It follows these steps:
// a. The SecretKeySpec class is used to create a secret key object from the SECRET_KEY string.
// b. An instance of the Cipher class is obtained using the CIPHER_INSTANCE.
// c. The cipher is initialized in decryption mode (Cipher.DECRYPT_MODE) with the secret key.
// d. The ciphertext string is decoded from Base64 into encrypted bytes.
// e. The doFinal method is called on the cipher object to perform the decryption, returning the decrypted bytes.
// f. The decrypted bytes are converted back to a string using UTF-8 encoding.

// The main method demonstrates the usage of the encrypt and decrypt methods. It encrypts the plaintext string "Hello, World!" and then decrypts the resulting ciphertext. The plaintext, encrypted text, and decrypted text are printed to the console.

// Note: The code includes a generic exception handling (catch (Exception e)) to catch and print any exception that may occur during encryption or decryption.