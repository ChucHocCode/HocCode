package Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import Model.Account;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

        HttpSession session=request.getSession();

        //ktra session
        if(session ==null){
            response.sendRedirect("login.jsp");
            return;
        }
        Account acc=(Account) session.getAttribute("Account");

        if(acc==null){
            response.sendRedirect("login.jsp");
            return;
        }
        request.setAttribute("username",acc.getUser().getUserName());
        request.setAttribute("balance",acc.getBalance());

        RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
        rd.forward(request,response);
    }
}
