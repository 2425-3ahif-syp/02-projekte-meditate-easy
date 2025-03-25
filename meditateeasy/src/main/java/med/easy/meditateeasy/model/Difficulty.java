package med.easy.meditateeasy.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Difficulty {
    private IntegerProperty difficultyId = new SimpleIntegerProperty();
    private StringProperty description = new SimpleStringProperty();

    public Difficulty() {

    }

    public Difficulty(int difficultyId, String description) {
        setDifficultyId(difficultyId);
        setDescription(description);
    }

    // Getter and Setter


    public int getDifficultyId() {
        return difficultyId.get();
    }

    public IntegerProperty difficultyIdProperty() {
        return difficultyId;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId.set(difficultyId);
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

    @Override
    public String toString() {
        return String.format("%s", this.getDescription());
    }
}
