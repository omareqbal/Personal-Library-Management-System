/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author DELL
 */
public class IssueReturnTest {
    
    public IssueReturnTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getIssuedBooks method, of class IssueReturn.
     */
    @Test
    public void testGetIssuedBooks() {
        System.out.println("getIssuedBooks");
        String uname = "aditya";
        IssueReturn[] expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM issue WHERE friend_uname=? AND return_date IS NULL");
           User u=User.getDetails(uname);
           stmt.setString(1,u.uname);
           ResultSet rs=stmt.executeQuery();
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               expResult=new IssueReturn[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                     expResult[i]=new IssueReturn(Book.getDetails(rs.getInt(2)),new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(4)));
                    i++;
               }
            }
            c.close();
        }
        catch(Exception e){
        }    
        IssueReturn[] result = IssueReturn.getIssuedBooks(uname);
        assertArrayEquals(expResult, result);
        
        
        uname = "sarthak";
        result = IssueReturn.getIssuedBooks(uname);
        assertNull("No books issued", result);
        // TODO review the generated test code and remove the default call to fail.
    }


    /**
     * Test of getBorrower method, of class IssueReturn.
     */
    @Test
    public void testGetBorrower() {
        System.out.println("getBorrower");
        int id = 1;
        String expResult = "";
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM issue WHERE book_id=? AND return_date IS NULL");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                expResult=rs.getString(3);
            }
            c.close();
        }
        catch(Exception e){
        }
        String result = IssueReturn.getBorrower(id);
        assertEquals("issued",expResult, result);
        
        id = 2;
        result = IssueReturn.getBorrower(id);
        assertNull("available",result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test    
    public void testIssueBook(){
        System.out.println("Issue Book");
        int book_id=6;
        String friend_uname="nikhil";
        IssueReturn.issueBook(book_id,friend_uname);
        IssueReturn result=null;
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM issue WHERE book_id=? AND return_date IS NULL");
            stmt.setInt(1,book_id);
            ResultSet rs=stmt.executeQuery();
            rs.next();
            result=new IssueReturn(Book.getDetails(rs.getInt(2)),new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(4)));
            c.close();
        }
        catch(Exception e){
        }
        
        IssueReturn expResult=new IssueReturn(Book.getDetails(book_id),new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        assertEquals(expResult,result);
        
    }
    
    @Test
    public void testReturnBook(){
        System.out.println("Return Book");
        int book_id=5;
        String friend_uname="nikhil";
        IssueReturn.returnBook(book_id,friend_uname);
        Date result=null;
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM issue WHERE book_id=? ORDER BY sno DESC");
            stmt.setInt(1,book_id);
            ResultSet rs=stmt.executeQuery();
            rs.next();
            result=rs.getDate(4);
            c.close();
        }
        catch(Exception e){
        }

        assertNotNull(result);
    }
    
}
