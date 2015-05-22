/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client2.main;

import com.eva.me.cm.ConfigUtil;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server2.stub.TeamQueryInterface;

/**
 *
 * @author Levy
 */
public class MainTest {

    public MainTest() {
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
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String hostname = ConfigUtil.getInstance().getProperty("SERVER_HOSTNAME");
        int port = Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT"));
        try {
            Registry registry = LocateRegistry.getRegistry(hostname, port);
            TeamQueryInterface teamQueryInterface = (TeamQueryInterface) registry.lookup("teamQuery");
            if (true) {
                //设置组号为第2组
                String team = "2";
                ArrayList<String> members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                //检查返回的name list是否和数据库里面的数据匹配
                assertTrue(members.contains("李伟"));
                assertTrue(members.contains("许舰"));
                assertTrue(members.contains("王笑盈"));
                assertTrue(members.contains("关晨"));
                assertTrue(members.contains("孙琳"));
                //检查在组号错误时，是否返回empty的name list
                members.clear();
                team = "-1";
                members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                assertTrue(members.isEmpty());
                //检查在license用完之后，是否返回null的name list
                team = "2";
                //将剩余license耗尽（服务端默认为5个license）
                members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                members.clear();
                members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                assertTrue(members == null);
                System.out.println("test of main() done");

            }
        } catch (RemoteException e) {
            e.printStackTrace();
            fail("test of main failed");
        } catch (NotBoundException e) {
            e.printStackTrace();
            fail("test of main failed");
        }
    }

}
