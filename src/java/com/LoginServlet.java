/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;
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
import java.sql.ResultSet;
@WebServlet(name="LoginServlet",urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        
        if (email == null || password == null) {
            out.print("{\"status\":\"error\", \"message\":\"Missing credentials!\"}");
            return;
        }
        
        String sql="SELECT user_id,full_name,role FROM users WHERE email=? AND password=?";
        
        try(Connection con=DBConnection.getConnection();
                PreparedStatement pstmt=con.prepareStatement(sql)){
            
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            
            ResultSet rs=pstmt.executeQuery();
            
            if(rs.next()){
                
                int userId = rs.getInt("user_id");
                String name = rs.getString("full_name");
                String role = rs.getString("role");
                
                // Send a success JSON back to Flutter with the user's role
                out.print("{\"status\":\"success\", "
                        + "\"userId\":" + userId + ", "
                        + "\"name\":\"" + name + "\", "
                        + "\"role\":\"" + role + "\"}");
            }else{
                
                out.print("{\"status\":\"error\", \"message\":\"Invalid email or password!\"}");
            }
        }catch(Exception e){
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
        }
    }
}
