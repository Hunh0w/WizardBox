package fr.hunh0w.wizardbox.internal.authentication.sql;

public enum Database {

    WIZARDBOX(new DatabaseCredentials("127.0.0.1", "WizardBox", 5432, "wizardbox", "WIZARDBOX6769"));

    private final HikariDatabase database;

    Database(DatabaseCredentials credentials){
        database = new HikariDatabase(credentials);
    }

    public HikariDatabase getDatabase() {
        return this.database;
    }

    public static void initAll() {
        String users = "CREATE TABLE IF NOT EXISTS users ("+
                "id INT GENERATED ALWAYS AS IDENTITY,"+
                "pseudo VARCHAR(30) UNIQUE, "+
                "nom VARCHAR(20),"+
                "prenom VARCHAR(20), "+
                "password VARCHAR(200)"+
                ")";
        WIZARDBOX.getDatabase().getTablesBuilder().addQuery(users);
        for(Database db : Database.values()) {
            db.getDatabase().init();
        }
    }

    public static void closeAll() {
        for(Database db : Database.values()) {
            db.getDatabase().destroy();
        }
    }

}
