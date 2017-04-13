package Crypto;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * Created by abdullah on 13/04/2017.
 */
public class KeyGeneration {

    /**
     * Generate a keypair
     * @return the keypair
     */
    public static KeyPair generateECDSAKeys(){
        KeyPair pair;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
            pair = kpg.generateKeyPair();
            return pair;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generate a keypair
     * @param keysize : size of the keys
     * @return the keypair
     */
    public static KeyPair generateElGamalKeys(int keysize){
        try {
            SecureRandom random = new SecureRandom();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ElGamal", "BC");
            //Important: for this to work, you may need to download http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
            //Copy paste the content to /jdk/jre/lib/security and overwrite the current (0 kB) files.
            kpg.initialize(keysize, random);

            KeyPair pair = kpg.generateKeyPair();
            return pair;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
