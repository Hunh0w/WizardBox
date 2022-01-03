package fr.hunh0w.wizardbox.server.protocol;

import java.io.Serializable;

public class WizardMessage implements Serializable {

    private final byte[] data;

    public WizardMessage(byte[] data){
        this.data = data;
    }

    public byte[] getData(){
        return this.data;
    }

}
