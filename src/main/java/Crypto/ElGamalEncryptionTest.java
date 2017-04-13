package Crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.*;

/**
 * Created by abdullah on 13/04/2017.
 */
public class ElGamalEncryptionTest {

    private Cipher cipher;
    private SecureRandom random;

    public ElGamalEncryptionTest() {
        try {
            random = new SecureRandom();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ElGamal", "BC");
            //Important: for this to work, you may need to download http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
            //Copy paste the content to /jdk/jre/lib/security and overwrite the current (0 kB) files.
            kpg.initialize(256, random);

            KeyPair pair = kpg.generateKeyPair();
            cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
            String input = "je moeder is een hoer.";
            byte[] c = encrypt(input, pair);
            System.out.println("Length of ciphertext " + c.length);
            String i = decrypt(c, pair);
            System.out.println(input.equals(i));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public byte[] encrypt(String input, KeyPair pair){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic(), random);
            byte[] ciphertext = cipher.doFinal(input.getBytes());
            System.out.println("Encryption of " + input + " is " + new String(ciphertext));
            return ciphertext;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String decrypt(byte[] ciphertext, KeyPair pair){
        try {
            cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
            byte[] plainText = cipher.doFinal(ciphertext);
            System.out.println("Decrypted text is " + new String(plainText));
            return new String(plainText);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
