package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Borrow;

public class BorrowDAO {
    private Connection conn;

    public BorrowDAO(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return this.conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void addBorrow(Borrow br) throws SQLException {
        String sql = "INSERT INTO model.Borrow(readerId,bookCode,borrowDate)VALUES(?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, br.getReaderId());
        ps.setInt(2, br.getBookCode());
        ps.setDate(3, new Date(br.getBorrowDate().getTime()));
        ps.executeUpdate();
    }

    public List<Borrow> getAllBorrow() throws SQLException {
        List<Borrow> l = new ArrayList();
        String sql = "SELECT *FROM model.Borrow";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Borrow br = new Borrow(rs.getInt("borrowId"), rs.getInt("readerId"), rs.getInt("bookCode"), rs.getDate("borrowDate"), rs.getDate("returnDate"));
            l.add(br);
        }

        return l;
    }

    public void updateBorrow(int borrowId, java.util.Date newReturnDate) throws SQLException {
        String sql = "UPDATE model.Borrow SET returnDate=? WHERE borrowId=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setDate(1, new Date(newReturnDate.getTime()));
        ps.setInt(2, borrowId);
        ps.executeUpdate();
    }

    public void deleteBorrow(int borrowId) throws SQLException {
        String sql = "DELETE FROM model.Borrow WHERE borrowId=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, borrowId);
        ps.executeUpdate();
    }
}
