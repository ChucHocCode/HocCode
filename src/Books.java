import java.util.Scanner;
import java.time.*;

public class Books {
    private int bookCode;
    private String bookName;
    private String authorName;
    private int publish;
    private String category;
    private int quantity;
    static int getdate=Year.now().getValue();

    Books(){};

    public Books(int bookCode, String bookName, String authorName, int publish, String category, int quantity) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publish = publish;
        this.category = category;
        this.quantity = quantity;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublish() {
        return publish;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void inputData(){
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Nhap ma sach :");
            bookCode=sc.nextInt();
        }while( bookCode <0 );
        sc.nextLine();
        System.out.println("Nhap ten sach : ");
        bookName=sc.nextLine();
        System.out.println("Nhap ten tac gia: ");
        authorName=sc.nextLine();
        do{
            System.out.println("Nhap nam xuat ban : ");
            publish=sc.nextInt();
        }while(publish <=0 || publish > getdate);
        sc.nextLine();
        System.out.println("Nhap the loai sach : ");
        category=sc.nextLine();
        do{
            System.out.println("Nhap so luong sach : ");
            quantity=sc.nextInt();
        }while(quantity <0 ||quantity >1000);


    }

    @Override
    public String toString() {
        return
                "bookCode=" + bookCode +","+
                " bookName=" + bookName + "," +
                " authorName=" + authorName + "," +
                "publish=" + publish +
                " category=" + category + "," +
                "quantity=" + quantity;
    }
}
