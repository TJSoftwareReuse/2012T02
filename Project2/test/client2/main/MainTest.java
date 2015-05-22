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
                //�������Ϊ��2��
                String team = "2";
                ArrayList<String> members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                //��鷵�ص�name list�Ƿ�����ݿ����������ƥ��
                assertTrue(members.contains("��ΰ"));
                assertTrue(members.contains("��"));
                assertTrue(members.contains("��Цӯ"));
                assertTrue(members.contains("�س�"));
                assertTrue(members.contains("����"));
                //�������Ŵ���ʱ���Ƿ񷵻�empty��name list
                members.clear();
                team = "-1";
                members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
                assertTrue(members.isEmpty());
                //�����license����֮���Ƿ񷵻�null��name list
                team = "2";
                //��ʣ��license�ľ��������Ĭ��Ϊ5��license��
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
