/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
import java.sql.*;
public class Feedback {
    String friend;
    int rating;
    String comments;
    public Feedback(String friend,int rating, String comments){
        this.friend=friend;
        this.rating=rating;
        this.comments=comments;
    }
    public static int giveFeedback(String uname,int rating, String comments){
       try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("INSERT INTO feedback (uname,rating,comments)"
                         + "VALUES (?,?,?);");
          stmt.setString(1,uname);
          stmt.setInt(2,rating);
          stmt.setString(3,comments);      
          stmt.executeUpdate();
          c.close();
          return 1;
       }
       catch(Exception e){
          return 0;
       }
    }
 
    public static Feedback[] viewFeedback(){
        try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("SELECT * FROM feedback;");
          ResultSet rs=stmt.executeQuery();
          Feedback res[]=null;
          if(rs.next()){
               rs.last();
               int count=rs.getRow();
               res=new Feedback[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   res[i]=new Feedback(rs.getString(2),rs.getInt(3),rs.getString(4));
                   i++;
               }
          }
          c.close();
          return res;
        }
        catch(Exception e){
          return null;
       }
    }
    
    public static double getAvgRating(){
        try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("SELECT ROUND(AVG(rating),2) FROM feedback;");
          ResultSet rs=stmt.executeQuery();
          double r=0;
          if(rs.next()){
               r=rs.getDouble(1);
          }
          c.close();
          return r;
        }
        catch(Exception e){
          return 0;
       }
    }
}
