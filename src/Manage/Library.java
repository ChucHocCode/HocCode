package Manage;

import DAO.*;
import model.*;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;

public class Library {
    private Connection conn;
    private BookDAO bookDAO;
    private ReaderDAO readerDAO;
    private BorrowDAO borrowDAO;

    public Library(BookDAO bookDAO, ReaderDAO readerDAO, BorrowDAO borrowDAO,Connection conn) {
        this.conn=conn;
        this.bookDAO = bookDAO;
        this.readerDAO = readerDAO;
        this.borrowDAO = borrowDAO;
    }
    Scanner sc=new Scanner(System.in);
    //1.Them sach
    public void addBook(){
        System.out.println("Nhap thong tin them sach : ");
        Book b=new Book();
        try{
            b.inputData();
            bookDAO.addBook(b);
            System.out.println("Da them sach thanh cong !");
        }catch (Exception e){
            System.out.println("Loi khi them sach : "+e.getMessage());
        }
    }

    //2.Them nguoi doc
    public void addReader(){
        System.out.println("Nhap thong tin them nguoi doc: ");
        Reader r=new Reader();
        try{
            r.inputdata();
            readerDAO.addReader(r);
            System.out.println("Da them nguoi doc thanh cong");
        }catch (Exception e){
            System.out.println("Loi khi them nguoi doc: "+e.getMessage());
        }
    }

