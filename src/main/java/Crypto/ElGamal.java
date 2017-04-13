package Crypto;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * Example code for encryption using ElGamal
 * Created by abdullah on 13/04/2017.
 */
public class ElGamal {

    private Cipher cipher;
    private SecureRandom random;

    /**
     * Sets up the system, generates a keypair and starts the encryption/decryption functions. s
     */
    public ElGamal() {
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
            String i = new String(decrypt(c, pair));
            System.out.println(input.equals(i));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Encrypt a given message.
     * @param input : message to encrypt
     * @param pair : keypair used to encrypt
     * @return byte array of the ciphertext
     */
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

    /**
     * Function to decrypt the ciphertext
     * @param ciphertext : to decrypt
     * @param pair : keypair used to decrypt a message
     * @return byte array of the decrypted ciphertexts
     */
    public byte[] decrypt(byte[] ciphertext, KeyPair pair){
        try {
            cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
            byte[] plainText = cipher.doFinal(ciphertext);
            System.out.println("Decrypted text is " + new String(plainText));
            return plainText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
