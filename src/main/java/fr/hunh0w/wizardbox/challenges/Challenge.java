package fr.hunh0w.wizardbox.challenges;

import fr.hunh0w.wizardbox.internal.objects.Rank;
import fr.hunh0w.wizardbox.utils.VarUtils;

public enum Challenge {

    L1(1, 5, "Tas de données 1", Rank.LYCEEN, "TDD1.txt", "Le but de ce Challenge est de réussir à trouver le flag dans ce gros fichier rempli de données !", "{L3Fl4gD3HunH0w}");

    private final int id;
    private final int winpoints;
    private final String name;
    private final Rank type;
    private final String filename;
    private final String description;
    private final String flag;

    Challenge(int id, int winpoints, String name, Rank type, String filename, String description, String flag) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.flag = flag;
        this.id = id;
        this.filename = filename;
        this.winpoints = winpoints;
    }

    public String getName() {
        return name;
    }

    public Rank getType() {
        return type;
    }

    public String getPath() {
        return "/challenges/download/"+id;
    }

    public String getFilename(){
        return filename;
    }

    public String getDescription() {
        return description;
    }

    public String getFlag() {
        return flag;
    }

    public int getId(){
        return this.id;
    }

    public int getWinpoints(){
        return winpoints;
    }

    public String toJson(){
        return "{"+
                "\"id\": \""+ VarUtils.toBase64(id+"")+"\","+
                "\"name\": \""+VarUtils.toBase64(name)+"\","+
                "\"type\": \""+VarUtils.toBase64(type.getName())+"\","+
                "\"path\": \""+VarUtils.toBase64(getPath())+"\","+
                "\"winpoints\": \""+VarUtils.toBase64(winpoints+"")+"\","+
                "\"description\": \""+VarUtils.toBase64(description)+"\""+
                "}";
    }

}
