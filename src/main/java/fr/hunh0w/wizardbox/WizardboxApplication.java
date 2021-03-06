package fr.hunh0w.wizardbox;

import fr.hunh0w.wizardbox.internal.authentication.managers.AuthManager;
import fr.hunh0w.wizardbox.server.WizardBoxClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WizardboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WizardboxApplication.class, args);
        AuthManager.init();
        WizardBoxClient.start();

    }

}
