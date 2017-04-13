package Tests;

import Crypto.ECDSA;
import Crypto.ElGamal;
import Crypto.KeyGeneration;

import java.security.KeyPair;

/**
 * Created by abdullah on 13/04/2017.
 */
public class Test {

    public Test() {
        //El Gamal Encryption Test

        KeyPair pair = KeyGeneration.generateElGamalKeys(256);
        String input = "Hallo";
        byte[] cipher = ElGamal.encrypt(input, pair);
        byte[] orig = ElGamal.decrypt(cipher, pair);

        System.out.println(input.equals(new String(orig)));

        //ECDSA Signing test

        KeyPair signingPair = KeyGeneration.generateECDSAKeys();
        byte[] signature = ECDSA.sign(input, signingPair);
        boolean correct = ECDSA.verify(signingPair, signature, input);
        System.out.println(correct);


    }
}
