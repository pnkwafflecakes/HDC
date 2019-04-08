package servlets;

import Entities.Accounttype;
import Entities.User;
import businesslogic.UserService;
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
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String postalCode = request.getParameter("postal");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");//wont grab
        
        request.setAttribute("firstname", firstName);
        request.setAttribute("lastname", lastName);
        request.setAttribute("address",address);
        request.setAttribute("postal", postalCode);
        request.setAttribute("email",email);
        request.setAttribute("phone",phone);
        request.setAttribute("username", username);
        
        //Remove dash from postal code
        postalCode = postalCode.replace("-", "");
        
        
        Boolean errorCheck = false;
        //Validation for correct length and that each field is not blank
        if(username.equals("") || username.length() < 8)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Username That Is 8 Characters Or Longer.");
        }
        else if(!password.equals(confirmPassword))
        {
            errorCheck = true;
            request.setAttribute("error", "The Passwords Do Not Match.");
        }
        else if(password.equals("") || password.length() < 8)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Password That Is 8 Characters Or Longer.");
        }
        else if(firstName.equals("") || firstName.length() < 2)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Full Name");
        }
        else if(lastName.equals("") || lastName.length() < 2)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Last Name");
        }
        else if(address.equals("") || address.length() < 10)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Full Address");
        }
        else if(postalCode.equals("") || postalCode.length() < 6 || postalCode.length() > 6)
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Postal Code");
        }
        else if(email.equals(""))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Email");
        }
        else if(phone.equals(""))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Phone Number");
        }
        //Validation alphanumeric and specidic characters/patterns
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
        if(!firstName.matches(".[a-zA-Z ]*"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Name");
        }
        if(!address.matches(".[a-zA-Z0-9. ]*"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Full Address");
        }
        if(!postalCode.matches("^[a-zA-z]{1}[0-9]{1}[a-zA-z]{1}[-]{1}[0-9]{1}[a-zA-Z]{1}[0-9]{1}") && !postalCode.matches("^[a-zA-z]{1}[0-9]{1}[a-zA-z]{1}[0-9]{1}[a-zA-Z]{1}[0-9]{1}"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter Your Postal Code With the Dashes Included");
        }
        if(!email.matches("[A-Za-z0-9]{2,30}[@][A-Za-z0-9]{2,20}{1}[.]{1}[A-Za-z]{2,8}"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Valid Email Address");
        }
        if(!phone.matches("^\\d{3}[-]{1}\\d{3}[-]{1}\\d{4}") && !phone.matches("^\\d{3}\\d{3}\\d{4}") && !phone.matches("^\\d{3}[ ]{1}\\d{3}[ ]{1}\\d{4}"))
        {
            errorCheck = true;
            request.setAttribute("error", "Please Enter a Valid Phone Number");
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
                rs.next();
                boolean error = false;
                //check for username in db
                String prepSelectStatement = "Select username from user where username = ?;";
                PreparedStatement ps2 = connection.prepareStatement(prepSelectStatement);
                ps2.setString(1, username);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                int row = rs2.getRow();
                if(row == 1)
                {
                    
                    if(rs2.getString("username").equals(username))
                    {
                        error = true;
                        request.setAttribute("error", "Username is Already in Use!");
                        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    }
                }
                if(!error)
                {
                    
                    int user_id = rs.getInt(1);
                    user_id++;
                    postalCode = postalCode.replace("-",""); 
                    Accounttype at = new Accounttype(1);
                    //Create User
                    User user = new User();
                    user.setUserId(user_id);
                    user.setName(firstName);
                    user.setAddress(address);
                    user.setPostalCode(postalCode);
                    user.setEmail(email);
                    user.setPhoneNo(phone);
                    user.setAccountType(at);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setAccountStatus(true);
                    UserService us = new UserService();
                    try 
                    {
                        us.create(user);
                    } catch (Exception ex) {
                        Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Confirm to user that they have been registered.
                    request.setAttribute("firstname", "");
                    request.setAttribute("lastname", "");
                    request.setAttribute("address", "");
                    request.setAttribute("postal", "");
                    request.setAttribute("email", "");
                    request.setAttribute("phone", "");
                    request.setAttribute("username", "");
                    request.setAttribute("status", "Registration Complete! Please login to begin");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                }
            }
            catch(IOException | ClassNotFoundException | SQLException | ServletException ex)
            {
                
            }
        }
    }
}
