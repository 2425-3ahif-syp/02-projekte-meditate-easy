package med.easy.meditateeasy.database;

import med.easy.meditateeasy.model.Difficulty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DifficultyRepository {

    private Connection connection;

    public DifficultyRepository() {
        connection = Database.getInstance().getConnection();
    }


    public Difficulty getDifficulty(int id) {
        String sql = "SELECT * FROM DIFFICULTY WHERE difficultyId = ?1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Difficulty(
                            rs.getInt("difficultyId"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
