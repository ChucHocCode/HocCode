import java.sql.*;
import java.text.Normalizer;
import java.util.*;
import java.sql.Date;



public class Library {
    private Connection cn;
    public Library(){
        cn=CreateDB.connect();
    }
    //1.ham them sach
    public void addBooks(Books b) throws SQLException{
        String sql="INSERT INTO books (bookCode,bookName,authorName,publish,category,quantity)VALUES(?,?,?,?,?,?)";
        PreparedStatement ps= cn.prepareStatement(sql);
        ps.setInt(1,b.getBookCode());
        ps.setString(2,b.getBookName());
        ps.setString(3, b.getAuthorName());
        ps.setInt(4,b.getPublish());
        ps.setString(5,b.getCategory());
        ps.setInt(6,b.getQuantity());
        ps.executeUpdate();
        System.out.println("Them danh sach thanh cong!");
    }

    //2.Ham them nguoi đọc
    public void addReaders(Readers r) throws SQLException{
        String sql="INSERT INTO READERS(readerId,fullName,dob,gender,address) VALUES(?,?,?,?,?)";
        PreparedStatement ps=cn.prepareStatement(sql);
        ps.setInt(1,r.getReaderId());
        ps.setString(2,r.getFullName());
        ps.setString(3, r.getDob());
        ps.setString(4, r.getGender());
        ps.setString(5,r.getAddress());
        ps.executeUpdate();
        System.out.println("Da them nguoi doc thanh cong !");
    }

    //3.ham muon sách
    public void borrowBook(int readerId, int bookCode) throws SQLException{
        //truy van va gan tham so
        String checkSql="SELECT quantity FROM books WHERE bookCode = ? ";
        PreparedStatement check=cn.prepareStatement(checkSql);
        check.setInt(1,bookCode);//gan gia tri bookcode vao dau hoi cham o vtri thu 1
        ResultSet rs=check.executeQuery();
        //chay cau truy van va lay ket qua tra ve duoi dang ResultSet

        if(!rs.next()){
            System.out.println("Khong tim thay sach co ma : "+bookCode);
            return;
        }

        int quantity=rs.getInt("quantity");
        //2.Ktra so luong sach con
        if(quantity <=0){
            System.out.println("Da het sach khong the muon");
            return;
        }

        //3.tru so luong di 1
        String updateSql="UPDATE Books SET quantity=quantity-1 WHERE BookCode =? ";
        PreparedStatement update =cn.prepareStatement(updateSql);
        update.setInt(1,bookCode);
        update.executeUpdate();

        //4.ghi lich su muon avo borrow
        String insertBorrow="INSERT INTO BORROW (readerId, bookCode,borrowDate) VALUES (?,?,CURDATE())";
        PreparedStatement insert=cn.prepareStatement(insertBorrow);
        insert.setInt(1,readerId);
        insert.setInt(2,bookCode);
        insert.executeUpdate();

        System.out.println("Muon sach thanh cong!");

    }


    //4.Hàm trả sách
    public void returnBook(int readerId,int bookCode) throws SQLException{
        //1.Ktra xem nguoi doc co muon cuon sach do hay khong
        String checkSql ="SELECT *FROM borrow WHERE readerId=? AND bookCode=? ";
        PreparedStatement check=cn.prepareStatement(checkSql);
        check.setInt(1,readerId);
        check.setInt(2,bookCode);
        ResultSet rs=check.executeQuery();

        if(!rs.next()){
            System.out.println("Nguoi doc chua muon sach nay!");
            return ;
        }

        //2.xoa bang ghi muon trong bang borrow
        String deleteSql="DELETE FROM borrow WHERE readerId=? AND bookCode=? ";
        PreparedStatement delete =cn.prepareStatement(deleteSql);
        delete.setInt(1,readerId);
        delete.setInt(2,bookCode);
        delete.executeUpdate();

        //3.Cap nhap lai so luong sach trong bang books(cong them 1)
        String updateSql="UPDATE books Set quantity=quantity +1 WHERE bookCode=? ";
        PreparedStatement update=cn.prepareStatement(updateSql);
        update.setInt(1,bookCode);
        update.executeUpdate();

        System.out.println("Tra sach thanh cong !");
    }


    //5.Ham xem danh sach sach
    public void viewAllBooks() throws SQLException{
        String sql="SELECT *FROM books";
        PreparedStatement ps=cn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        System.out.printf("%-15s %-30s %-20s %-15s %-15s %-10s\n",
                "ma sach ","ten sach","ten tg","Nam xb","the loai","so luong");

        while(rs.next()){
            int code=rs.getInt("bookCode");
            String name=rs.getString("bookName");
            String author=rs.getString("authorName");
            int year=rs.getInt("publish");
            String category=rs.getString("category");
            int quantity=rs.getInt("quantity");

            System.out.printf("%-15d %-30s %-20s %-15d %-15s %-10d\n",code,name,author,year,category,quantity);
        }
    }

