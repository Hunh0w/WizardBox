package fr.hunh0w.wizardbox.internal.objects;

public enum Rank {

    DEFAULT(0, "Débutant", "#C7C7C7", null),
    LYCEEN(1, "Lycéen", "#DBDBDB", null),
    RT1(2, "R&T 1", "#E36A6A", null),
    RT2(3, "R&T 2", "#D11717", null),
    LP(4, "Licence Pro", "#A50000", null),
    Professionnal(5, "Professionnel", "#7A00AF", null),
    Professeur(6, "Professeur", "#7A00AF", null),
    ADMIN(100, "Admin", "#810000", "font-weight: bold; text-decoration: underline;");

    private final int id;
    private final String name;
    private final String color;
    private final String cssattr;

    Rank(int id, String name, String color, String cssattr){
        this.id = id;
        this.name = name;
        this.color = color;
        this.cssattr = cssattr;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getCssattr() {
        return cssattr;
    }

    public static Rank getRank(int id){
        for(Rank r : Rank.values()){
            if(r.getId() == id) return r;
        }
        return null;
    }

}
