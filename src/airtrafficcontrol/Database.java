/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author TMJ
 */
public class Database {
    Connection getConnection()
    {   
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/center","root", "Tmj.123");
        } catch (SQLException ex) {
            System.out.println("Connection Error : "+ex.getMessage());
        }
        return con;
    }
    
    int insertAirport(String akey, String title, String country)
    {
        try {
            Connection con = getConnection();
            String sql = "insert into airports (`akey`,`title`,`country`) values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(0,akey);
            st.setString(1,title);
            st.setString(2,country);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int deleteAirport(String akey)
    {
        try {
            Connection con = getConnection();
            String sql = "delete from airports where `akey`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(0,akey);
            
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
            Connection con = getConnection();
            String sql = "update airports set akey=?,title=?,country=? where `akey`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(0,newAkey);
            st.setString(1, title);
            st.setString(2,country);
            st.setString(3,oldAkey);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int insertFlightTime(String src, String dest, double ftime)
    {
        try {
            Connection con = getConnection();
            String sql = "insert into times(`source`,`destination`,`flighttime`) values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(0,src);
            st.setString(1, dest);
            st.setDouble(2, ftime);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int deleteFlightTime(String src, String dest)
    {
        try {
            Connection con = getConnection();
            String sql = "delete from times where `source`=? and `destination`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(0,src);
            st.setString(1,dest);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int updateFlightTime(String src, String dest, double newftime)
    {
        try {
            Connection con = getConnection();
            String sql = "update times set `flighttime`=? where `source`=? and `destination`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,src);
            st.setString(2, dest);
            st.setDouble(0, newftime);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
    
    int deleteAirportFlights(String akey)
    {
         try {
            Connection con = getConnection();
            String sql = "delete from times where `source`=? or `destination`=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(0,akey);
            st.setString(1,akey);
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
        return 0;
    }
}
