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

/**
 *
 * @author DELL
 */
public class BookTest {
    
    public BookTest() {
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
     * Test of search method, of class Book.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String key = "formal";
        String field = "name";
        Book[] expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE "+field+" LIKE ?");
           stmt.setString(1,"%"+key+"%");
           ResultSet rs=stmt.executeQuery();
          
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               expResult=new Book[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   expResult[i]=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
                   i++;
               }
           }
           c.close();
         
        }
        catch(Exception e){          
        }
        Book[] result = Book.search(key, field);
        assertArrayEquals("Search by name",expResult, result);

        
        key = "h";
        field = "author";
        expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE "+field+" LIKE ?");
           stmt.setString(1,"%"+key+"%");
           ResultSet rs=stmt.executeQuery();
          
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               expResult=new Book[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   expResult[i]=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
                   i++;
               }
           }
           c.close();
         
        }
        catch(Exception e){          
        }
        result = Book.search(key, field);
        assertArrayEquals("Search by author",expResult, result);
 
        
        key = "xyz";
        field = "publisher";
        expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE "+field+" LIKE ?");
           stmt.setString(1,"%"+key+"%");
           ResultSet rs=stmt.executeQuery();
          
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               expResult=new Book[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   expResult[i]=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
                   i++;
               }
           }
           c.close();
         
        }
        catch(Exception e){          
        }
        result = Book.search(key, field);
        assertArrayEquals("Search by publisher",expResult, result);
        
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of searchByISBN method, of class Book.
     */
    @Test
    public void testSearchByISBN() {
        System.out.println("searchByISBN");
        String ISBN = "";
        Book[] expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE ISBN=?");
           stmt.setString(1,ISBN);
           ResultSet rs=stmt.executeQuery();
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               expResult=new Book[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   expResult[i]=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
                   i++;
               }
               
           }
           c.close();
        }
        catch(Exception e){
        }
        Book[] result = Book.searchByISBN(ISBN);
        assertArrayEquals("Search by ISBN",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
  
    }

    /**
     * Test of getDetails method, of class Book.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        int id = 2;
        Book expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE id=?");
           stmt.setInt(1,id);
           ResultSet rs=stmt.executeQuery();
           if(rs.next()){
               expResult=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
           }
           c.close();
          
        }
        catch(Exception e){
           
        }
        Book result = Book.getDetails(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    @Test
    public void testAdd(){
        System.out.println("Add book");
        String name="Xyz";
        String author="abc";
        String ISBN="7894561230852";
        String publisher="qwe";
        int year=2012;
        
        int id=Book.add(name,author,ISBN,publisher,year);
        Book result=null;
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE id=?");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                result=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
            }
            c.close();
        }
        catch(Exception e){
        }
        
        Book expResult=new Book(id,name,author,ISBN,publisher,year,1);
        assertEquals(expResult, result);
        
    }
 
    @Test
    public void testDelete(){
        System.out.println("Delete Book");
        int id=12;
        Book.delete(id);
        Book result=null;
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM books WHERE id=?");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                result=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
            }
            c.close();
        }
        catch(Exception e){
        }
        assertNull(result);
    }
    
}
