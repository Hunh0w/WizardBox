package fr.hunh0w.wizardbox.internal.sql;

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
                "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,"+
                "email VARCHAR(50) UNIQUE,"+
                "pseudo VARCHAR(24) UNIQUE,"+
                "nom VARCHAR(20),"+
                "prenom VARCHAR(20),"+
                "password VARCHAR(150),"+
                "activity VARCHAR(50) DEFAULT NULL,"+
                "points INT DEFAULT 0,"+
                "rank INT DEFAULT 0,"+
                "ctf_flags BYTEA DEFAULT NULL,"+
                "chall_flags BYTEA DEFAULT NULL"+
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
