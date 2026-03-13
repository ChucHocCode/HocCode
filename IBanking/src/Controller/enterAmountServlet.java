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

@WebServlet("/enterAmount")
public class enterAmountServlet extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        String name=(String)session.getAttribute("name");
        String toAccount=(String) session.getAttribute("toAccount");

        String amountStr =request.getParameter("amount");

        Account acc=(Account) session.getAttribute("Account");
        if(acc==null){
            response.sendRedirect("transfer.jsp");
            return;
        }
        double amount;
        try{
            amount=Double.parseDouble(amountStr);
            if(amount < acc.getBalance()){
                session.setAttribute("name",name);
                session.setAttribute("toAccount",toAccount);
                session.setAttribute("amount",amount);
                response.sendRedirect("enterPIN.jsp");
            }else{
                request.setAttribute("message","So du khong du");
                request.getRequestDispatcher("transfer.jsp").forward(request,response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("message","Invalid amount");
            request.getRequestDispatcher("enterAmount.jsp").forward(request,response);
        }

    }
}