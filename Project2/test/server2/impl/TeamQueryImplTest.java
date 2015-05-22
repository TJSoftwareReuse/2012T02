/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server2.impl;

import com.lisence.LicenseManager;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Levy
 */
public class TeamQueryImplTest {
    
    public TeamQueryImplTest() {
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
     * Test of getTeamMember method, of class TeamQueryImpl.
     */
    @Test
    public void testGetTeamMember() throws Exception {
        System.out.println("getTeamMember");
        TeamQueryImpl instance = new TeamQueryImpl();
        ArrayList<String> nameList = new ArrayList<String>();
        nameList=instance.getTeamMember(2);
        assertTrue(nameList.contains("ÀîÎ°"));
        assertTrue(nameList.contains("Ðí½¢"));
        assertTrue(nameList.contains("ÍõÐ¦Ó¯"));
        assertTrue(nameList.contains("¹Ø³¿"));
        assertTrue(nameList.contains("ËïÁÕ"));
        nameList.clear();
        nameList=instance.getTeamMember(-1);
        assertTrue(nameList.isEmpty());
        while(LicenseManager.getInstance().getRestLicenseCapacity()>0){
            nameList=instance.getTeamMember(2);
        }
        nameList.clear();
        nameList=instance.getTeamMember(2);
        assertTrue(nameList==null);
        System.out.println("test of getTeamMember() done");
        
    }
    
}
