
import CreateDB.CreateDB;
import DAO.BookDAO;
import DAO.BorrowDAO;
import DAO.ReaderDAO;
import Manage.Library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = CreateDB.connect();
        if (conn == null) {
            System.out.println("Khong the ket noi den co so du lieu .Thoat chuong trinh");
            return;
        }
        try {

            BookDAO bookDao = new BookDAO(conn);
            ReaderDAO readerDAO = new ReaderDAO(conn);
            BorrowDAO borrowDAO = new BorrowDAO(conn);

            Library lib = new Library(bookDao, readerDAO, borrowDAO, conn);
            int choice;
            do {
                System.out.println("0.Thoát");
                System.out.println("1.Thêm sách");
                System.out.println("2.Thêm người đọc");
                System.out.println("3.Mượn sách");
                System.out.println("4.Trả sách");
                System.out.println("5.Xem danh sách sách");
                System.out.println("6.Tìm kiếm sách");
                System.out.println("7.Xem lịch sử mượn /trả");
                System.out.println("8.Thống kê sách");
                System.out.println("9.Nhóm sách theo thể loại");
                System.out.println("Chọn:");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        lib.addBook();
                        break;

                    case 2:
                        lib.addReader();
                        break;
                    case 3:
                        System.out.println("Nhap ma nguoi doc: ");
                        int readerId = sc.nextInt();
                        System.out.println("Nhap ma sach : ");
                        int bookCode = sc.nextInt();
                        lib.addBorrow(readerId, bookCode);
                        break;
                    case 4:
                        System.out.println("Nhap ma nguoi doc: ");
                        int ri = sc.nextInt();
                        System.out.println("Nhap ma sach : ");
                        int bi = sc.nextInt();
                        lib.returnBook(ri, bi);
                        break;
                    case 5:
                        lib.viewBook();
                        break;
                    case 6:
                        lib.bookFind(0);
                        break;
                    case 7:
                        lib.viewBorrow();

                        break;
                    case 8:
                        lib.bookStatisti();
                        break;
                    case 9:
                        lib.groupBook();
                        break;
                    default:
                        System.out.println("Lua chon khong hop le.Vui long chon lai");
                        break;

                }

            } while (choice != 0);
            conn.close();
        } catch (Exception e) {
            System.out.println("Loi chuong trinh :" + e.getMessage());
        }

    }
}