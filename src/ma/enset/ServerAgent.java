package ma.enset;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        PrivateKey privateKey=(PrivateKey) getArguments()[0];
         addBehaviour(new CyclicBehaviour() {
             @Override
             public void action() {
                 ACLMessage recive=receive();
                 String CryptedEncodMessag = recive.getContent();
                 byte[] CryptedMessag= Base64.getDecoder().decode(CryptedEncodMessag.getBytes());


                 try {
                     Cipher cipher=Cipher.getInstance("RSA");
                     cipher.init(Cipher.DECRYPT_MODE,privateKey);
                     byte[] bytes= cipher.doFinal(CryptedMessag);
                     System.out.println(new String(bytes));

                 } catch (NoSuchAlgorithmException e) {
                     throw new RuntimeException(e);
                 } catch (NoSuchPaddingException e) {
                     throw new RuntimeException(e);
                 } catch (InvalidKeyException e) {
                     throw new RuntimeException(e);
                 } catch (IllegalBlockSizeException e) {
                     throw new RuntimeException(e);
                 } catch (BadPaddingException e) {
                     throw new RuntimeException(e);
                 }

             }
         });
    }
}
