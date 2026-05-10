
import com.placementsync.utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name="SignupServlet",urlPatterns={"/SignupServlet"})
public class SignupServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        
        PrintWriter out=response.getWriter();
        
        String fullName=request.getParameter("fullName");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        
        if(fullName==null||email==null||password==null||role==null){
            out.print("{\"status\":\"error\", \"message\":\"Missing fields!\"}");
            return;
        }
        
        String sql="INSERT INTO users(full_name,email,password,role)VALUES(?,?,?,?)";
        try(Connection con=DBConnection.getConnection();
                PreparedStatement pstmt=con.prepareStatement(sql)){
            
            pstmt.setString(1,fullName);
            pstmt.setString(2,email);
            pstmt.setString(3,password);
            pstmt.setString(4,role);
            
            int rowsAffected=pstmt.executeUpdate();
            
            if(rowsAffected>0){
              out.print("{\"status\":\"success\", \"message\":\"Account created successfully!\"}");
            } else {
                out.print("{\"status\":\"error\", \"message\":\"Failed to create account.\"}");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            // MySQL Error 1062 happens when someone tries to use an email that already exists
            if (e.getMessage().contains("Duplicate entry")) {
                out.print("{\"status\":\"error\", \"message\":\"Email already registered!\"}");
            } else {
                out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            }
        }
    }
}
