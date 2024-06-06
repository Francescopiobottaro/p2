package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/Register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao usDao = new UserDao();
        
        try {
            UserBean user = new UserBean();
            user.setUsername(request.getParameter("un"));
            user.setPassword(BCrypt.hashpw(request.getParameter("pw"), BCrypt.gensalt()));
            // Imposta altri attributi dell'utente se necessario

            usDao.registerUser(user);
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
