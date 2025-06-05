package med.easy.meditateeasy.database;

import med.easy.meditateeasy.model.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoRepository {
    private final Connection connection;

    public VideoRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Video> getAllVideos() {
        List<Video> videoList = new ArrayList<>();
        String sql = "SELECT * FROM video";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                videoList.add(new Video(
                        rs.getInt("videoId"),
                        rs.getString("title"),
                        rs.getString("link"),
                        rs.getInt("difficultyId")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videoList;
    }
}
