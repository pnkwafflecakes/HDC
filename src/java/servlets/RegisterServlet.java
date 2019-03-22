package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Knyfe
 */
public class RegisterServlet extends HttpServlet
{
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String postalCode = request.getParameter("postal");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Boolean errorCheck = false;
        //Validation for correct length and that each field is not blank
        if(username.equals("") || username.length() < 8)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Username");
        }
        else if(password.equals("") || password.length() < 8)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Password That Is 8 Characters Or Longer.");
        }
        else if(name.equals("") || name.length() < 5)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Full Name");
        }
        else if(address.equals("") || address.length() < 10)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Full Address");
        }
        else if(postalCode.equals("") || postalCode.length() < 7 || postalCode.length() > 7)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Postal Code (Please Include The Dash)");
        }
        else if(email.equals(""))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Email");
        }
        else if(phone.equals("") || phone.length() < 12)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Phone Number (Include The Dashes)");
        }
        
        //Validation alphanumeric and specidic characters/patterns
        if(username.contains("_"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Only Include Letters And Numbers");
        }
        if(!username.matches(".[a-zA-Z0-9]*"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Username With Only Letters and Numbers");
        }
        if(!password.matches(".[a-zA-Z0-9]*"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Password That Contains Only Letters and Numbers");
        }
        if(!name.matches(".[a-zA-Z ]*"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Name");
        }
        if(!address.matches(".[a-zA-Z0-9. ]*"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Full Address");
        }
        if(!postalCode.matches("^[a-zA-z]{1}[0-9]{1}[a-zA-z]{1}[-]{1}[0-9]{1}[a-zA-Z]{1}[0-9]{1}"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Postal Code With the Dashes Included");
        }
        if(!email.matches("[A-Za-z0-9]{2,30}[@][A-Za-z0-9]{2,20}{1}[.]{1}[A-Za-z]{2,8}"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Valid Email Address");
        }
        if(!phone.matches("^\\d{3}[-]{1}\\d{3}[-]{1}\\d{4}") && !phone.matches("^[+]{1}\\d{1,3}[-]{1}\\d{3}[-]{1}\\d{3}[-]{1}\\d{4}"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Valid Phone Number (Add a Plus and a Dash For Extensions)");
        }
        if(errorCheck)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        else
        {
            try {
                //Get highest user_id from db
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb","root", "password");
                String prepStatement = "Select MAX(user_id) from user;";
                PreparedStatement ps = connection.prepareStatement(prepStatement);
                ResultSet rs = ps.executeQuery();
                
                //Create Insert statement
                String prepInsertUser = "Insert Into User(user_id,name,address,postal_code,email,phone_no,account_type,username,password,account_status) Values(?,?,?,?,?,?,?,?,?,?);";

                //Get highest account number.
                rs.next();
                int user_id = rs.getInt(1);
                user_id++;
                
                //Prepare postalCode
                
                postalCode = postalCode.replace("-",""); 
                
                //Add user input into prepared statement.
                ps = connection.prepareStatement(prepInsertUser);
                ps.setInt(1, user_id);
                ps.setString(2, name);
                ps.setString(3, address);
                ps.setString(4, postalCode);
                ps.setString(5, email);
                ps.setString(6, phone);
                ps.setInt(7, 1);
                ps.setString(8, username);
                ps.setString(9, password);
                ps.setInt(10, 1);
                //Insert user into user table.
                ps.executeUpdate();
                
                //Confirm to user that they have been registered.
                request.setAttribute("status", "Registration Complete! Please login to begin");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
