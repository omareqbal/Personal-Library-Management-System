

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
public class User {
    public String name;
    public String uname;
    public String email;
    public String contact;
    public User(String name, String uname, String email, String contact){
        this.name=name;
        this.uname=uname;
        this.email=email;
        this.contact=contact;
    }
    public boolean equals(Object obj){
        User u = (User) obj;
        boolean check = false;
        if(this.name.equals(u.name)&& this.uname.equals(u.uname)&&this.email.equals(u.email)&&this.contact.equals(u.contact)){
            check = true;
        }
        return check;
    }
    public static int login(String uname,String pw){
        try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE username=?");
          stmt.setString(1,uname);
          ResultSet rs=stmt.executeQuery();
          int level=-1;
          if(rs.next()){
              String password=rs.getString(2);
              if(password.equals(pw)){
                  level=rs.getInt(6);
              }
          }
          c.close();
          return level;
        }
        catch(Exception e){
            return -1;
        }
    }
    
    public static boolean checkUser(String uname){
       try{
         Connection c=Connect.getConnection();
         PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE username=?");
         stmt.setString(1,uname);
         ResultSet rs=stmt.executeQuery();
         boolean check=false;
         if(rs.next()){
           check=true;
         }
         c.close();
         return check;
       }
       catch(Exception e){
            return false;
       }
    }
    
    public static boolean checkPassword(String uname,String pw){
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1, uname);
            ResultSet rs=stmt.executeQuery();
            rs.next();
            String password=rs.getString(2);
            boolean check=false;
            if(password.equals(pw)){
                check=true;
            }
            c.close();
            return check;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public static int changePassword(String uname,String pw){
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("UPDATE users SET password=? WHERE username=?");
            stmt.setString(1, pw);
            stmt.setString(2, uname);
            stmt.executeUpdate();
            c.close();
            return 1;
        }
        catch(Exception e){
            return 0;
        }
    }
    public static int register(String uname,String pw,String name,String email,String contact){
        try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("INSERT INTO users (username,password,name,email,contact)"
                        + "VALUES (?,?,?,?,?);");
          stmt.setString(1,uname);
          stmt.setString(2,pw);
          stmt.setString(3,name);
          stmt.setString(4,email);
          stmt.setString(5,contact);
          stmt.executeUpdate();
          c.close();
          return 1;
        }
        catch(Exception e){
            return 0;
       }
    }
    
    public static User[] search(String name){
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE name LIKE ? AND level=0");
           stmt.setString(1,"%"+name+"%");
           ResultSet rs=stmt.executeQuery();
           User res[]=null;
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               res=new User[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   res[i]=new User(rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));
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
    
    public static User getDetails(String uname){
       try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE username=?");
           stmt.setString(1,uname);
           ResultSet rs=stmt.executeQuery();
           User res=null;
           if(rs.next()){
               res=new User(rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));
           }
           c.close();
           return res;
       }
      catch(Exception e){
           return null;
       }
    }
    
    public static int update(String uname,String name,String email,String contact){
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("UPDATE users "
                   + " SET name=?,email=?,contact=? "
                   + " WHERE username=?");
           stmt.setString(1,name);
           stmt.setString(2,email);
           stmt.setString(3,contact);
           stmt.setString(4,uname);
           stmt.executeUpdate();
           c.close();
           return 1;
       }
      catch(Exception e){
           return 0;
       }
    }
    
}
