package med.easy.meditateeasy.database;

import med.easy.meditateeasy.model.Difficulty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DifficultyRepository {

    private Connection connection;

    public DifficultyRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Difficulty> getAllDifficulties() {
        List<Difficulty> list = new ArrayList<>();
        String sql = "SELECT * FROM DIFFICULTY";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Difficulty(rs.getInt("difficultyId"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Difficulty getDifficulty(int id) {
        String sql = "SELECT * FROM DIFFICULTY WHERE difficultyId = ?";

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

    public boolean insertDifficulty(String description) {
        String sql = "INSERT INTO DIFFICULTY (description) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, description);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
