import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Library lib=new Library();
        Books s=new Books();
        int choice;
        do{
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
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Nhap thong tin them sach : ");
                    s.inputData();
                    try{
                        lib.addBooks(s);
                    }catch (Exception e){
                        System.out.println("Loi khi them sach : "+e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Nhao thong tin nguoi doc");
                    Readers r=new Readers();
                    r.inputdata();
                    try{
                        lib.addReaders(r);
                    }catch (Exception e){
                        System.out.println("Loi khi them nguoi doc: "+e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Nhap thong tin muon sach");
                    System.out.println("Nhap ma nguoi doc: ");
                    int readerId=sc.nextInt();
                    System.out.println("Nhap ma sach muon muon: ");
                    int bookCode=sc.nextInt();
                    try{
                        lib.borrowBook(readerId,bookCode);
                    }catch(SQLException e){
                        System.out.println("Loi khi muon sach: "+e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Nhap thong tin muon sach ");
                    System.out.println("Nhap ma nguoi doc da tra sach :");
                    int reader=sc.nextInt();
                    System.out.println("Nhap ma sach da tra : ");
                    int bookcode=sc.nextInt();
                    try{
                        lib.returnBook(reader,bookcode);
                    } catch (SQLException e) {
                        System.out.println("Loi khi tra sach: "+e.getMessage());
                    }
                    break;
                case 5:
                    try{
                        lib.viewAllBooks();
                    }catch (SQLException e){
                        System.out.println("Loi khi xem sanh sach: "+e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Nhap ten sach can tim : ");
                    sc.nextLine();
                    String keyword=sc.nextLine();
                    try{
                        lib.searchBook(keyword);
                    } catch (SQLException e) {
                        System.out.println("Loi khi tim kiem sach : "+e.getMessage());
                    }
                    break;
                case 7:
                    try{
                        lib.viewhistory();
                    }catch (SQLException e){
                        System.out.println("Loi khi xem lich su muon tra: " +e.getMessage());
                    }
                    break;
                case 8:
                    try{
                        lib.statisticBook();
                    }catch (SQLException e){
                        System.out.println("Loi khi thong ke sach : "+e.getMessage());
                    }
                    break;
                case 9:
                    try{
                        lib.groupBook();
                    }catch (SQLException e){
                        System.out.println("Loi khi nhom sach theo the loai: "+e.getMessage());
                    }
                    break;

            }

        }while(choice!=0);

        }
    }