    //3.Muon sach
    public void addBorrow(int readerId,int bookCode){
        try{
            conn.setAutoCommit(false);//bat dau transaction
            //1.Kiem tra sach con k
            Book book=bookDAO.getBookByCode(bookCode);
            if(book==null){
                System.out.println("Sach da het");
                conn.rollback();
                return;
            }
            if(book.getQuantity() <=0){
                System.out.println("Sach da het , khong the muon");
                conn.rollback();
                return;
            }

            //2.Them ban ghi muon
            Borrow br=new Borrow();
            br.setReaderId(readerId);
            br.setBookCode(bookCode);
            br.setBorrowDate(new Date(System.currentTimeMillis()));

            borrowDAO.addBorrow(br);

            //3.cap nhap so luong sach sau khi muon
            bookDAO.updateQuantity(bookCode,-1);

            conn.commit();//neu loi thi commit
            System.out.println("Muon sach thanh cong!");
        }catch (Exception e) {
            try{
                conn.rollback();//neu loi thi rollback lai toan bo
                System.out.println("Loi khi muon sach , da rollback.");
                e.printStackTrace();
            }catch (SQLException ex){
                System.out.println("Rollback that bai :"+e.getMessage());
            }
        }finally {
            try{
                conn.setAutoCommit(true);//tra lai trang thai auto commit nhu cu
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //4.tra sach
    public void returnBook(int readerId,int bookCode){
        try{
            conn.setAutoCommit(false);//bat dau contraction

            //1.tim borrowId chua tra
            String sql="SELECT borrowId FROM Borrow WHERE readerId=? AND bookCode=? AND returnDate IS NULL LIMIT 1";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,readerId);
            ps.setInt(2,bookCode);
            ResultSet rs=ps.executeQuery();
            if(!rs.next()){
                System.out.println("Khong tim thay luot muon nao can tra");
                conn.rollback();
                return;
            }

            int borrowId=rs.getInt("borrowId");

            //2.cap nhap returnDate cho borrowId
            String updateSql="UPDATE borrow SET returnDate =CURDATE() WHERE borrowId=? ";
            PreparedStatement updateps=conn.prepareStatement(updateSql);
            updateps.setInt(1,borrowId);
            updateps.executeUpdate();

            //3.Cap nhap lai so luong(cong lai 1)
            bookDAO.updateQuantity(bookCode,1);

            conn.commit();//hoan tat transaction
            System.out.println("Tra sach thanh cong!");
        }catch (Exception e){
            try{
                conn.rollback();//neu loi thi rollback lai toan bo
                System.out.println("Loi khi tra sach ,da rollback :"+e.getMessage());
            }catch (SQLException ex){
                System.out.println("rollback that bai :"+ex.getMessage());
            }
        }finally {
            try{
                conn.setAutoCommit(true);//tra lai trang thai auto commit nhu ban dau
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //5.Xem danh sach sach
    public void viewBook(){
        try{
            List<Book> l=bookDAO.getAllBooks();
            if(l.isEmpty()){
                System.out.println("Khong co sach nao trong thu vien");
                return;
            }
            System.out.println("Danh sach trong thu vien la: ");
            for(Book b:l){
                System.out.println("Ma sach :" + b.getBookCode());
                System.out.println("Ten sach: "+b.getBookName());
                System.out.println("Tac gia: "+b.getAuthorName());
                System.out.println("Nam xuat ban : "+b.getPublish());
                System.out.println("The loai: "+b.getCategory());
                System.out.println("So luong : "+b.getQuantity());
            }
        }catch (SQLException e){
            System.out.println("Loi khi xem danh sach sach : "+e.getMessage());
        }
    }


    //6.Tim kiem sach
    public void bookFind(int bookName) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap ten cuon sach can tim : ");
        String keyword=sc.nextLine();

        try{
            List<Book> l=bookDAO.getBookByName(keyword);

            if(l.isEmpty()){
                System.out.println("Khong tim thay sach nao co ten : "+keyword);
                return;
            }

            System.out.println("Ket qua tim kiem :");
            for(Book b:l){
                System.out.println("Ma sach :"+b.getBookCode());
                System.out.println("Ten sach :"+b.getBookName());
                System.out.println("Tac gia : "+b.getAuthorName());
                System.out.println("Nam xb: "+b.getPublish());
                System.out.println("The loai: "+b.getCategory());
                System.out.println("So luong :"+b.getQuantity());
            }
        }catch (SQLException e){
            System.out.println("Loi khi tim kiem sach :"+e.getMessage());
        }
    }

    //7.Xem lich su muon tra
    public void viewBorrow(){
        try{
            List<Borrow> l=borrowDAO.getAllBorrow();
            if(l.isEmpty()){
                System.out.println("Khong co lich su muon tra nao");
                return;
            }
            System.out.println("Lich su muon tra sach :");;
            for(Borrow br: l){
                System.out.println("Ma luot muon: "+ br.getBorrowId());
                System.out.println("Ma nguoi doc: "+br.getReaderId());
                System.out.println("Ma sach :" +br.getBookCode());
                System.out.println("Ngay muon : "+br.getBorrowDate());
                System.out.println("Ngay tra : "+(br.getReturnDate()!=null ? br.getReturnDate(): "Chua tra "));
            }
        }catch (SQLException e){
            System.out.println("Loi khi xem lich su muon tra : "+e.getMessage());
        }
    }

    //8.Thong ke sach
    public void bookStatisti() {
        try{
            String sql="SELECT COUNT(*) AS soluongsach,SUM(quantity) AS Tongsoluong FROM Book ";
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                int soluongsach=rs.getInt("soluongsach");
                int Tongsoluong=rs.getInt("Tongsoluong");

                System.out.println("Tong so sach dau: "+soluongsach);
                System.out.println("Tong so sach ton kho :"+Tongsoluong);
            }
        }catch (SQLException e){
            System.out.println("Loi khi thong ke sach : "+e.getMessage());
        }
    }


    //9.Nhom sach theo the loai
    public void groupBook(){
        try{
            String sql="SELECT *FROM Book ORDER BY category ,bookName";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            String currentCategory="";
            boolean first=true;

            while (rs.next()){
                String category=rs.getString("category");

                if(!category.equalsIgnoreCase(currentCategory)){
                    if(!first) System.out.println();
                    System.out.println("The loai: "+category.toUpperCase());
                    currentCategory=category;
                    first=false;
                }
                int code=rs.getInt("bookCode");
                String bookName=rs.getString("bookName");
                String author=rs.getString("authorName");
                int publish=rs.getInt("publish");
                int quantity=rs.getInt("quantity");

                System.out.println("Ma sach: " + code);
                System.out.println("Ten sach: " + bookName);
                System.out.println("Tac gia: " + author);
                System.out.println("Nam xb"+publish);
                System.out.println("So luong: " + quantity);

            }
        }catch (SQLException e){
            System.out.println("Loi khi nhom sach theo the loai: "+e.getMessage());
        }

    }
}
