package med.easy.meditateeasy.database;

import med.easy.meditateeasy.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdminRepository {

    private Connection connection;

    public AdminRepository() {
        connection = Database.getInstance().getConnection();
    }

    public Admin findByUsername(String username) {
        String sql = "SELECT id, username, password_hash FROM ADMIN WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password_hash")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean verifyUser(String username, String passwordPlain) {
        Admin user = findByUsername(username);
        if (user == null) {
            return false;
        }
        String hashedInput = hashPassword(passwordPlain);
        return hashedInput.equals(user.getPasswordHash());
    }


    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 Algorithm not found", e);
        }
    }

    public boolean saveAdmin(String username, String passwordPlain) {
        String sql = "INSERT INTO ADMIN (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, hashPassword(passwordPlain));
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
