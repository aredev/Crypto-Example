package Crypto;

import java.security.Security;

/**
 * Created by abdullah on 13/04/2017.
 */
public class MainTest {

    public static void main(String[] args) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        new SignatureTest();
        new ElGamalEncryptionTest();
    }

}
