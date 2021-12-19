package fr.hunh0w.wizardbox.internal.sql;

public class DatabaseCredentials {

    private final String host;
    private final String database;
    private final int port;
    private final String user;
    private final String password;

    public DatabaseCredentials(String host, String database, int port, String user, String password) {
        this.host = host;
        this.database = database;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public String toURI() {
        return "jdbc:mysql://"+host+":"+port+"/"+database;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
