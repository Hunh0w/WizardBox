package fr.hunh0w.wizardbox.ctf;

import fr.hunh0w.wizardbox.internal.objects.Rank;
import fr.hunh0w.wizardbox.utils.VarUtils;

public enum CTF {

    L1(1, 15, "Web 1", Rank.LYCEEN, "/CTF/L1", "Le but de ce CTF est de réussir à envoyer le formulaire avec un message", "WbFl4gnv1lCyeN0");

    private int id;
    private int winpoints;
    private String name;
    private Rank type;
    private String path;
    private String description;
    private String flag;

    CTF(int id, int winpoints, String name, Rank type, String path, String description, String flag) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.description = description;
        this.flag = flag;
        this.id = id;
        this.winpoints = winpoints;
    }

    public String getName() {
        return name;
    }

    public Rank getType() {
        return type;
    }

    public String getPath() {
        return path;
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
                "\"path\": \""+VarUtils.toBase64(path)+"\","+
                "\"winpoints\": \""+VarUtils.toBase64(winpoints+"")+"\","+
                "\"description\": \""+VarUtils.toBase64(description)+"\""+
                "}";
    }
}
