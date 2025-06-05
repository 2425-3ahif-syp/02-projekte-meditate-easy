package med.easy.meditateeasy.database;

import java.sql.*;

public class Database {

    private static Database instance;

    private static final String URL = "jdbc:postgresql://localhost:5432/meditateEasyDb";
    private static final String USERNAME = "app";
    private static final String PASSWORD = "app";

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
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
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
                "CREATE TABLE IF NOT EXISTS difficulty (" +
                        "    difficultyId SERIAL PRIMARY KEY," +
                        "    description VARCHAR(255)" +
                        ");",

                "CREATE TABLE IF NOT EXISTS instruction (" +
                        "    instructionId SERIAL PRIMARY KEY," +
                        "    title VARCHAR(255)," +
                        "    description VARCHAR(255)," +
                        "    difficultyId INT REFERENCES difficulty(difficultyId) ON DELETE CASCADE" +
                        ");",

                "CREATE TABLE IF NOT EXISTS video (" +
                        "    videoId SERIAL PRIMARY KEY," +
                        "    title VARCHAR(255)," +
                        "    link VARCHAR(255)," +
                        "    difficultyId INT REFERENCES difficulty(difficultyId) ON DELETE CASCADE" +
                        ");",

                "CREATE TABLE IF NOT EXISTS admin (" +
                        "    id SERIAL PRIMARY KEY," +
                        "    username VARCHAR(50) NOT NULL UNIQUE," +
                        "    password_hash VARCHAR(255) NOT NULL" +
                        ");"
        };

        try (Statement statement = connection.createStatement()) {
            for (String stmt : createStmts) {
                statement.execute(stmt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            insertTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTestData() {
        try (Statement statement = connection.createStatement()) {
            if (!isTableEmpty("difficulty")) {
                return;
            }
            statement.execute("INSERT INTO difficulty (description) VALUES ('Anfänger')");
            statement.execute("INSERT INTO difficulty (description) VALUES ('Fortgeschritten')");
            statement.execute("INSERT INTO difficulty (description) VALUES ('Profi')");

            statement.execute("INSERT INTO instruction (title, description, difficultyId) VALUES" +
                    "('Sonnengruß A', 'Beginne im Stand, atme ein und hebe die Arme. Atme aus, beuge dich nach vorne. Folge dem Flow durch Planke, Chaturanga und herabschauenden Hund.', 1)," +
                    "('Krieger-Sequenz', 'Starte im herabschauenden Hund, bring den rechten Fuß nach vorne, richte dich auf in Krieger I. Drehe dann in Krieger II. Atme ruhig und gleichmäßig.', 2)," +
                    "('Savasana', 'Lege dich auf den Rücken, schließe die Augen und entspanne den ganzen Körper. Lasse los und bleibe mindestens 5 Minuten liegen.', 3)");

            statement.execute("INSERT INTO video (title, link, difficultyId) VALUES" +
                    "('Yoga Flow für den Morgen', 'https://www.youtube.com/embed/_VFRpeEQQxM', 1)," +
                    "('Abendroutine zur Entspannung', 'https://www.youtube.com/embed/ockCQMt9kM0', 1)," +
                    "('15 Minuten Ganzkörper Yoga', 'https://www.youtube.com/embed/4Z1RPavOX3s', 2)," +
                    "('Geführte Meditation für Achtsamkeit', 'https://www.youtube.com/embed/zEDulC3nPsY', 2)," +
                    "('Yin Yoga für tiefe Entspannung', 'https://www.youtube.com/embed/mn-KTgQnYg0', 3)");

            statement.execute("INSERT INTO admin (username, password_hash) VALUES" +
                    "('admin', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Verbindung geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isTableEmpty(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() && rs.getInt(1) == 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
