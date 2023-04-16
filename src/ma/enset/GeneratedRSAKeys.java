package ma.enset;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GeneratedRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair=CryptographyUtiles.generateRSAKeys();
        PrivateKey privateKey=keyPair.getPrivate();
        PublicKey publicKey=keyPair.getPublic();
        String encodedPk = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodedPbK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("=============Private Key=============");
        System.out.println(encodedPk);
        System.out.println("=============Public Key=============");
        System.out.println(encodedPbK);

    }
}
