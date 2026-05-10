/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.placementsync.utils;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DriveDao {
    
    
    public List<Drive>getAllDrives() throws SQLException{
        
        List<Drive>driveList=new ArrayList<>();
        
        String sql="SELECT * FROM placement_drives WHERE application_deadline>=CURRENT_DATE ORDER BY application_deadline ASC";
        
        try(
            Connection conn=DBConnection.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery()){
            
            while(rs.next()){
                
                int id=rs.getInt("drive_id");
                String company=rs.getString("company_name");
                String role=rs.getString("job_role");
                double ctc=rs.getDouble("ctc_lpa");
                double minCgpa=rs.getDouble("min_cgpa_required");
                java.sql.Date deadline=rs.getDate("application_deadline");
                
                Drive drive =new Drive(id,company,role,ctc,minCgpa,deadline);
                driveList.add(drive);
                
               System.out.println(driveList);
            }
            
        }catch(Exception e){
            
            System.out.println("Error fetching drives from dtabse");
        }
        
        return driveList;
            
    }
    
}
