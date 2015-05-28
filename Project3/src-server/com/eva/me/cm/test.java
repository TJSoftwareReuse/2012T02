/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eva.me.cm;

import java.util.Map;

/**
 *
 * @author Levy
 */
public class test {
    public static void main(String[] args){
        ConfigUtil instance = ConfigUtil.getInstance();
        Map info=instance.showConfigFileContent();
        
    }
}
