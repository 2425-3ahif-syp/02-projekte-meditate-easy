package med.easy.meditateeasy.database;

import org.h2.tools.Server;

import java.nio.file.Paths;

public class DBManager {

    private static Server h2Server;

    public static void startDatabase() {
        if (h2Server == null) {
            try {
                String dbDirectory = Paths.get("db").toAbsolutePath().toString();

                h2Server = Server.createTcpServer("-ifNotExists", "-baseDir", dbDirectory).start();
                System.out.println("H2 Database server started at: tcp://localhost:9092");

                Database.getInstance();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopDatabase() {
        if (h2Server != null) {
            h2Server.stop();
            System.out.println("H2 database server stopped.");
            h2Server = null;
        }
    }
}
