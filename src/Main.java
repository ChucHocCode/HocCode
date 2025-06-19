import java.util.Scanner;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Banktransaction user=new Banktransaction();

        int choice;
        do{
            System.out.println("\nCHON PHUONG THUC GIAO DICH\n");
            System.out.println("0.Thoat");
            System.out.println("1.Tao tk");
            System.out.println("2.Nap/Rut tien");
            System.out.println("3.Chuyen tien");
            System.out.println("Chon: ");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Nhap tai khoan :");
                    user.inputData();

                    boolean count=false;
                    try{
                        FileReader fr=new FileReader("FileData.txt");
                        BufferedReader br=new BufferedReader(fr);

                        String line;
                        while((line= br.readLine())!=null){
                            if(line.trim().isEmpty()) continue;
                            String [] data=line.split(",");
                            if(Integer.parseInt(data[0])== user.getAccountNumber()){
                                count=true;
                                break;
                            }
                        }
                        br.close();

                        if(count){
                            System.out.println("Tai khoan da ton tai");
                        }else{
                            FileWriter fw=new FileWriter("FileData.txt",true);
                            BufferedWriter bw=new BufferedWriter(fw);

                            bw.write(user.toString()+"\n");
                            bw.close();
                            System.out.println("\nTao tai khoan thanh cong");
                        }

                    }catch(IOException e){
                        System.out.println("Loi khi khi file : "+e.getMessage());
                    }

                    break;
                case 2:
                    int option;
                    do{
                        System.out.println("0.Thoat");
                        System.out.println("1.Nap ");
                        System.out.println("2.Rut");
                        System.out.println("Chon: ");
                        option=sc.nextInt();
                        switch(option){
                            case 1:
                                System.out.println("\nNhap so tai khoan:");
                                int acount=sc.nextInt();
                                Banktransaction people=findRecipient(acount);
                                while(people==null){
                                    System.out.println("Khong co so tai khoan do");
                                    System.out.println("Nhap so tai khoan : ");
                                    acount=sc.nextInt();

                                }
                                System.out.println("Nhap so tien can nap: ");
                                double amount =sc.nextDouble();
                                while(amount <=0){
                                    System.out.println("So tien khong hop le !");
                                    System.out.println("Nhap so tien can nap: ");
                                    amount =sc.nextDouble();
                                }

                                System.out.println("Nhap ma pin: ");
                                int inputpin=sc.nextInt();
                                while(inputpin!=people.getPin()){
                                    System.out.println("\nMa pin khong hop le!");
                                    System.out.println("Nhap ma pin: ");
                                    inputpin=sc.nextInt();
                                }

                                //cap nhap lai so du
                                people.setBalance(people.getBalance()+amount);
                                UpdateTransaction(people,null,0);
                                break;
                            case 2:
                                System.out.println("\nNhap so tai khoan:");
                                int an=sc.nextInt();
                                Banktransaction pp=findRecipient(an);
                                while(pp==null){
                                    System.out.println("Khong co so tai khoan do");
                                    System.out.println("Nhap so tai khoan : ");
                                    acount=sc.nextInt();

                                }
                                System.out.println("Nhap so tien can rut: ");
                                double rut=sc.nextDouble();
                                while(rut<=0 || rut >pp.getBalance()){
                                    System.out.println("So tien rut khong hop le!");
                                    System.out.println("Nhap so tien can rut: ");
                                    rut=sc.nextDouble();
                                }

                                System.out.println("\nNhap ma pin: ");
                                int pin=sc.nextInt();
                                while(pin!=pp.getPin()){
                                    System.out.println("Ma pin khong hop le !");
                                    System.out.println("\nNhap ma pin: ");
                                    pin=sc.nextInt();
                                }

//                                double withdraw= user.getBalance();
//                                user.setBalance(user.getBalance()-rut);
//                                System.out.println("Rut tien thanh cong");
//                                System.out.println("So du moi: "+user.getBalance());
                                pp.setBalance(pp.getBalance()-rut);
                                UpdateTransaction(pp,null,0);

                                break;
                        }
                    }while(option !=0);
                    break;
                case 3:
                    System.out.println("Nhap so tai khoan nguoi nhan:");
                    int accountnumber=sc.nextInt();
                    Banktransaction recipient=findRecipient(accountnumber);
                    if(recipient==null){
                        System.out.println("Khong tim thay so tai khoan");
                        int choose;
                        do{
                            System.out.println("NHAP LUA CHON:");
                            System.out.println("0.Thoat");
                            System.out.println("1.Tao tai khoan");
                            System.out.println("2.Nhap so tai khoan");
                            System.out.println("Chon: ");
                            choose=sc.nextInt();
                            switch (choose){
                                case 1:
                                    System.out.println("Tao tai khoan : ");
                                    user.inputData();
                                    break;
                                case 2:
                                    System.out.println("Nhap so tai khoan nguoi nhan:");
                                    accountnumber=sc.nextInt();
                                    recipient=findRecipient(accountnumber);
                                    System.out.println("\nNhap so tien can chuyen: ");
                                    double amount= sc.nextDouble();
                                    while(amount <=0 || user.getBalance()< amount){
                                        System.out.println("So tien chuyen khong hop le!");
                                        System.out.println("\nNhap so tien can chuyen: ");
                                        amount=sc.nextDouble();
                                    }

                                    System.out.println("\nNhap ma pin: ");
                                    int pin=sc.nextInt();
                                    while(user.getPin()!=pin){
                                        System.out.println("Ma pin khong hop le!");
                                        System.out.println("\nNhap ma pin: ");
                                        pin=sc.nextInt();
                                    }
                                    UpdateTransaction(user,recipient,amount);
                                    break;
                            }

                        }while(choose !=0);
                    }
                    System.out.println("\nNhap so tien can chuyen: ");
                    double amount= sc.nextDouble();
                    while(amount <=0 || user.getBalance()< amount){
                        System.out.println("So tien chuyen khong hop le!");
                        System.out.println("\nNhap so tien can chuyen: ");
                        amount=sc.nextDouble();
                    }

                    System.out.println("\nNhap ma pin: ");
                    int pin=sc.nextInt();
                    while(user.getPin()!=pin){
                        System.out.println("Ma pin khong hop le!");
                        System.out.println("\nNhap ma pin: ");
                        pin=sc.nextInt();
                    }
                    UpdateTransaction(user,recipient,amount);
                    break;
            }
        }while(choice !=0);


    }
    public static void UpdateTransaction(Banktransaction user, Banktransaction recipient, double amount) {
        try {
            // 1. Đọc toàn bộ nội dung FileData.txt
            FileReader fr = new FileReader("FileData.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                if(recipient==null){
                    if(Integer.parseInt(data[0])== user.getAccountNumber()){
                        data[3]=String.valueOf(user.getBalance());
                    }
                }else{
                    // Cập nhật số dư người gửi
                    if (Integer.parseInt(data[0]) == user.getAccountNumber()) {
                        data[3] = String.valueOf(user.getBalance() - amount);
                    }

                    // Cập nhật số dư người nhận
                    if (Integer.parseInt(data[0]) == recipient.getAccountNumber()) {
                        data[3] = String.valueOf(recipient.getBalance() + amount);
                    }
                }



                sb.append(String.join(",", data)).append("\n");
            }
            br.close();

            // 2. Ghi đè toàn bộ vào FileData.txt
            FileWriter fw = new FileWriter("FileData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();

            System.out.println("Chuyen tien thanh cong. Da cap nhat vao file.");

        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public static Banktransaction findRecipient(int stk){
        try{
            FileReader fr=new FileReader("FileData.txt");
            BufferedReader br=new BufferedReader(fr);

            String line;
            while((line =br.readLine()) !=null){
                if(line.trim().isEmpty()) continue;
                String[] data=line.split(",");
                if(Integer.parseInt(data[0].trim()) ==stk) {
                    Banktransaction bt = new Banktransaction();
                    bt.setAccountNumber(Integer.parseInt(data[0].trim()));
                    bt.setName(data[1].trim());
                    bt.setBank(data[2].trim());
                    bt.setBalance(Double.parseDouble(data[3].trim()));
                    return bt;
                }
            }
        }catch (IOException e){
            System.out.println("Loi khi doc file :"+e.getMessage());
        }
        return null;
    }


}