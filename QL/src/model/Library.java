package Manage;

import DAO.BookDAO;
import DAO.BorrowDAO;
import DAO.ReaderDAO;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Book;
import model.Borrow;
import model.Reader;

public class Library {
    private Connection conn;
    private BookDAO bookDAO;
    private ReaderDAO readerDAO;
    private BorrowDAO borrowDAO;
    Scanner sc;

    public Library(BookDAO bookDAO, ReaderDAO readerDAO, BorrowDAO borrowDAO, Connection conn) {
        this.sc = new Scanner(System.in);
        this.conn = conn;
        this.bookDAO = bookDAO;
        this.readerDAO = readerDAO;
        this.borrowDAO = borrowDAO;
    }

    public void addBook() {
        System.out.println("Nhap thong tin them sach : ");
        Book b = new Book();

        try {
            b.inputData();
            this.bookDAO.addBook(b);
            System.out.println("Da them sach thanh cong !");
        } catch (Exception e) {
            System.out.println("Loi khi them sach : " + e.getMessage());
        }

    }

    public void addReader() {
        System.out.println("Nhap thong tin them nguoi doc: ");
        Reader r = new Reader();

        try {
            r.inputdata();
            this.readerDAO.addReader(r);
            System.out.println("Da them nguoi doc thanh cong");
        } catch (Exception e) {
            System.out.println("Loi khi them nguoi doc: " + e.getMessage());
        }

    }

