public class UserDao {

    // Altri metodi

    public UserBean doRetrieveByUsername(String username) throws SQLException {
        UserBean user = null;
        String selectSQL = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user = new UserBean();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password")); // Password criptata
                    // Imposta altri attributi dell'utente se necessario
                }
            }
        }
        return user;
    }
}
