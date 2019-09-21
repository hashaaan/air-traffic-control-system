/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TMJ
 */
public class Database {
    
    Connection con;
    
    public Database()
    {
        getConnection();
    }
    final void getConnection()
    {   
        try {
            // Tmj's database con
            // con = DriverManager.getConnection("jdbc:mysql://localhost/center","root", "Tmj.123");
            // jzed's databse con
            con = DriverManager.getConnection("jdbc:mysql://localhost/air_traffic_control","air_traffic", "123456");
        } catch (SQLException ex) {
            System.out.println("Connection Error : "+ex.getMessage());
        }
    }
    
    ResultSet getAirports() throws SQLException
    {    
        String sql = "select * from airports order by akey";
        Statement st = con.createStatement();
        return st.executeQuery(sql);
    }
    
    ResultSet getFTimes() throws SQLException
    {
        Statement st = con.createStatement();
        String sql = "select * from times order by source";
        return st.executeQuery(sql);
    }
    
    int insertAirport(String akey, String title, String country)
    {
        try {
            String sql = "insert into airports (`akey`,`title`,`country`) values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,akey);
            st.setString(2,title);
            st.setString(3,country);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int deleteAirport(String akey)
    {
        try {
            String sql = "delete from airports where `akey`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,akey);
            
            deleteAirportFlights(akey);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int updateAirport(String oldAkey, String newAkey, String title, String country)
    {
        try {
            String sql = "update airports set akey=?,title=?,country=? where `akey`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,newAkey);
            st.setString(2, title);
            st.setString(3,country);
            st.setString(4,oldAkey);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int insertFlightTime(String src, String dest, double ftime)
    {
        try {
            String sql = "insert into times(`source`,`destination`,`flighttime`) values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,src);
            st.setString(2, dest);
            st.setDouble(3, ftime);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int deleteFlightTime(String src, String dest)
    {
        try {
            String sql = "delete from times where `source`=? and `destination`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,src);
            st.setString(2,dest);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int updateFlightTime(String src, String dest, double newftime)
    {
        try {
            String sql = "update times set `flighttime`=? where `source`=? and `destination`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(2,src);
            st.setString(3, dest);
            st.setDouble(1, newftime);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int deleteAirportFlights(String akey)
    {
         try {
            String sql = "delete from times where `source`=? or `destination`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,akey);
            st.setString(2,akey);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
}
