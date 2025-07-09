package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Reader;

public class ReaderDAO {
    private Connection conn;

    public ReaderDAO(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return this.conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void addReader(Reader r) throws SQLException {
        String sql = "INSERT INTO model.Reader (readerId,fullName,dob,gender,address)VALUES(?,?,?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, r.getReaderId());
        ps.setString(2, r.getFullName());
        ps.setString(3, r.getDob());
        ps.setString(4, r.getGender());
        ps.setString(5, r.getAddress());
        ps.executeUpdate();
    }

    public Reader getReaderById(int readerId) throws SQLException {
        String sql = "SELECT *FROM model.Reader WHERE readerId=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, readerId);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new Reader(rs.getInt("readerId"), rs.getString("fullName"), rs.getString("dob"), rs.getString("gender"), rs.getString("address")) : null;
    }

    public void updateReader(Reader r) throws SQLException {
        String sql = "UPDATE model.Reader SET fullname=? ,dob=?,gender=?,address=? WHERE readerId=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, r.getFullName());
        ps.setString(2, r.getDob());
        ps.setString(3, r.getGender());
        ps.setString(4, r.getAddress());
        ps.setInt(5, r.getReaderId());
        ps.executeUpdate();
    }

    public void deleteReader(int readerId) throws SQLException {
        String sql = "DELETE FROM model.Reader WHERE readerId=? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, readerId);
        ps.executeUpdate();
    }
}
