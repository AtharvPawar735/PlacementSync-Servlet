/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.placementsync.utils;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DriveServlet", urlPatterns = {"/DriveServlet"})
public class DriveServlet extends HttpServlet {
    
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        response.setHeader("Access-control-allow-origin","*");
        response.setHeader("Access-control-allow-Methods","GET, OPTIONS");
        
        PrintWriter out=response.getWriter();
        
        try{
            
            DriveDao driveDAO=new DriveDao();
            List<Drive>upcomingDrives=driveDAO.getAllDrives();
            
            Gson gson=new Gson();
            String jsonResponse=gson.toJson(upcomingDrives);
            
            out.print(jsonResponse);       
        }catch(Exception e){
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("failed to fetch placemnt drives");
            e.printStackTrace();
        }finally{
            
            out.flush();
            out.close();
        }
        
    }
}
