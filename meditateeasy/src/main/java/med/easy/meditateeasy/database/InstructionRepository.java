package med.easy.meditateeasy.database;

import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Instruction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructionRepository {
    private Connection connection;

    public InstructionRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Instruction> getAllInstructions() {
        List<Instruction> instructionList = new ArrayList<>();
        String sql = "SELECT * FROM instruction";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)
        ) {
            while (rs.next()) {
                instructionList.add(new Instruction(
                                rs.getInt("instructionId"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getInt("difficultyId")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructionList;
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
}