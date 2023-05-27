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

class AES{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        
        //String we want to encrypt
        String  message="Hello, World!";
        byte[] myMessage =message.getBytes(); //string to byte array as DES works on bytes

        //If you want to use your own key
        // SecretKeyFactory MyKeyFactory = SecretKeyFactory.getInstance("DES");
        // String Password = "My Password";
        // byte[] mybyte =Password.getBytes();
        // DESKeySpec myMaterial = new DESKeySpec(mybyte);
        // SecretKey myDESKey = MyKeyFactory.generateSecret(myMaterial);

        //Generating Key
        KeyGenerator Mygenerator = KeyGenerator.getInstance("AES");
        SecretKey myDesKey = Mygenerator.generateKey();

        //initializing crypto algorithm
        Cipher myCipher = Cipher.getInstance("AES");

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
 This code demonstrates how to use the AES (Advanced Encryption Standard) algorithm for encrypting and decrypting data in Java. Here's a breakdown of the code:

The code imports various classes from the javax.crypto package that are required for AES encryption and decryption.

The AES class contains the main method, which is the entry point of the program.

The program starts by defining a plaintext message as a string, which is "This is Abhishek." The getBytes() method is then used to convert the string into a byte array, as AES operates on bytes.

A new AES key is generated using the KeyGenerator class. The getInstance("AES") method retrieves an instance of the AES key generator.

An instance of the Cipher class is created using Cipher.getInstance("AES") to initialize the AES cipher.

The encryption mode is set using the init() method with Cipher.ENCRYPT_MODE and the generated AES key.

The doFinal() method is used to perform the encryption operation on the input byte array, resulting in an encrypted byte array.

The decryption mode is set using the init() method with Cipher.DECRYPT_MODE and the same AES key.

The doFinal() method is again used, but this time on the encrypted byte array, to perform the decryption operation, resulting in a decrypted byte array.

The encrypted and decrypted byte arrays are converted back to strings using the new String() constructor.

Finally, the original message, the encrypted data, and the decrypted data are printed to the console.

Please note that AES encryption requires a secure key management process, and this code generates a new random key each time it is executed. If you want to use your own key, you can uncomment the lines of code related to the SecretKeyFactory and DESKeySpec and customize it according to your needs.
 */