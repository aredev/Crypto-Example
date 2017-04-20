package Crypto;

import java.security.KeyPair;
import java.security.Signature;

/**
 * Example code for signature example, using ECDSA
 * Created by abdullah on 13/04/2017.
 */
public class ECDSA {

    /**
     * Signs a message
     * @param plaintext : message to sign
     * @param pair : keypair used to sign
     * @return byte array of signature, null in case of failure
     */
    public static byte[] sign(String plaintext, KeyPair pair){
        Signature ecdsaSign = null;
        try {
            ecdsaSign = Signature.getInstance("SHA3-512withECDSA", "BC");
            ecdsaSign.initSign(pair.getPrivate());
            ecdsaSign.update(plaintext.getBytes("UTF-8"));
            return ecdsaSign.sign();
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
    public static boolean verify(KeyPair pair, byte[] signature, byte[] data){
        Signature ecdsaVerify = null;
        try {
            ecdsaVerify = Signature.getInstance("SHA3-512withECDSA", "BC");
            ecdsaVerify.initVerify(pair.getPublic());
            ecdsaVerify.update(data);
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
