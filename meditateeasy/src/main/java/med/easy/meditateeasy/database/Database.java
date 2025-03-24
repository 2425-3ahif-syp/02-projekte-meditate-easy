package med.easy.meditateeasy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Database instance;

    private static final String URL = "jdbc:h2:tcp://localhost:9092/./meditateeasyDB";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    private static Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if(instance == null) {
            synchronized (Database.class) {
                if(instance == null)
                {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void initialize() throws SQLException {
        String[] createStmts = new String[]{
                "CREATE TABLE difficulty (" +
                        "    difficultyId INT PRIMARY KEY AUTO_INCREMENT," +
                        "    description VARCHAR(255)" +
                        ");",

                "CREATE TABLE instruction (" +
                        "    title VARCHAR(255) PRIMARY KEY," +
                        "    description VARCHAR(255)," +
                        "    difficultyId INT," +
                        "    FOREIGN KEY (difficultyId) REFERENCES Difficulty(difficultyId) ON DELETE CASCADE" +
                        ");",

                "CREATE TABLE video (" +
                        "    title VARCHAR(255) PRIMARY KEY," +
                        "    link VARCHAR(255)," +
                        "    difficultyId INT," +
                        "    FOREIGN KEY (difficultyId) REFERENCES Difficulty(difficultyId) ON DELETE CASCADE" +
                        ");"
        };

        try (var statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS video");
            statement.execute("DROP TABLE IF EXISTS instruction");
            statement.execute("DROP TABLE IF EXISTS difficulty");

            for (String stmt : createStmts) {
                statement.execute(stmt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
                System.out.println("Datenbankverbindung geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
