/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 *
 * @authors JZED, TMJ
 */
public class AirTrafficControl {
    
    String[] codes = new String[1];//to airport identifications
    double[][] FTimes = new double[1][];//to Flight Times
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AirTrafficControl atc = new AirTrafficControl();
        
        atc.loadData();
        atc.display();
        
        atc.insertAirport("DTH");
    }
    
    void loadData() {       
        try {
            // Database Cnnection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/air_traffic_control","air_traffic", "123456");
            
            // Load airport data from database
            String sql = "select * from airports order by akey";
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            // String[] codes;//to airport identifications
            // double[][] FTimes;//to Flight Times
            
            // getting rw count
            int count=0;
            while(rst.next()) {
               count++;
            }
            
            
            codes = new String[count]; //initaiaze
            
            //Load airport identifications to array
            while(rst.previous()) {
                count--;
                codes[count] = rst.getString("akey");      
            }
            
            FTimes = new double[codes.length][codes.length];
            
            //Get flight time data from database
            sql = "select * from times order by source";
            rst = st.executeQuery(sql);
            
            //load Flighttime data to array
            while(rst.next())
            {
                int source = Arrays.binarySearch(codes,rst.getString("source"));
                int destination = Arrays.binarySearch(codes,rst.getString("destination"));
                FTimes[source][destination] = rst.getDouble("flight_time");
            }
            
        } catch (SQLException ex) {
            System.out.println("SQL Error "+ ex.getMessage());
        }
    }
    
    void insertAirport(String newCode)
    {
        String[] temp = new String[codes.length+1];
        double[][] tempF = new double[temp.length][temp.length];
        
        /* for(int i=0;i<codes.length;i++) {
            temp[i] = codes[i];
        } */
        System.arraycopy(codes, 0, temp, 0, codes.length);
        
        temp[codes.length] = newCode;
        
        Arrays.sort(temp);
        
        codes = temp;
        
        int addedIndex = Arrays.binarySearch(temp,newCode);
        
        for(int i=0, k=0;i<temp.length;i++)
        {
            for(int j=0,l=0;j<temp.length;j++)
            {
                if(i==addedIndex||j==addedIndex)
                {
                
                }
                else
                {
                    tempF[i][j] = FTimes[k][l] ;
                    l++;
                }
            }
            if(i!=addedIndex)
            {
                k++;
            }
        }
        FTimes = tempF;
    }
    
    void display() {
        System.out.print("\t");
        for (String code : codes) {
            System.out.print(code+"\t");
        }
        
        System.out.println();
        
        for(int i=0; i<codes.length;i++) {
            for(int j=0;j<codes.length;j++) {
                if(j==0) {
                    System.out.print(codes[i]+"\t");
                }
                System.out.print(FTimes[i][j]+"\t");
            }
            System.out.println();
        }
    }
    
}
