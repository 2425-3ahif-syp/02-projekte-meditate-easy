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
        String sql = "SELECT difficultyid, description FROM DIFFICULTY ORDER BY difficultyid";

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
        String sql = "SELECT difficultyid, description FROM DIFFICULTY WHERE difficultyId = ?";

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

    public boolean addDifficulty(Difficulty difficulty) {
        String sql = "INSERT INTO difficulty (description) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, difficulty.getDescription());
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateDifficulty(Difficulty difficulty) {
        String sql = "UPDATE difficulty SET description = ? WHERE difficultyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, difficulty.getDescription());
            stmt.setInt(2, difficulty.getDifficultyId());
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDifficulty(int difficultyId) {
        String sql = "DELETE FROM difficulty WHERE difficultyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, difficultyId);
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

