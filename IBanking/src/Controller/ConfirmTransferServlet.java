package Controller;

import Model.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ConfirmTransferServlet")
public class ConfirmTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session= request.getSession();
        String pin=request.getParameter("pin");

        String toAccount=(String) session.getAttribute("toAccount");

        Account account=(Account) session.getAttribute("Account");
        if(account==null){
            response.sendRedirect("login.jsp");
            return;
        }

        String message;

        List<String> history=(List<String>) session.getAttribute("history");
        if(history==null){
            history=new ArrayList<>();
        }
        Double amount=(Double) session.getAttribute("amount");
        if(amount == null){
            request.setAttribute("message","Vui lòng nhập số tiền trước khi xác nhận PIN");
            request.getRequestDispatcher("enterAmount.jsp").forward(request,response);
            return;
        }
        //PIN dung gia su  la 2222

        if(pin.equals("2222")){
            if(account.withdraw(amount)){
                message="Chuyển tiền thành công";
                history.add("Chuyển tiền "+amount+"đến tai khoan "+toAccount);
            }else{
                message="Số tiền không hợp lệ !";
            }
        }else{
            message="Sai mã PIN";
        }

        session.setAttribute("history",history);
        session.setAttribute("Account",account);
        request.setAttribute("message",message);
        request.getRequestDispatcher("transfer.jsp").forward(request,response);
    }
}
