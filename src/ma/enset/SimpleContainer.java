package ma.enset;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SimpleContainer {
    public static void main(String[] args) throws ControllerException, NoSuchAlgorithmException {
        Runtime runtime= Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(Profile.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profileImpl);
        KeyPair keyPair=CryptographyUtiles.generateRSAKeys();
        PrivateKey privateKey= keyPair.getPrivate();
        PublicKey publicKey= keyPair.getPublic();
        container.start();
        AgentController agentController=container.createNewAgent("clientAgent","ma.enset.ClientAgent",new Object[]{publicKey});
        AgentController agentController1=container.createNewAgent("serverAgent","ma.enset.ServerAgent",new Object[]{privateKey});
        agentController.start();
        agentController1.start();

    }
}
