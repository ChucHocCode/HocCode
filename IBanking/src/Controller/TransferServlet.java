package Controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Account;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

        String name=request.getParameter("name");
        String toAccount=request.getParameter("toAccount");

        HttpSession session=request.getSession();

        Map<String,String> accountList=new HashMap<>();
        accountList.put("cuong","123");
        accountList.put("lo","234");
        accountList.put("chuc","345");
        accountList.put("ha","456");

        if(accountList.containsKey(name) && accountList.get(name).equals(toAccount)){
            session.setAttribute("name",name);
            session.setAttribute("toAccount",toAccount);
            request.getRequestDispatcher("enterAmount.jsp").forward(request,response);
        }else{
            request.setAttribute("message","Tai khoan k ton tai");
            request.getRequestDispatcher("transfer.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doPost(request,response);
    }
}
