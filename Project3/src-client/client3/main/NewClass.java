/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client3.main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Levy
 */
public class NewClass {
    public static void main(String[] args){
        Pattern p=Pattern.compile("[1-9]");
        String s= new Scanner(System.in).nextLine();
        Matcher isNum=p.matcher(s);
        System.out.println(isNum.matches());
        
    }
}
