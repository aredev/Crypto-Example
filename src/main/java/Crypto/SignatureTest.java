package Crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;

/**
 * Created by abdullah on 13/04/2017.
 */
public class SignatureTest {

    public SignatureTest() {

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

    public void verify(KeyPair pair, byte[] signature, String data){
        Signature ecdsaVerify = null;
        try {
            ecdsaVerify = Signature.getInstance("SHA3-512withECDSA", "BC");
            ecdsaVerify.initVerify(pair.getPublic());
            ecdsaVerify.update(data.getBytes("UTF-8"));
            boolean result = ecdsaVerify.verify(signature);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
