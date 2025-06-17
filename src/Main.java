
import java.util.Scanner;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BankTransaction ng=new BankTransaction();
        BankTransaction nn=new BankTransaction();
        System.out.println("Nhap thong tin cua nguoi gui: ");
        ng.inputData();
        System.out.println("\nNhap thong tin nguoi nhan: ");
        nn.inputData();

        //ghi file
        try {
            FileWriter fw = new FileWriter("taikhoan.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write( nn.toString() + "\n");

            bw.newLine();
            bw.close();
            System.out.println("Da luu danh sach vao file taikhoan.txt");
        }catch(IOException e){
            System.out.println("Loi khi ghi file : "+ e.getMessage());
        }

        int choice;
        do{
            System.out.println("CHON PHUONG THUC");
            System.out.println("0.Thoat");
            System.out.println("1.Nap tien");
            System.out.println("2.Rut tien");
            System.out.println("3.Chuyen tien");
            System.out.println("Chon: ");
            choice=sc.nextInt();
            switch(choice){
                case 1:
                    //nap tien
                    System.out.println("\nNhap so tien can nap:");
                    double nap=sc.nextDouble();
                    while(nap<=0){
                        System.out.println("\nSo tien can nap khong hop le !");
                        System.out.println("\nNhap so tien can nap:");
                        nap=sc.nextDouble();
                    }
                    if(ng.deposit(nap)){
                        System.out.println("Da nap tien thanh cong");
                    }else{
                        System.out.println("Nap tien that bai!");
                    }


                    break;
                case 2:
                    //rut tien
                    System.out.println("\nNhap so tien can rut: ");
                    double rut=sc.nextDouble();
                    while(rut<=0){
                        System.out.println("\nSo tien rut khong hop le!");
                        System.out.println("\nNhap so tien can rut: ");
                        rut=sc.nextDouble();
                    }
                    System.out.println("Nhap ma pin : ");
                    int inputPin= sc.nextInt();
                    int index=0;
                    while(ng.getPin()!=inputPin && index <5){
                        System.out.println("Ma pin khong hop le !");
                        System.out.println("Nhap ma pin : ");
                        index++;
                        inputPin=sc.nextInt();
                    }
                    if(ng.withdraw(rut,inputPin)){
                        System.out.println("Da rut tien thanh cong");
                    }else{
                        System.out.println("Rut tien that bai! ");
                    }

                    break;
                case 3:
                    //chuyent tien
                    System.out.println("Nhap so tai khoan nguoi nhan: ");
                    int tk=sc.nextInt();
                    nn=findRecipient(tk);
                    if(nn==null){
                        System.out.println("Khong tim thay tai khoan");
                        break;
                    }
                    System.out.println("Nhap so tien can chuyen: ");
                    double amount =sc.nextDouble();
                    while(amount <=0 ){
                        System.out.println("So tien khong hop le!");
                        System.out.println("Nhap so tien can chuyen: ");
                        amount=sc.nextDouble();
                    }
                    System.out.println("Nhap ma pin : ");
                    int Pin= sc.nextInt();
                    int count=0;
                    while(ng.getPin()!=Pin && count <5){
                        System.out.println("Ma pin khong hop le !");
                        System.out.println("Nhap ma pin : ");
                        count++;
                        Pin=sc.nextInt();
                    }
                    if(ng.transfer(nn,Pin,amount)){
                        System.out.println("Chuyen tien thanh cong");
                        try{
                            FileReader fr=new FileReader("taikhoan.txt");
                            BufferedReader br=new BufferedReader(fr);
                            StringBuilder sb=new StringBuilder();//luu vao 1 file tam thoi

                            String line;
                            while((line =br.readLine())!= null){
                                if(line.trim().isEmpty()) continue;
                                String[] data=line.split(",");
                                if(Integer.parseInt(data[0]) == nn.getAccountNumber()){
                                    data[3]=String.valueOf(nn.getBalance());//cap nhap lai so du
                                    line=String.join(",",data);
                                }
                                sb.append(line+"\n");//tam luu toan bo noi dung file
                            }
                            br.close();

                            FileWriter fw=new FileWriter("taikhoan.txt");
                            BufferedWriter bw=new BufferedWriter(fw);

                            bw.write(sb.toString());
                            bw.close();
                        }catch (IOException e){
                            System.out.println("Loi khi cap nhap so du nguoi nhan: " +e.getMessage());
                        }
                    }else{
                        System.out.println("Chuyen tien that bai!(so du k du)");
                    }
                    break;
            }

        }while(choice !=0);



    }

    //doc file dua ra ngoai ham main
    public static BankTransaction findRecipient(int stk){
        try{
            FileReader fr=new FileReader("taikhoan.txt");
            BufferedReader br=new BufferedReader(fr);

            String line;//chua dong khi doc
            while((line=br.readLine()) !=null){
                if(line.trim().isEmpty()) continue;//bo khoang trang
                String [] data=line.split(",");
                if(Integer.parseInt(data[0])==stk){
                    BankTransaction bt=new BankTransaction();
                    bt.setAccountNumber(Integer.parseInt(data[0]));
                    bt.setName(data[1]);
                    bt.setBank(data[2]);
                    bt.setBalance(Double.parseDouble(data[3]));
                    bt.setPin(Integer.parseInt(data[4]));
                    return bt;
                }
            }
        }catch(IOException e){
            System.out.println("Loi khi doc file nguoi nhan: "+e.getMessage());
        }
        return null;
    }
}
