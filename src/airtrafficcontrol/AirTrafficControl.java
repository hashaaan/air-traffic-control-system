/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors JZED, TMJ, NIRMALI
 */
public class AirTrafficControl {
    
    String[] codes = new String[1]; //to airport identifications
    double[][] FTimes = new double[1][]; //to Flight Times
    double[][] solution = new double[3][3];
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AirTrafficControl mainData = new AirTrafficControl();
        FindRouteUI fr = new FindRouteUI(mainData);
        
        mainData.loadData();
        
        fr.setVisible(true);
        
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AirTrafficControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(mainData.codes[1]);
        
        // AirTrafficControl atc = new AirTrafficControl();
        
        /*atc.loadData();
        
        atc.insertAirport("DTH");
        atc.insertAirport("KLM");
        
        atc.display();
        
        atc.deleteAirport("DTH");
        atc.insertAirport("THJ");
        atc.insertAirport("VGH");
        
        atc.display();
        
        atc.deleteAirport("CMB");*/
    }
    
    void loadData() {       
        try {
            // Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/center","root", "Tmj.123");
            
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
            while(rst.next()) {
                int source = Arrays.binarySearch(codes,rst.getString("source"));
                int destination = Arrays.binarySearch(codes,rst.getString("destination"));
                FTimes[source][destination] = rst.getDouble("flighttime");
            }
            solution = new double[3][codes.length];
            
        } catch (SQLException ex) {
            System.out.println("SQL Error "+ ex.getMessage());
        }
    }
    
    int codeSearch(String[] array,String key) {
        for(int i=0;i<array.length;i++) {
            if(array[i].equals(key)) {
                return i;
            }
        }
        return 0;
    }
    
    void insertAirport(String newCode) {
        String[] temp = new String[codes.length+1];
        double[][] tempF = new double[temp.length][temp.length];
        
        /* for(int i=0;i<codes.length;i++) {
            temp[i] = codes[i];
        } */
        
        // ( src_arr, src_start_index, des_arr, des_start_index, items_count )
        System.arraycopy(codes, 0, temp, 0, codes.length);
        
        // add item to last element
        temp[codes.length] = newCode;
        
        // sort to asc
        Arrays.sort(temp);
        
        codes = temp;
        
        // ( arr, key ) | to get the arr index
        int addedIndex = Arrays.binarySearch(temp,newCode);
        
        //System.out.println(addedIndex);
        
        for(int i=0, k=0; i<temp.length; i++) {
            for(int j=0, l=0; j<temp.length; j++) {
                if(i==addedIndex||j==addedIndex) {
                    //
                } else {
                    tempF[i][j] = FTimes[k][l] ;
                    l++;
                }
            }
            if(i!=addedIndex) {
                k++;
            }
        }
        
        FTimes = tempF;
    }
    
    void deleteAirport(String deleteValue) {
        int deleteIndex = Arrays.binarySearch(codes,deleteValue);
        
        String[] temp = new String[codes.length-1];
        double[][] tempF = new double[temp.length][temp.length];
        
        System.arraycopy(codes, 0, temp, 0, deleteIndex);
        System.arraycopy(codes,deleteIndex+1, temp,deleteIndex, temp.length-deleteIndex);
        
        codes = temp;
        
         for(int i=0, k=0;i<FTimes.length;i++) {
            for(int j=0,l=0;j<FTimes.length;j++) {
                if(i==deleteIndex||j==deleteIndex) {
                    //
                } else {
                    tempF[k][l] = FTimes[i][j] ;
                    l++;
                }
            }
            if(i!=deleteIndex) {
                k++;
            }
        }
        FTimes = tempF;  
        display(); 
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
    
    //Dijksras logic
     void calc()
    {
        int Nodecount = codes.length; 
        for(int i=0;i<Nodecount;i++)
        {
            System.out.print("\t" + codes[i]);
        }
        System.out.println();
        for(int i = 0; i<Nodecount; i++)
        {
            System.out.print(codes[i]+"\t");
            for(int j = 0; j<Nodecount; j++)
            {
                System.out.print(FTimes[i][j]+"\t");
            }
            System.out.println();
        }
        
        System.out.println("Enter Starting Node");
        String  key = sc.next();
        int start = search(codes,key);
        int[][] solution = new int[3][Nodecount];
        for(int i=0;i<Nodecount;i++)
        {
            solution[1][i] = -1;
        }
        solution[1][start] = 0;
        solution[2][start] = 99;
        solve(Nodecount, start, solution);
        for(int i = 0; i<3; i++)
        {  
            for(int j = 0; j<Nodecount; j++)
            {
                System.out.print(solution[i][j]+"\t");
            }
            System.out.println();
        }
        int dest ;
        System.out.println("Enter Destination");
        key = sc.next();
        dest = search(codes, key);
        System.out.println("distance : " + solution[1][dest]);
        System.out.print("Path : ");
        
    }
    
   
    
    void solve(int size,int start,int[][] solution)
    {
        add(size, start);
        start = NodeN(size);
        if(start != -99)
        {
            solve(size, start, solution);
        }
    }
    
    void add(int size,int start)
    {
        solution[0][start] = 1;
        for(int i =0; i<size;i++)
        {
            if((FTimes[start][i]==0)||(solution[0][i]==1))
            {
                
            }
            else{
                double distance = solution[1][start]+FTimes[start][i];
                if(solution[1][i]==-1)
                {
                    solution[1][i] = distance;
                    solution[2][i] = start;
                }
                else if(solution[1][i]>distance)
                {
                    solution[1][i] = solution[1][start]+FTimes[start][i];
                    solution[2][i] = start;
                }
            }
        }  
    }
    
    int NodeN(int size)
    {
        double min = 0;
        int minlocation = -99,flag=0;
        for(int i=0;i<size;i++)
        {
            if((solution[0][i]==1)||(solution[1][i]==-1))
            {
            
            }
            else
            {
                if(flag==0)
                {
                    min = solution[1][i];
                    minlocation = i;
                    flag = 1;
                }
                else if(min>solution[1][i])
                {
                    min = solution[1][i];
                    minlocation = i;
                }
            }
        }
        return minlocation;
    }
    
    int search(String[] array,String key)
    {
        for(int i=0;i<array.length;i++)
        {
            
            if(array[i].equals(key))
            {
                return i;
            }
        }
        return 0;
    }
    
}
