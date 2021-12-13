package fr.hunh0w.wizardbox.internal.objects;

public enum Category {

    CRYPTO(1, "Cryptologie"),
    NETWORK(2, "Réseau"),
    SYSTEM_APP(3, "App Système"),
    PROGRAMMATION(4, "Programmation"),
    REVERSE_ENGINEERING(5, "Ingénierie Inverse");

    private int id;
    private String name;

    Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
