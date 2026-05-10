/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.placementsync;

//package com.placementsync.utils;

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


@WebServlet(name = "AddDriveServlet", urlPatterns = {"/AddDriveServlet"})
public class AddDriveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Setup CORS so Flutter Web/Mobile can talk to it
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // 2. Read the text data sent from Flutter
        String companyName = request.getParameter("companyName");
        String jobRole = request.getParameter("jobRole");
        String ctcString = request.getParameter("ctcLpa");
        String cgpaString = request.getParameter("minCgpa");
        String deadlineString = request.getParameter("deadline");

        // 3. Basic Security Check: Did they leave anything blank?
        if (companyName == null || jobRole == null || ctcString == null || cgpaString == null || deadlineString == null) {
            out.print("{\"status\":\"error\", \"message\":\"Missing fields! Please fill the entire form.\"}");
            return;
        }

        try {
            // 4. Convert text numbers into actual Decimals and Dates for MySQL
            double ctcLpa = Double.parseDouble(ctcString);
            double minCgpa = Double.parseDouble(cgpaString);
            java.sql.Date deadline = java.sql.Date.valueOf(deadlineString); // Format must be YYYY-MM-DD

            // 5. The exact SQL Insert command
            String sql = "INSERT INTO placement_drives (company_name, job_role, ctc_lpa, min_cgpa_required, application_deadline) VALUES (?, ?, ?, ?, ?)";

            // 6. Open connection and execute
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, companyName);
                pstmt.setString(2, jobRole);
                pstmt.setDouble(3, ctcLpa);
                pstmt.setDouble(4, minCgpa);
                pstmt.setDate(5, deadline);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    out.print("{\"status\":\"success\", \"message\":\"Drive added successfully!\"}");
                } else {
                    out.print("{\"status\":\"error\", \"message\":\"Failed to add drive to database.\"}");
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Data Conversion Error: Someone typed text in a number field!");
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"CTC and CGPA must be valid numbers!\"}");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Date Conversion Error: Wrong format!");
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"Deadline must be in YYYY-MM-DD format!\"}");
            
        } catch (Exception e) {
            System.out.println("Database Error occurred in AddDriveServlet");
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
        }
    }
}