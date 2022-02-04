package fr.hunh0w.wizardbox.ctf;

import fr.hunh0w.wizardbox.internal.objects.Rank;
import fr.hunh0w.wizardbox.utils.VarUtils;

public enum CTF {

    L1(1, 15, "Web 1", Rank.LYCEEN, "/CTF/L1", "Le but de ce CTF est de réussir à envoyer le formulaire avec un message", "{WbFl4gnv1lCyeN0}"),
    L2(2, 20, "Web 2", Rank.LYCEEN, "/CTF/L2", "Le but de ce CTF est de réussir à retrouver l'identifiant et le mot de passe", "{Fl4gINXjXZS27sn3502v1lCeN0}");

    private final int id;
    private final int winpoints;
    private final String name;
    private final Rank type;
    private final String path;
    private final String description;
    private final String flag;

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