    public void addBorrow(int readerId, int bookCode) {
        try {
            this.conn.setAutoCommit(false);
            Book book = this.bookDAO.getBookByCode(bookCode);
            if (book != null) {
                if (book.getQuantity() <= 0) {
                    System.out.println("Sach da het , khong the muon");
                    this.conn.rollback();
                    return;
                }

                Borrow br = new Borrow();
                br.setReaderId(readerId);
                br.setBookCode(bookCode);
                br.setBorrowDate(new Date(System.currentTimeMillis()));
                this.borrowDAO.addBorrow(br);
                this.bookDAO.updateQuantity(bookCode, -1);
                this.conn.commit();
                System.out.println("Muon sach thanh cong!");
                return;
            }

            System.out.println("Sach da het");
        } catch (Exception var17) {
            Exception e = var17;

            try {
                this.conn.rollback();
                System.out.println("Loi khi muon sach , da rollback.");
                e.printStackTrace();
            } catch (SQLException var16) {
                System.out.println("Rollback that bai :" + var17.getMessage());
            }

            return;
        } finally {
            try {
                this.conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void returnBook(int readerId, int bookCode) {
        try {
            this.conn.setAutoCommit(false);
            String sql = "SELECT borrowId FROM model.Borrow WHERE readerId=? AND bookCode=? AND returnDate IS NULL LIMIT 1";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, readerId);
            ps.setInt(2, bookCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int borrowId = rs.getInt("borrowId");
                String updateSql = "UPDATE borrow SET returnDate =CURDATE() WHERE borrowId=? ";
                PreparedStatement updateps = this.conn.prepareStatement(updateSql);
                updateps.setInt(1, borrowId);
                updateps.executeUpdate();
                this.bookDAO.updateQuantity(bookCode, 1);
                this.conn.commit();
                System.out.println("Tra sach thanh cong!");
                return;
            }

            System.out.println("Khong tim thay luot muon nao can tra");
        } catch (Exception var20) {
            Exception e = var20;

            try {
                this.conn.rollback();
                System.out.println("Loi khi tra sach ,da rollback :" + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("rollback that bai :" + ex.getMessage());
            }

            return;
        } finally {
            try {
                this.conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void viewBook() {
        try {
            List<Book> l = this.bookDAO.getAllBooks();
            if (l.isEmpty()) {
                System.out.println("Khong co sach nao trong thu vien");
                return;
            }

            System.out.println("Danh sach trong thu vien la: ");

            for(Book b : l) {
                System.out.println("Ma sach :" + b.getBookCode());
                System.out.println("Ten sach: " + b.getBookName());
                System.out.println("Tac gia: " + b.getAuthorName());
                System.out.println("Nam xuat ban : " + b.getPublish());
                System.out.println("The loai: " + b.getCategory());
                System.out.println("So luong : " + b.getQuantity());
            }
        } catch (SQLException e) {
            System.out.println("Loi khi xem danh sach sach : " + e.getMessage());
        }

    }

    public void bookFind(int bookName) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten cuon sach can tim : ");
        String keyword = sc.nextLine();

        try {
            List<Book> l = this.bookDAO.getBookByName(keyword);
            if (l.isEmpty()) {
                System.out.println("Khong tim thay sach nao co ten : " + keyword);
                return;
            }

            System.out.println("Ket qua tim kiem :");

            for(Book b : l) {
                System.out.println("Ma sach :" + b.getBookCode());
                System.out.println("Ten sach :" + b.getBookName());
                System.out.println("Tac gia : " + b.getAuthorName());
                System.out.println("Nam xb: " + b.getPublish());
                System.out.println("The loai: " + b.getCategory());
                System.out.println("So luong :" + b.getQuantity());
            }
        } catch (SQLException e) {
            System.out.println("Loi khi tim kiem sach :" + e.getMessage());
        }

    }

    public void viewBorrow() {
        try {
            List<Borrow> l = this.borrowDAO.getAllBorrow();
            if (l.isEmpty()) {
                System.out.println("Khong co lich su muon tra nao");
                return;
            }

            System.out.println("Lich su muon tra sach :");

            for(Borrow br : l) {
                System.out.println("Ma luot muon: " + br.getBorrowId());
                System.out.println("Ma nguoi doc: " + br.getReaderId());
                System.out.println("Ma sach :" + br.getBookCode());
                System.out.println("Ngay muon : " + String.valueOf(br.getBorrowDate()));
                PrintStream var10000 = System.out;
                Object var10001 = br.getReturnDate() != null ? br.getReturnDate() : "Chua tra ";
                var10000.println("Ngay tra : " + String.valueOf(var10001));
            }
        } catch (SQLException e) {
            System.out.println("Loi khi xem lich su muon tra : " + e.getMessage());
        }

    }

    public void bookStatisti() {
        try {
            String sql = "SELECT COUNT(*) AS soluongsach,SUM(quantity) AS Tongsoluong FROM Book ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int soluongsach = rs.getInt("soluongsach");
                int Tongsoluong = rs.getInt("Tongsoluong");
                System.out.println("Tong so sach dau: " + soluongsach);
                System.out.println("Tong so sach ton kho :" + Tongsoluong);
            }
        } catch (SQLException e) {
            System.out.println("Loi khi thong ke sach : " + e.getMessage());
        }

    }

    public void groupBook() {
        try {
            String sql = "SELECT *FROM Book ORDER BY category ,bookName";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String currentCategory = "";
            boolean first = true;

            while(rs.next()) {
                String category = rs.getString("category");
                if (!category.equalsIgnoreCase(currentCategory)) {
                    if (!first) {
                        System.out.println();
                    }

                    System.out.println("The loai: " + category.toUpperCase());
                    currentCategory = category;
                    first = false;
                }

                int code = rs.getInt("bookCode");
                String bookName = rs.getString("bookName");
                String author = rs.getString("authorName");
                int publish = rs.getInt("publish");
                int quantity = rs.getInt("quantity");
                System.out.println("Ma sach: " + code);
                System.out.println("Ten sach: " + bookName);
                System.out.println("Tac gia: " + author);
                System.out.println("Nam xb" + publish);
                System.out.println("So luong: " + quantity);
            }
        } catch (SQLException e) {
            System.out.println("Loi khi nhom sach theo the loai: " + e.getMessage());
        }

    }
}