    //6.Tim kiem sach
    public void searchBook(String keyword) throws SQLException{
        String sql="SELECT *FROM books WHERE bookName LIKE?";
        PreparedStatement ps=cn.prepareStatement(sql);
        ps.setString(1,"%" +keyword+"%");//"%" la ki tu dai dien dung trong SQL khi ket hop vs yoan tu LIKE
        ResultSet rs=ps.executeQuery();

        boolean found=false;//ktra xem co tim dc it nhat 1 cuon sach hay k
        System.out.printf("%-15s %-30s %-20s %-15s %-15s %-10s\n",
                "ma sach ","ten sach","ten tg","Nam xb","the loai","so luong");
        while(rs.next()){
            found=true;
            int code=rs.getInt("bookCode");
            String name=rs.getString("bookName");
            String author=rs.getString("authorName");
            int year=rs.getInt("publish");
            String category=rs.getString("category");
            int quantity=rs.getInt("quantity");

            System.out.printf("%-15d %-30s %-20s %-15d %-15s %-10d\n",code,name,author,year,category,quantity);
        }
        if(!found){
            System.out.println("Khong tim thay sach nao co ten chua: "+keyword);
        }
    }


    //7.Xem lich su muon tra
    public void viewhistory() throws SQLException{
        String sql="SELECT b.borrowId, b.readerId, r.fullName, b.bookCode, bk.bookName, b.borrowDate " +
                "FROM borrow b " +
                "JOIN Readers r ON b.readerId=r.readerId " +
                "JOIN Books bk ON b.bookCode=bk.bookCode";
        PreparedStatement ps=cn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        System.out.printf("%-5s %-10s %-25s %-10s %-30s %-15s\n " ,
                "ID","Ma sach" ,"Ten nguoi doc" ,"Ma sach" ,"Ten sach ","Ngay muon");
        while(rs.next()){
            int id=rs.getInt("borrowId");
            int readerId=rs.getInt("readerId");
            String fullName=rs.getString("fullName");
            int bookCode=rs.getInt("bookCode");
            String bookName=rs.getString("bookName");
            Date borrowDate=rs.getDate("borrowDate");
            System.out.printf("%-5d %-10d %-25s %-10d %-30s %-15s\n " ,
                    id,readerId,fullName,bookCode,bookName,borrowDate.toString());
        }
    }

    //8.Thong ke sach
    public void statisticBook() throws SQLException{
        String sql="SELECT category,COUNT(*) AS soluongsach,SUM(quantity) AS Tongsoluong "+
                "FROM books GROUP BY category";
        PreparedStatement ps=cn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        System.out.printf("%-20s %30s %-30s\n ","the loai","Soluong dau sach","Tong so sach");
        while(rs.next()){
            String category= rs.getNString("category");
            int soluongsach=rs.getInt("soluongsach");
            int Tongsoluong=rs.getInt("Tongsoluong");

            System.out.printf("%-20s %30d %-30d\n ",category,soluongsach,Tongsoluong);
        }

    }

    //9.Nhom sach theo the loai
    public void groupBook() throws SQLException{
        String sql="SELECT *,LOWER(TRIM(category)) AS categoryKey FROM books ORDER BY category,bookCode";
        PreparedStatement ps=cn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        String currentCategoryKey=" ";
        boolean first=true;//xu ly viec xuong dong khi co dong moi xuat hien

        while(rs.next()){
            String categoryRaw=rs.getString("category");//giu nguyen de in ra
            if(categoryRaw==null) categoryRaw="Khong co the loai nay";

            String categoryKey=remove(categoryRaw);
            if(!categoryKey.equals(currentCategoryKey)){
                if(!first){
                    System.out.println();
                }
                currentCategoryKey=categoryKey;//cap nhap de luu the loai moi vua gap
                System.out.println("The loai: "+categoryRaw);
                first=false;//da xu ly xong dong dau tien tu h se in dong trang giua cac nhom
            }

            //in thong tin sach trong nhom
            String bookCode=rs.getString("bookCode");
            String bookName=rs.getString("bookName");
            String authorName=rs.getString("authorName");
            int publish=rs.getInt("publish");

            int quantity=rs.getInt("quantity");

            System.out.printf("Ma sach :%s ,Ten: %s,Tac gia: %s,Nam xb: %d,So luong:%d\n",bookCode,bookName,authorName,publish,quantity);


        }
    }

    //ham loai bo dau tieng viet
    public static String remove(String str){
        String temp= Normalizer.normalize(str,Normalizer.Form.NFD);
        return temp.replaceAll("\\p{M}", "").toLowerCase().trim();
    }

}
