package Controller;
import Model.Account;
import Model.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("error")!=null){
            request.setAttribute("Error","Sai username hoac password");
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username.trim()) && "123".equals(password.trim())) {
            User user=new User(1,username,password,"admin");
            Account acc=new Account(1001,10000000.0,user);

            HttpSession session=request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("Account", acc);
            response.sendRedirect(request.getContextPath()+"/home");

        } else {
            response.sendRedirect(request.getContextPath()+"/login?error=1");
        }
    }
}
