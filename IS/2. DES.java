import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class DES{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        
        //String we want to encrypt
        String  message="This is Abhishek.";
        byte[] myMessage =message.getBytes(); //string to byte array as DES works on bytes

        //If you want to use your own key
        // SecretKeyFactory MyKeyFactory = SecretKeyFactory.getInstance("DES");
        // String Password = "My Password";
        // byte[] mybyte =Password.getBytes();
        // DESKeySpec myMaterial = new DESKeySpec(mybyte);
        // SecretKey myDESKey = MyKeyFactory.generateSecret(myMaterial);

        //Generating Key
        KeyGenerator Mygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = Mygenerator.generateKey();

        //initializing crypto algorithm
        Cipher myCipher = Cipher.getInstance("DES");

        //setting encryption mode
        myCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        byte[] myEncryptedBytes=myCipher.doFinal(myMessage);
        

        //setting decryption mode
        myCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        byte[] myDecryptedBytes=myCipher.doFinal(myEncryptedBytes);

        //print message in byte format
        //System.out.println(Arrays.toString(myEncryptedBytes));
        //System.out.println(Arrays.toString(myDecryptedBytes));

        String encrypteddata=new String(myEncryptedBytes);
        String decrypteddata=new String(myDecryptedBytes);

        System.out.println("Message : "+ message);
        System.out.println("Encrypted - "+ encrypteddata);
        System.out.println("Decrypted Message - "+ decrypteddata);
    }
}














/*
 * The code you provided is a Java program that demonstrates the encryption and decryption using the DES (Data Encryption Standard) algorithm. Here's a breakdown of what the code does:

The necessary Java classes for encryption and decryption are imported.

The DES class is defined, which contains the main method to execute the encryption and decryption operations.

Inside the main method, a string message is defined, which represents the message you want to encrypt.

The message string is converted to a byte array myMessage because the DES algorithm operates on bytes.

A key generator (KeyGenerator) is created with the algorithm set to "DES" to generate a secret key.

An instance of the Cipher class is created with the algorithm set to "DES".

The cipher is initialized in encryption mode (Cipher.ENCRYPT_MODE) using the generated secret key.

The doFinal method is called on the cipher with myMessage as the input, which performs the encryption and returns the encrypted byte array myEncryptedBytes.

The cipher is then initialized in decryption mode (Cipher.DECRYPT_MODE) using the same secret key.

The doFinal method is called again on the cipher with myEncryptedBytes as the input, which performs the decryption and returns the decrypted byte array myDecryptedBytes.

The encrypted byte array is converted to a string encrypteddata using the String constructor that takes a byte array as an argument.

The decrypted byte array is converted to a string decrypteddata using the same approach.

Finally, the original message, encrypted data, and decrypted data are printed to the console.

Please note that the DES algorithm is considered relatively weak and insecure for modern cryptographic purposes. It is recommended to use stronger encryption algorithms such as AES (Advanced Encryption Standard) for secure communication and data protection.
 */