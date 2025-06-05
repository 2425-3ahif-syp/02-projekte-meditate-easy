package med.easy.meditateeasy.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import med.easy.meditateeasy.database.DifficultyRepository;

public class Instruction {
    private IntegerProperty instructionId = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private IntegerProperty difficultyId = new SimpleIntegerProperty();

    private DifficultyRepository difficultyRepository;

    public Instruction() {
        difficultyRepository = new DifficultyRepository();
    }

    public Instruction(int instructionId, String title, String description, int difficultyId) {
        setInstructionId(instructionId);
        setTitle(title);
        setDescription(description);
        setDifficultyId(difficultyId);
        difficultyRepository = new DifficultyRepository();
    }

    // Getter and Setter

    public int getInstructionId() {
        return instructionId.get();
    }

    public IntegerProperty instructionIdProperty() {
        return instructionId;
    }

    public void setInstructionId(int instructionId) {
        this.instructionId.set(instructionId);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getDifficultyId() {
        return difficultyId.get();
    }

    public IntegerProperty difficultyIdProperty() {
        return difficultyId;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId.set(difficultyId);
    }

    public Difficulty getDifficulty() {
        return difficultyRepository.getDifficulty(getDifficultyId());
    }
    @Override
    public String toString() {
        return String.format("Übung %s | Schwierigkeitsgrad %s", this.getTitle(), difficultyRepository.getDifficulty(getDifficultyId()).toString());
    }
}
