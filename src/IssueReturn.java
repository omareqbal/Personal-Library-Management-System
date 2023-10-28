

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
import java.text.SimpleDateFormat;  
public class IssueReturn {
    Book book;
    String issueDate;
    public IssueReturn(Book book, String issueDate){
        this.book=book;
        this.issueDate=issueDate;
    }
    
    public boolean equals(Object obj){
        IssueReturn b = (IssueReturn) obj;
        boolean check = false;
        if(this.book.equals(b.book)&&this.issueDate.equals(b.issueDate)){
            check = true;
        }
        return check;
    }

    public static IssueReturn[] getIssuedBooks(String uname){
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM issue WHERE friend_uname=? AND return_date IS NULL");
           User u=User.getDetails(uname);
           stmt.setString(1,u.uname);
           ResultSet rs=stmt.executeQuery();
           IssueReturn[] res=null;
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               res=new IssueReturn[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                     res[i]=new IssueReturn(Book.getDetails(rs.getInt(2)),new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(4)));
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
    public static int issueBook(int book_id, String friend_uname){
       try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("INSERT INTO issue "
                   + "(book_id,friend_uname,issue_date) VALUES(?,?,CURRENT_DATE())");
           stmt.setInt(1,book_id);
           stmt.setString(2,friend_uname);
           stmt.executeUpdate();
           stmt=c.prepareStatement("UPDATE books SET availability=0 WHERE id=?");
           stmt.setInt(1,book_id);
           stmt.executeUpdate();
           c.close();
           return 1;
       }
       catch(Exception e){
        return 0;
       }
    }
    public static int returnBook(int book_id, String friend_uname){
       try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("UPDATE issue "
                   + "SET return_date=CURRENT_DATE() WHERE book_id=? AND friend_uname=? AND return_date IS NULL");
           stmt.setInt(1,book_id);
           stmt.setString(2,friend_uname);
           stmt.executeUpdate();
           stmt=c.prepareStatement("UPDATE books SET availability=1 WHERE id=?");
           stmt.setInt(1,book_id);
           stmt.executeUpdate();
           c.close();
           return 1;
       }
       catch(Exception e){
           return 0;
       }
    }
    
    public static String getBorrower(int id){
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM issue WHERE book_id=? AND return_date IS NULL");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            String b=null;
            if(rs.next()){
                b=rs.getString(3);
            }
            c.close();
            return b;
        }
        catch(Exception e){
           return null;
        }
    }
}
