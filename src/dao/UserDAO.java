package dao;
import java.sql.*;

import dao.Interfaces.UserCRUD;
import domain.LibraryUser;

import javax.swing.*;

public class UserDAO implements UserCRUD {

    private static Connection connection;

    // Constructor
    public UserDAO(Connection connection) {
        UserDAO.connection = connection;
    }

    @Override
    public boolean createUser(LibraryUser user) {
        String sql = "INSERT INTO library_user (user_name, user_email, user_password, staff_member) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUser_name());
            stmt.setString(2, user.getUser_email());
            stmt.setString(3, user.getUser_password());
            stmt.setBoolean(4, user.isStaff_member());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public LibraryUser getUserById(int id) {
        String sql = "SELECT * FROM library_user WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new LibraryUser(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getBoolean("staff_member")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean readUser() {
        String sql = "SELECT * FROM library_user";

        // try-with-resources
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            StringBuilder message = new StringBuilder("Library Users:\n");
            boolean hasUsers = false;

            while (rs.next()) {
                hasUsers = true;

                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                boolean staffMember = rs.getBoolean("staff_member");

                message.append("ID: ").append(userId)
                        .append(" - Name: ").append(userName)
                        .append(" - Email: ").append(userEmail)
                        .append(" - Staff: ").append(staffMember ? "Yes" : "No")
                        .append("\n");
            }

            // Show Users
            if (hasUsers) {
                JOptionPane.showMessageDialog(null, message.toString(), "Library Users", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "There are no users registered!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading users: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUser(LibraryUser user) {
        String sql = "UPDATE library_user SET user_name=?, user_email=?, user_password=?, staff_member=? WHERE user_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUser_name());
            stmt.setString(2, user.getUser_email());
            stmt.setString(3, user.getUser_password());
            stmt.setBoolean(4, user.isStaff_member());
            stmt.setInt(5, user.getUser_id());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM library_user WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    public LibraryUser getUserByCredentials(String email, String password) {
        String sql = "SELECT * FROM library_user WHERE user_email = ? AND user_password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new LibraryUser(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getBoolean("staff_member")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding user by email and password: " + e.getMessage());
        }
        return null;
    }
}


