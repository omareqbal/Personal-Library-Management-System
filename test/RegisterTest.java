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
public class RegisterTest {
    
    public RegisterTest() {
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

   
    @Test
    public void testGetFlag(){
        System.out.println("getFlag");
        Register r=new Register();
        assertEquals("New user",1,r.getFlag("omar","omar123","omar","omar@gmail.com","9876543210"));
        assertEquals("All details not entered",0,r.getFlag("omar","","","omar@gmail.com",""));
        assertEquals("Name contains special characters",0,r.getFlag("omar@123","omar12","omar","omar@gmail.com","9876543210"));
        assertEquals("Username contains special characters",0,r.getFlag("omar","omar@123","omar","omar@gmail.com","9876543210"));
        assertEquals("Invalid contact Number",0,r.getFlag("omar","omar12","omar12","omar@gmail.com","6543210"));
        assertEquals("Username already exists",0,r.getFlag("omar","omar","omar12","omar@gmail.com","9876543210"));
    }
    
}
