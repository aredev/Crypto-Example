package Crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

/**
 * Example code for signature example, using ECDSA
 * Created by abdullah on 13/04/2017.
 */
public class ECDSA {

    /**
     * Constructor, creates a keypair and start the signature creation and verification.
     */
    public ECDSA() {

        KeyPair pair;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
            pair = kpg.generateKeyPair();
            byte[] signature = sign("Hallo", pair);
            verify(pair, signature, "Hallo1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Signs a message
     * @param plaintext : message to sign
     * @param pair : keypair used to sign
     * @return byte array of signature, null in case of failure
     */
    public byte[] sign(String plaintext, KeyPair pair){
        Signature ecdsaSign = null;
        try {
            ecdsaSign = Signature.getInstance("SHA3-512withECDSA", "BC");
            ecdsaSign.initSign(pair.getPrivate());
            ecdsaSign.update(plaintext.getBytes("UTF-8"));
            byte[] signature = ecdsaSign.sign();
            System.out.println(signature);
            return signature;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Verify a given signature
     * @param pair : keypair used for verification
     * @param signature : signature to verify
     * @param data : data (ie. original text) to use in verification, which can be used afterwards
     */
    public boolean verify(KeyPair pair, byte[] signature, String data){
        Signature ecdsaVerify = null;
        try {
            ecdsaVerify = Signature.getInstance("SHA3-512withECDSA", "BC");
            ecdsaVerify.initVerify(pair.getPublic());
            ecdsaVerify.update(data.getBytes("UTF-8"));
            boolean result = ecdsaVerify.verify(signature);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
