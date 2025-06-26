import java.sql.*;

public class CreateDB{
    public static Connection connect(){
        try{
            String url="jdbc:mysql://localhost:3306/librarydb?useSSL=false&serverTimezone=UTC";
            String user="root";
            String password= "Chuccuong3011@";
            return DriverManager.getConnection(url,user,password);

        }catch(SQLException e){
            System.out.println("❌ Ket noi that bai : "+e.getMessage());
            return null;
        }
    }
}





//import java.sql.*;
//
//public class CreateDB {
//    public static Connection connect() {//ham dung de giao tiep voi mySQL
//        try {
//            String url = "jdbc:mysql://localhost:3306/LibraryDB";
//            String user = "root";
//            String password = "your_password"; // thay bằng mật khẩu thật
//            return DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            System.out.println("❌ Kết nối thất bại: " + e.getMessage());
//            return null;
//        }
//    }
//}