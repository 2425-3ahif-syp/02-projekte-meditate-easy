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
                        "    description TEXT," +
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
                    "('Sonnengruß A', 'Beginne im Stand mit aufrechtem Rücken, die Füße hüftbreit auseinander und die Schultern entspannt. Atme tief durch die Nase ein und hebe dabei langsam die Arme über den Kopf, die Handflächen zeigen zueinander. Spüre, wie sich dein Brustkorb weitet und die Wirbelsäule sich streckt. Atme aus, beuge dich langsam und kontrolliert aus der Hüfte nach vorne, während du den Blick Richtung Knie lenkst. Lasse die Hände zum Boden oder zu den Schienbeinen sinken, je nach Beweglichkeit, und spüre die Dehnung entlang der Rückseite deiner Beine. Mit der nächsten Einatmung hebst du den Oberkörper leicht an in die halbe Vorbeuge, halte den Rücken lang und die Schultern entspannt. Atme aus und trete oder springe mit den Füßen zurück in die Planke, halte den Körper in einer geraden Linie von Kopf bis Fuß, der Bauch ist angespannt und stabilisiert die Haltung. Senke dich kontrolliert in Chaturanga hinunter, die Ellenbogen bleiben eng am Körper, halte die Schulterblätter stabil. Atme ein und rolle dich in den heraufschauenden Hund, drücke die Hände in die Matte, hebe die Brust und öffne die Vorderseite des Körpers, während die Beine lang und aktiv bleiben. Atme aus und schiebe dich zurück in den herabschauenden Hund, drücke die Hände fest in den Boden, die Fersen streben Richtung Matte und die Wirbelsäule wird lang. Halte diese Position für einige Atemzüge, spüre die Länge in der Wirbelsäule und die Kraft in Armen und Beinen. Gehe oder springe dann wieder zurück in den Anfangsstand und bereite dich auf die nächste Runde vor.', 1)," +
                    "('Krieger-Sequenz', 'Starte im herabschauenden Hund und bringe mit einer fließenden Bewegung den rechten Fuß zwischen die Hände. Positioniere das rechte Knie direkt über dem rechten Fußgelenk, um Stabilität zu gewährleisten. Richte dich langsam auf, strecke die Arme über den Kopf und komme in die Haltung des Krieger I. Spüre, wie dein Brustkorb sich hebt und deine Hüften nach vorne zeigen, während das hintere linke Bein gestreckt und fest im Boden verankert bleibt. Halte den Blick geradeaus oder leicht nach oben gerichtet, um die Haltung zu stabilisieren. Atme ruhig und gleichmäßig, während du die Kraft in deinen Beinen spürst und deinen Rumpf aktivierst. Nach einigen Atemzügen öffnest du die Hüften leicht und streckst die Arme seitlich aus, um in Krieger II zu kommen. Der Blick folgt der rechten Hand, die vorn ausgestreckt ist, während die linke Hand nach hinten zeigt. Spüre die Dehnung in der Hüfte und die Stabilität im Stand. Halte die Position 5 bis 10 tiefe Atemzüge lang und konzentriere dich auf das gleichmäßige Atmen, das deinen Geist beruhigt. Wiederhole die Sequenz auf der anderen Seite, um Balance und Kraft auf beiden Körperseiten zu entwickeln.', 2)," +
                    "('Savasana', 'Lege dich flach und bequem auf den Rücken auf deine Yogamatte, idealerweise mit einem kleinen Kissen unter dem Kopf, wenn das für dich angenehm ist. Breite die Beine locker aus, sodass die Füße nach außen fallen können, und lasse die Arme entspannt neben dem Körper liegen, die Handflächen zeigen nach oben. Schließe sanft die Augen und beginne, deinen Atem bewusst wahrzunehmen, ohne ihn aktiv zu beeinflussen. Erlaube dir, mit jedem Ausatmen mehr und mehr loszulassen – alle Muskelanspannungen im Körper, von den Zehen bis zum Scheitel, lösen sich langsam auf. Spüre, wie dein Körper schwer und entspannt auf der Matte liegt, während dein Geist immer ruhiger wird. Lasse Gedanken kommen und gehen, ohne dich an ihnen festzuhalten, wie Wolken, die am Himmel vorüberziehen. Verweile in diesem Zustand für mindestens fünf bis zehn Minuten, um deinem Nervensystem Zeit zur Regeneration zu geben und vollständige Entspannung zu ermöglichen. Savasana ist die wichtigste Haltung, um Körper, Geist und Seele in Einklang zu bringen und die wohltuende Wirkung der Yogapraxis zu integrieren.', 3)," +
                    "('Baumhaltung', 'Stelle dich aufrecht hin, die Füße hüftbreit auseinander und das Gewicht gleichmäßig auf beide Füße verteilt. Verlagere das Gewicht auf den linken Fuß, hebe den rechten Fuß und platziere die Fußsohle entweder an der Innenseite des linken Oberschenkels, der Wade oder – wenn möglich – am inneren Knie, aber niemals direkt darauf, um Verletzungen zu vermeiden. Bringe die Hände vor der Brust in Gebetshaltung zusammen und finde einen festen Blickpunkt, um dein Gleichgewicht zu stabilisieren. Atme tief und ruhig, während du die Länge in deiner Wirbelsäule spürst und die Muskulatur in deinem Standbein aktivierst. Spüre die Kraft und Stabilität, die aus der Verbindung zur Erde kommt, und gleichzeitig die Leichtigkeit in deinem gehobenen Bein. Halte die Position für mindestens 30 Sekunden bis zu einer Minute, bevor du die Seite wechselst. Diese Haltung stärkt das Gleichgewicht, fördert die Konzentration und schafft innere Ruhe.', 1)," +
                    "('Herabschauender Hund', 'Beginne auf Händen und Knien, die Handgelenke schulterbreit und die Knie hüftbreit auseinander. Spreize die Finger weit, drücke die Hände kräftig in den Boden und strecke langsam die Beine nach hinten aus, bis dein Körper eine umgekehrte V-Form bildet. Die Füße sind hüftbreit auseinander, die Fersen streben Richtung Boden, auch wenn sie den Boden nicht berühren. Strecke die Wirbelsäule lang und aktiviere die Bauchmuskeln, um den Rücken zu unterstützen. Lass den Kopf zwischen den Armen locker hängen und halte den Blick zu deinen Füßen gerichtet. Atme tief ein und aus, spüre, wie sich deine Rückseite sanft dehnt, besonders die Waden, Oberschenkelrückseite und die Wirbelsäule. Halte diese Position für 5 bis 10 Atemzüge, um Kraft in Armen und Schultern aufzubauen und Verspannungen im Rücken zu lösen.', 1)," +
                    "('Brücke (Setu Bandhasana)', 'Lege dich auf den Rücken, die Knie angewinkelt und die Füße hüftbreit aufgestellt, nah am Gesäß. Die Arme liegen entspannt seitlich neben dem Körper, die Handflächen zeigen nach unten. Atme tief ein und hebe beim Ausatmen langsam das Becken an, indem du die Füße und Schultern in den Boden drückst. Rolle die Wirbelsäule sanft Wirbel für Wirbel vom Boden ab, bis das Becken maximal angehoben ist. Halte die Schultern entspannt und lasse den Nacken lang. Aktiviere die Oberschenkel- und Gesäßmuskulatur, um die Haltung stabil zu halten, und öffne die Brust, indem du die Arme auf dem Boden streckst. Atme tief und ruhig weiter und halte die Brücke für 30 Sekunden bis zu einer Minute. Diese Haltung stärkt die Rückenmuskulatur, öffnet die Brust und fördert die Durchblutung.', 2)," +
                    "('Drehsitz (Ardha Matsyendrasana)', 'Setze dich mit ausgestreckten Beinen auf die Matte. Beuge das rechte Knie und stelle den rechten Fuß neben das linke Knie, das linke Bein bleibt ausgestreckt oder du beugst es ebenfalls, je nach Komfort. Drehe deinen Oberkörper nach rechts, setze die linke Hand neben das linke Knie und bringe die rechte Hand hinter dich auf den Boden. Ziehe die Wirbelsäule lang und drehe dich tief, ohne die Schultern hochzuziehen. Halte den Kopf in Verlängerung der Wirbelsäule, der Blick folgt der Drehung. Atme tief ein und aus und vertiefe die Drehung bei jedem Ausatmen leicht, ohne zu erzwingen. Bleibe in dieser Haltung für mindestens 30 Sekunden, spüre die Öffnung in Rücken und Brust und die Massage der inneren Organe. Wechsle die Seite, um die Beweglichkeit beider Körperhälften zu fördern.', 2)," +
                    "('Kindhaltung (Balasana)', 'Knie dich auf deine Matte, die großen Zehen berühren sich und die Knie sind hüftbreit geöffnet. Atme aus und senke den Oberkörper sanft nach vorne, bis die Stirn den Boden berührt oder auf einem Kissen ruht. Die Arme können nach vorne ausgestreckt oder entspannt entlang des Körpers liegen. Schließe die Augen und lass alle Muskelspannung los, spüre das sanfte Nachgeben deines Körpers. Atme tief und gleichmäßig, während sich die Hüften nach hinten Richtung Fersen bewegen und der Rücken sich entspannt. Diese Haltung ist besonders wohltuend, um den Rücken zu dehnen, Stress abzubauen und die Atmung zu vertiefen. Verweile so lange wie nötig, mindestens 1-2 Minuten, um vollkommene Ruhe und Regeneration zu fördern.', 1)," +
                    "('Planke', 'Begib dich in die Liegestützposition, die Hände unter den Schultern, die Beine gestreckt und die Füße hüftbreit auseinander. Der Körper bildet eine gerade Linie von Kopf bis Fuß, der Bauch ist fest angespannt und zieht den Bauchnabel Richtung Wirbelsäule, um den Rücken zu stabilisieren. Vermeide, dass die Hüften durchhängen oder nach oben steigen, halte die Position aktiv. Atme ruhig und kontrolliert weiter, während du die Muskulatur in Armen, Schultern, Bauch und Beinen spürst. Halte die Planke zunächst für 20 bis 30 Sekunden und steigere die Dauer mit zunehmender Kraft und Ausdauer. Diese Haltung stärkt den gesamten Körper, besonders den Rumpf, und verbessert die Haltung.', 3)," +
                    "('Katzen-Kuh-Übung', 'Beginne im Vierfüßlerstand, die Hände direkt unter den Schultern, die Knie unter den Hüften. Atme tief ein und lasse beim Einatmen den Bauch sinken, den Blick hebe leicht nach vorne, während du das Steißbein nach oben streckst – das ist die Kuh-Position. Beim Ausatmen ziehe den Bauchnabel zur Wirbelsäule, runde den Rücken nach oben, senke den Kopf zwischen die Arme – die Katzen-Position. Wiederhole diesen fließenden Wechsel mehrere Male in deinem eigenen Atemrhythmus, spüre die Mobilisierung deiner Wirbelsäule und die Dehnung im Rücken und Bauch. Diese Übung lockert die Wirbelsäule, regt die Durchblutung an und hilft Verspannungen zu lösen. Sie ist ideal als sanfter Start in jede Yogapraxis oder als Entspannung zwischendurch.', 1)");

            statement.execute("INSERT INTO video (title, link, difficultyId) VALUES" +
                    "('Yoga Flow für den Morgen', 'https://www.youtube.com/embed/_VFRpeEQQxM', 1)," +
                    "('Abendroutine zur Entspannung', 'https://www.youtube.com/embed/ockCQMt9kM0', 1)," +
                    "('15 Minuten Ganzkörper Yoga', 'https://www.youtube.com/embed/4Z1RPavOX3s', 2)," +
                    "('Geführte Meditation für Achtsamkeit', 'https://www.youtube.com/embed/zEDulC3nPsY', 2)," +
                    "('Yin Yoga für tiefe Entspannung', 'https://www.youtube.com/embed/mn-KTgQnYg0', 3)," +
                    "('Morgenmeditation mit Mira', 'https://www.youtube.com/embed/Eu7KIichpW0', 1)," +
                    "('Innere Ruhe finden', 'https://www.youtube.com/embed/jmvR2dyR8qA', 2)," +
                    "('Buddhas Lehre - Meditation', 'https://www.youtube.com/embed/Sz7iFjI40f4', 3)," +
                    "('Tiefenentspannung mit Anika', 'https://www.youtube.com/embed/khYkjOoNN98', 2)," +
                    "('Motivationsvideo Christian Bischoff', 'https://www.youtube.com/embed/b2ij_tskb4Q', 1)," +
                    "('Geführte Meditation Teil 2', 'https://www.youtube.com/embed/UPVgyMr_-D0', 2)," +
                    "('Innere Balance Yoga', 'https://www.youtube.com/embed/vUR-UA3ryP4', 3)," +
                    "('Abendmeditation mit Anika', 'https://www.youtube.com/embed/mAI2xU3Ulvo', 1)," +
                    "('Chakra Healing Musik', 'https://www.youtube.com/embed/wvYM1fZcLA4', 2)");


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
