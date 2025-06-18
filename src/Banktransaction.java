import java.util.Scanner;

public class Banktransaction {
    private String name;
    private int AccountNumber;
    private double balance;
    private String bank;
    private int pin;

    Banktransaction(){};


    public Banktransaction(String name, int accountNumber, double balance, String bank, int pin) {
        this.name = name;
        AccountNumber = accountNumber;
        this.balance = balance;
        this.bank = bank;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }


    //nhap du lieu
    public void inputData(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap ten:");
        name=sc.nextLine();
        System.out.println("Nhap so tai khoan: ");
        AccountNumber=sc.nextInt();
        do{
            System.out.println("Nhap so du: ");
            balance=sc.nextDouble();
        }while(balance <0);
        sc.nextLine();
        System.out.println("Nhap ngan hang: ");
        bank=sc.nextLine();
        System.out.println("Nhap ma pin: ");
        pin=sc.nextInt();

    }

    @Override
    public String toString() {
        return

                 AccountNumber +","+ name + ","+
                 bank + "," +  balance +","+ pin ;

    }
}
