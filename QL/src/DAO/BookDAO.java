package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookDAO {
    private Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return this.conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void addBook(Book b) throws SQLException {
        String sql = "INSERT INTO Book (bookCode,bookName,authorName,publish,category,quantity) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, b.getBookCode());
        ps.setString(2, b.getBookName());
        ps.setString(3, b.getAuthorName());
        ps.setInt(4, b.getPublish());
        ps.setString(5, b.getCategory());
        ps.setInt(6, b.getQuantity());
        ps.executeUpdate();
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> l = new ArrayList();
        String sql = "SELECT *FROM Book";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Book b = new Book(rs.getInt("bookCode"), rs.getString("bookName"), rs.getString("authorName"), rs.getInt("publish"), rs.getString("category"), rs.getInt("quantity"));
            l.add(b);
        }

        return l;
    }

    public void updateQuantity(int bookCode, int change) throws SQLException {
        String sql = "UPDATE Book SET quantity=quantity + ? WHERE bookCode=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, change);
        ps.setInt(2, bookCode);
        ps.executeUpdate();
    }

    public void deleteQuantity(int bookCode) throws SQLException {
        String sql = "DELETE FROM Book WHERE bookCode=?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, bookCode);
        ps.executeUpdate();
    }

    public Book getBookByCode(int bookCode) throws SQLException {
        String sql = "SELECT *FROM Book WHERE bookCode=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, bookCode);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new Book(rs.getInt("bookCode"), rs.getString("bookName"), rs.getString("authorName"), rs.getInt("publish"), rs.getString("category"), rs.getInt("quantity")) : null;
    }

    public List<Book> getBookByName(String keyword) throws SQLException {
        List<Book> l = new ArrayList();
        String sql = "SELECT *FROM Book WHERE bookName LIKE?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Book b = new Book(rs.getInt("bookCode"), rs.getString("bookName"), rs.getString("authorName"), rs.getInt("publish"), rs.getString("category"), rs.getInt("quantity"));
            l.add(b);
        }

        return l;
    }
}
