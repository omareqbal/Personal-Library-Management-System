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
public class UserTest {
    
    public UserTest() {
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
     * Test of login method, of class User.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        assertEquals("Owner login",1, User.login("omar", "omar"));
        assertEquals("Invalid owner login",-1, User.login("omar", "1234"));
        assertEquals("Friend login",0, User.login("sarthak", "sarthak"));
        assertEquals("Invalid friend login",-1, User.login("sarthak", "1234"));
        assertEquals("Username does not exist",-1, User.login("omar123", "1234"));
 
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkUser method, of class User.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        assertTrue("User exists",User.checkUser("omar"));
        assertTrue("User exists",User.checkUser("aditya"));
        assertFalse("User does not exist",User.checkUser("omar123"));
        // TODO review the generated test code and remove the default call to fail.
    }

  
    /**
     * Test of search method, of class User.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String name = "a";
        User[] expResult=null;

        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE name LIKE ? AND level=0");
           stmt.setString(1,"%"+name+"%");
           ResultSet rs=stmt.executeQuery();
           if(rs.next()){
               rs.last();
               int count=rs.getRow();
               expResult=new User[count];
               rs.beforeFirst();
               int i=0;
               while(rs.next()){
                   expResult[i]=new User(rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));
                   i++;
               }
           }
           c.close();
        }
        catch(Exception e){
        }
        User[] result = User.search(name);
        assertArrayEquals("Search Friend",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDetails method, of class User.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        String uname = "aditya";
        User expResult = null;
        try{
           Connection c=Connect.getConnection();
           PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE username=?");
           stmt.setString(1,uname);
           ResultSet rs=stmt.executeQuery();
           if(rs.next()){
               expResult=new User(rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));
           }
           c.close();
           
        }
        catch(Exception e){
        }
        User result = User.getDetails(uname);
        assertEquals("Get friend details",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testRegister(){
        System.out.println("Register");
        String uname="omar1";
        String pw="omar";
        String name="omar";
        String email="omar@abc.com";
        String contact="8529637410";
        User.register(uname, pw, name, email, contact);
        User result=null;
        try{
            Connection c=Connect.getConnection();
            PreparedStatement stmt=c.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1,uname);
            ResultSet rs=stmt.executeQuery();
            rs.next();
            result=new User(rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));
            c.close();
        }
        catch(Exception e){
        }
        User expResult=new User(name,uname,email,contact);
        assertEquals(expResult,result);
    }
    
}
