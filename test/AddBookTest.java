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
public class AddBookTest {
    
    public AddBookTest() {
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
        System.out.println("Add book");
        AddBook obj=new AddBook("omar");
        assertEquals("New book added",1,obj.getFlag("War and Peace","Tolstoy","7894561230789","Wiley",1900));
        assertEquals("All details not entered",0,obj.getFlag("War and Peace","","7894561230789","Wiley",1900));        
    }
    
}
