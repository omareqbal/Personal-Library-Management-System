

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
public class Book {
    public int id;
    public String name;
    public String author;
    public String ISBN;
    public String publisher;
    public int year;
    public int availability;
    public Book(int id, String name, String author, String ISBN, String publisher, int year,int availability){
        this.id=id;
        this.name=name;
        this.author=author;
        this.ISBN=ISBN;
        this.publisher=publisher;
        this.year=year;
        this.availability=availability;
    }
    
    public boolean equals(Object obj){
        Book b = (Book) obj;
        boolean check = false;
        if(this.id==b.id&&this.name.equals(b.name)&&this.author.equals(b.author)&&this.ISBN.equals(b.ISBN)
                &&this.publisher.equals(b.publisher)&&this.year==b.year&&this.availability==b.availability){
            check = true;
        }
        return check;
    }
    
    public static int add(String name,String author, String ISBN, String publisher, int year){
       try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("SELECT max(id) FROM books");
          ResultSet rs=stmt.executeQuery();
          int id;
          if(rs.next())
             id=rs.getInt(1)+1;
          else
             id=1;
          stmt=c.prepareStatement("INSERT INTO books (id,name,author,ISBN,publisher,year)"
                         + "VALUES (?,?,?,?,?,?);");
          stmt.setInt(1,id);
          stmt.setString(2,name);
          stmt.setString(3,author);
          stmt.setString(4,ISBN);
          stmt.setString(5,publisher);
          stmt.setInt(6,year);
          stmt.executeUpdate();
          c.close();
          return id;
       }
       catch(Exception e){
          return 0;
       }
    }
     public static Book[] search(String key, String field){
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE "+field+" LIKE ?");
           stmt.setString(1,"%"+key+"%");
           ResultSet rs=stmt.executeQuery();
           Book[] res=null;
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               res=new Book[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   res[i]=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
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
     

    public static Book[] searchByISBN(String ISBN){
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE ISBN=?");
           stmt.setString(1,ISBN);
           ResultSet rs=stmt.executeQuery();
           Book[] res=null;
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               res=new Book[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   res[i]=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
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
    
    public static Book getDetails(int id){
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE id=?");
           stmt.setInt(1,id);
           ResultSet rs=stmt.executeQuery();
           Book res=null;
           if(rs.next()){
               res=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
           }
           c.close();
           return res;
        }
        catch(Exception e){
           return null;
        }
    }
    public static int update(int id,String name,String author, String ISBN, String publisher, int year){
       try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("UPDATE books "
                  + "SET name=?,author=?,ISBN=?,publisher=?,year=? "
                  + "WHERE id=?");
          stmt.setString(1,name);
          stmt.setString(2,author);
          stmt.setString(3,ISBN);
          stmt.setString(4,publisher);
          stmt.setInt(5,year);
          stmt.setInt(6,id);
          stmt.executeUpdate();
          c.close();
          return 1;
       }
       catch(Exception e){
          return 0;
       }
    }
    
    public static int delete(int id){
        try{
          Connection c=Connect.getConnection();
          PreparedStatement stmt=c.prepareStatement("DELETE FROM books WHERE id=?");
          stmt.setInt(1,id);
          stmt.executeUpdate();
          c.close();
          return 1;
       }
       catch(Exception e){
          return 0;
       }
    }
 
}
