/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors JZED, TMJ, NIRMALI
 */
public class AirTrafficControl {
    
    String[] codes = new String[1]; //to airport identifications
    String[][] airports = new String[2][2];
    double[][] FTimes = new double[1][]; //to Flight Times
    double[][] solution = new double[3][3];
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AirTrafficControl mainData = new AirTrafficControl();
        mainData.loadData();
        //FindRouteUI fr = new FindRouteUI(mainData);
        FindRoutesUI frs = new FindRoutesUI(mainData);
        
        //mainData.calc("DEN","AKL");
        //fr.setVisible(true);
          frs.setVisible(true);
        //mainData.printPath(mainData.solution, mainData.codes, Arrays.binarySearch(mainData.codes,"AKL"));
//        try {
//            sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AirTrafficControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println();
//        mainData.display();
        
//        mainData.insertAirport("HK", "Hong Kong International", "Hong Kong");
//        
//        mainData.display();
        
//        mainData.deleteAirport("HK");
//        mainData.display();
//        
//        mainData.updateAirport("MBN", "MBN","Male", "Maildives");
//        mainData.display();
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
            Database db = new Database();
            
            ResultSet rst = db.getAirports();
            
            // getting rw count
            int count=0;
            while(rst.next()) {
               count++;
            }
            
            codes = new String[count]; //initaiaze
            airports = new String[2][count];
            
            //Load airport identifications to array
            while(rst.previous()) {
                count--;
                codes[count] = rst.getString("akey");
                airports[0][count] = rst.getString("title");
                airports[1][count] = rst.getString("country");
            }
            
            FTimes = new double[codes.length][codes.length];
            
            //Get flight time data from database
//            sql = "select * from times order by source";
//            rst = st.executeQuery(sql);
            rst = db.getFTimes();
            
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
    
    void insertAirport(String newCode, String newTitle, String newCountry) {
        String[] temp = new String[codes.length+1];
        String[][] airTemp = new String[2][codes.length+1];
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
        
        for(int i=0,j=0;i<temp.length;i++)
        {
            if(i==addedIndex)
            {
                airTemp[0][i] = newTitle;
                airTemp[1][i] = newCountry;
            }
            else{
                airTemp[0][i] = airports[0][j];
                airTemp[1][i] = airports[1][j];
                j++;
            }
        }
        
        airports = airTemp;
        
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
        Database db = new Database();
        db.insertAirport(newCode, newTitle, newCountry);
    }
    
    void deleteAirport(String deleteValue) {
        int deleteIndex = Arrays.binarySearch(codes,deleteValue);
        
        String[] temp = new String[codes.length-1];
        String[][] airTemp = new String[2][temp.length];
        double[][] tempF = new double[temp.length][temp.length];
        
        System.arraycopy(codes, 0, temp, 0, deleteIndex);
        System.arraycopy(codes,deleteIndex+1, temp,deleteIndex, temp.length-deleteIndex);
        
        for(int i=0,j=0;i<codes.length;i++)
        {
            if(i!=deleteIndex)
            {
                airTemp[0][j] = airports[0][i];
                airTemp[1][j] = airports[1][i];
                j++;
            }
        }
        
        airports = airTemp;
        
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
        
        Database db = new Database();
        db.deleteAirport(deleteValue);
        display(); 
    }
    
    int updateAirport(String oldAkey, String newAkey, String title, String country)
    {
        int index = Arrays.binarySearch(codes, oldAkey);
        codes[index] = newAkey;
        airports[0][index] = title;
        airports[1][index] = country;
        Database db = new Database();
        return db.updateAirport(oldAkey, newAkey, title, country);
    }
    
    int insertFlightTime(String source,String destination,double FTime)
    {
        int src = Arrays.binarySearch(codes,source);
        int dest = Arrays.binarySearch(codes, destination);
        FTimes[src][dest] = FTime;
        Database db = new Database();
        return db.insertFlightTime(source, destination, FTime);
    }
    
    int updateFlightTime(String source,String destination,double FTime)
    {
        int src = Arrays.binarySearch(codes,source);
        int dest = Arrays.binarySearch(codes, destination);
        FTimes[src][dest] = FTime;
        Database db = new Database();
        return db.updateFlightTime(source, destination, FTime);
    }
    
    int deleteFlightTime(String source,String destination)
    {
        int src = Arrays.binarySearch(codes, source);
        int dest = Arrays.binarySearch(codes,destination);
        FTimes[src][dest] = 0;
        Database db = new Database();
        db.deleteFlightTime(source, destination);
        return 0;
    }
    
    void display() 
    {    
        System.out.print("\t");
        for (String code : codes) {
            System.out.print(code+"\t");
        }
        System.out.println();
        for(int i=0; i<2;i++) {
            for(int j=0;j<codes.length;j++) {
                if(j==0) {
                    if(i==0)
                    {
                        System.out.print("Title"+"\t\t");
                    }
                    else
                    {
                        System.out.print("Country"+"\t\t");
                    }
                }
                System.out.print(airports[i][j]+"\t\t");
            }
            System.out.println();
        }
        
        System.out.println();
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
//    This 
//            Part 
//            Implements 
//                    Dijkstra's algorithm
                            
     void calc(String source, String destination)
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
        
        int start = Arrays.binarySearch(codes, source);
        
        for(int i=0;i<Nodecount;i++)
        {
            solution[1][i] = -1;
        }
        solution[1][start] = 0;
        solution[2][start] = 99;
        solve(Nodecount, start);
        for(int i = 0; i<3; i++)
        {  
            for(int j = 0; j<Nodecount; j++)
            {
                System.out.print(solution[i][j]+"\t");
            }
            System.out.println();
        }
        int dest ;
        dest = Arrays.binarySearch(codes, destination);
        System.out.println("distance : " + solution[1][dest]);
        System.out.print("Path : ");
        
    }
    
    void solve(int size,int start)
    {
        add(size, start);
        start = NodeN(size);
        if(start != -99)
        {
            solve(size, start);
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
    
//    int search(String[] array,String key)
//    {
//        for(int i=0;i<array.length;i++)
//        {
//            
//            if(array[i].equals(key))
//            {
//                return i;
//            }
//        }
//        return 0;
//    }
    
    void printPath(double[][] solutions,String[] nodes,int dest)
    {
        System.out.print(nodes[dest]);
        if(solution[1][dest]!=0)
        {
            System.out.print("<-");
            printPath(solution, nodes, (int)solution[2][dest]);
        }
    }
}
