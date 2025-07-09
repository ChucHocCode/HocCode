package CreateDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {
    public CreateDB() {
    }

    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/QL?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "Chuccuong3011@";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("❌ Ket noi that bai : " + e.getMessage());
            return null;
        }
    }
}
