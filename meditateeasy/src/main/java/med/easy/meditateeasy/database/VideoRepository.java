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
        String sql = "SELECT videoid, title, link, difficultyid FROM video order by videoid";

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

    public boolean addVideo(Video video) {
        String sql = "INSERT INTO video (title, link, difficultyId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getLink());
            stmt.setInt(3, video.getDifficultyId());
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVideo(Video video) {
        String sql = "UPDATE video SET title = ?, link = ?, difficultyId = ? WHERE videoId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getLink());
            stmt.setInt(3, video.getDifficultyId());
            stmt.setInt(4, video.getVideoId());
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVideo(int videoId) {
        String sql = "DELETE FROM video WHERE videoId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, videoId);
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
