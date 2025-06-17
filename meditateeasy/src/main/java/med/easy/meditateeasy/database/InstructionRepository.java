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
        String sql = "SELECT instructionid, title, description, difficultyid FROM instruction order by instructionid";

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

    public boolean addInstruction(Instruction instruction) {
        String sql = "INSERT INTO instruction (title, description, difficultyId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instruction.getTitle());
            stmt.setString(2, instruction.getDescription());
            stmt.setInt(3, instruction.getDifficultyId());
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInstruction(Instruction instruction) {
        String sql = "UPDATE instruction SET title = ?, description = ?, difficultyId = ? WHERE instructionId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instruction.getTitle());
            stmt.setString(2, instruction.getDescription());
            stmt.setInt(3, instruction.getDifficultyId());
            stmt.setInt(4, instruction.getInstructionId());
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteInstruction(int instructionId) {
        String sql = "DELETE FROM instruction WHERE instructionId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, instructionId);
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}