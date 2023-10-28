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

/**
 *
 * @author DELL
 */
public class LoginTest {
    
    public LoginTest() {
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
     * Test of getFlag method, of class Login.
     */
    @Test
    public void testGetFlag() {
        System.out.println("getFlag");
        Login instance = new Login();
        instance.s1="omar";
        instance.s2="omar";
        int expResult = 1;
        int result = instance.getFlag();
        assertEquals("Owner login",expResult, result);
        
        instance.s1="aditya";
        instance.s2="aditya";
        expResult = 0;
        result = instance.getFlag();
        assertEquals("Friend Login",expResult, result);
        
        instance.s1="omar";
        instance.s2="1234";
        expResult = -1;
        result = instance.getFlag();
        assertEquals("Invalid owner login",expResult, result);

        instance.s1="aditya";
        instance.s2="1234";
        expResult = -1;
        assertEquals("Invalid friend login",expResult,result);
        
        instance.s1="omar123";
        instance.s2="1234";
        expResult = -1;
        assertEquals("Username does not exist",expResult,result);
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of main method, of class Login.
     */

    
}
