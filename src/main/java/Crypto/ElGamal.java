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

    private final static String CIPHERMODE = "ElGamal/None/NoPadding";

    /**
     * Encrypt a given message.
     * @param input : message to encrypt
     * @param pair : keypair used to encrypt
     * @return byte array of the ciphertext
     */
    public static byte[] encrypt(String input, KeyPair pair){
        try {

            SecureRandom random = new SecureRandom();
            Cipher cipher = Cipher.getInstance(CIPHERMODE, "BC");
            cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic(), random);
            byte[] ciphertext = cipher.doFinal(input.getBytes());
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
    public static byte[] decrypt(byte[] ciphertext, KeyPair pair){
        try {
            Cipher cipher = Cipher.getInstance(CIPHERMODE, "BC");
            cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
            byte[] plainText = cipher.doFinal(ciphertext);
            return plainText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
