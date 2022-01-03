package fr.hunh0w.wizardbox.server;

import fr.hunh0w.wizardbox.internal.authentication.crypto.CryptoManager;
import fr.hunh0w.wizardbox.server.objects.WizardSocket;
import fr.hunh0w.wizardbox.server.protocol.WizardMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class WizardBoxClient {

    private static WizardSocket wzsock = null;

    public static void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Socket sock = connect();
                    wzsock = new WizardSocket(sock);
                    while(true){
                        String message = getMessage(wzsock);
                        if(message != null){
                            System.out.println("[WB-Tunnel] MESSAGE: "+message);
                            continue;
                        }
                        break;
                    }
                    wzsock.close();
                }
            }
        }).start();

    }

    public static boolean sendMessage(String message){
        return send(wzsock, message);
    }

    public static Socket connect(){
        Socket sock = null;
        while(sock == null){
            try{ sock = new Socket("127.0.0.1", 3787);
            }catch(Exception ex){
                //System.out.println("[WB-Tunnel] Connexion échouée, reconnexion dans 3s...");
                try {Thread.sleep(3000);}
                catch (Exception e) {e.printStackTrace();}
            }
        }
        return sock;
    }


    /* SOCK METHODS */
    private static String getMessage(WizardSocket sock){
        try {
            ObjectInputStream os = new ObjectInputStream(sock.getSocket().getInputStream());
            Object packet = os.readObject();
            if(packet instanceof WizardMessage) {
                WizardMessage wzmess = (WizardMessage) packet;
                return new String(CryptoManager.decrypt(wzmess.getData()));
            }
            System.out.println("[WB-Tunnel] Objet inconnu envoyé par : "+sock.getIPv4());
            return null;
        }catch(Exception e) {
            return null;
        }
    }

    public static boolean send(WizardSocket sock, String message){
        try{
            WizardMessage wzmess = new WizardMessage(CryptoManager.encrypt(message.getBytes(StandardCharsets.UTF_8)));
            ObjectOutputStream oos_sock = new ObjectOutputStream(sock.getSocket().getOutputStream());
            oos_sock.writeObject(wzmess);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
