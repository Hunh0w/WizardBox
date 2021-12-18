package fr.hunh0w.wizardbox.internal.objects;

public enum Rank {

    DEFAULT(0, "Débutant", "#C7C7C7", null),
    LYCEEN(0, "Lycéen", "#DBDBDB", null),
    RT1(1, "R&T 1", "#E36A6A", null),
    RT2(2, "R&T 2", "#D11717", null),
    LP(3, "Licence Pro", "#A50000", null),
    Professionnal(4, "Professionnel", "#7A00AF", null),
    Professeur(5, "Professeur", "#7A00AF", null);

    private int id;
    private String name;
    private String color;
    private String cssattr;

    Rank(int id, String name, String color, String cssattr){
        this.id = id;
        this.name = name;
        this.color = color;
        this.cssattr = cssattr;
    }

}
