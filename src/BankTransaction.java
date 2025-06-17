import java.util.Objects;
import java.util.Scanner;
public class BankTransaction {
    private String Name;
    private int AccountNumber;
    private double balance;
    private String bank;
    private int pin;

    BankTransaction(){};

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    //nap tien
    public boolean deposit(double amount){
        this.balance+=amount;
        return true;
    }


    //rut tien
    public boolean withdraw(double amount ,int inputPin){
        if(this.pin== inputPin && amount >0 &&this.balance >=amount){
            this.balance-=amount;
            return true;
        }
        return false;
    }


    //chuyen tien
    public boolean transfer(BankTransaction receiver,int inputPin,double amount){
        if(withdraw(amount,inputPin)){
            receiver.deposit(amount);
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(Name, AccountNumber, balance, bank, pin);
    }

    public void inputData(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhap ten:");
        Name=sc.nextLine();
        System.out.println("Nhap STK: ");
        AccountNumber=sc.nextInt();
        do{
            System.out.println("Nhap so du: ");
            balance=sc.nextDouble();
        }while(balance <0);

        sc.nextLine();
        System.out.println("Nhap ngan hang:");
        bank=sc.nextLine();
        System.out.println("Nhap ma pin: ");
        pin=sc.nextInt();

    }

    @Override
    public String toString() {
        return AccountNumber + "," + Name + "," + bank + "," + balance + "," + pin;
    }
}
