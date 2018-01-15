/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasedemo;
import javax.swing.*;
import java.util.*;
/**
 *
 * @author surendra
 */
public class Timedemo extends  Thread {
    
    JTextField jt;
    void send(JTextField j){
        jt=j;
    }
       
       
    
     public void run(){
        
      
        for(;;){
Date d=new Date();

        try{
        sleep(1000);}
        catch(Exception e){
            jt.setText(""+e);
        }
       
        jt.setText(""+d);
       
     }
    

}}