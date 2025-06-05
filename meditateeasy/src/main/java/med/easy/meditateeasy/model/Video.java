package med.easy.meditateeasy.model;

import javafx.beans.property.*;
import med.easy.meditateeasy.database.DifficultyRepository;

public class Video {
    private final IntegerProperty videoId = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty link = new SimpleStringProperty();
    private final IntegerProperty difficultyId = new SimpleIntegerProperty();

    private final DifficultyRepository difficultyRepository = new DifficultyRepository();

    public Video(int videoId, String title, String link, int difficultyId) {
        setVideoId(videoId);
        setTitle(title);
        setLink(link);
        setDifficultyId(difficultyId);
    }

    public int getVideoId() {
        return videoId.get();
    }

    public void setVideoId(int videoId) {
        this.videoId.set(videoId);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getLink() {
        return link.get();
    }

    public void setLink(String link) {
        this.link.set(link);
    }

    public int getDifficultyId() {
        return difficultyId.get();
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId.set(difficultyId);
    }

    public Difficulty getDifficulty() {
        return difficultyRepository.getDifficulty(getDifficultyId());
    }

    @Override
    public String toString() {
        return String.format("%s (Schwierigkeit: %s)", getTitle(), getDifficulty());
    }
}
