package med.easy.meditateeasy.database;

import med.easy.meditateeasy.model.Instruction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        // print the instruction list
        for (Instruction instruction : instructionList) {
            System.out.println(instruction.getInstructionId() + " " + instruction.getTitle() + " " + instruction.getDescription() + " " + instruction.getDifficultyId());
        }

        return instructionList;
    }
}
