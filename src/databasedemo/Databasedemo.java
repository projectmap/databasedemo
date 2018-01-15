/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasedemo;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author surendra
 */
public class Databasedemo {
    int i,j,k;
   String l;
      Connection con;
   
   public void createdatabase(int a,int b,int c,String s)throws Exception{
    // Class.forName("org.apache.derby.jdbc.ClientDriver");//auto loading of driver...
    i=a;//ID no
    j=b;//amounr deposit
    k=c;//ac no
    l=s;//name
    
   
        con=DriverManager.getConnection("jdbc:derby://localhost:1527/ss","hr","hr");  
  
  
   PreparedStatement p=con.prepareStatement("insert into deposit (id,ac,amnt,name) values(?,?,?,?)");
   p.setInt(1,i);
   p.setString(4, l);
   p.setInt(3,j);
   p.setInt(2, k);
   p.executeUpdate();
   JOptionPane.showMessageDialog(null,"Dear "+l+" Thank you for creating your account at Demodata Bank of Nepal");
   p.close();
   con.close();
   }

    /**
     *
     * @param i
     * @param j
     * @param jt
     * @throws Exception
     */
    public void deposit(int i,int j,JTextField jt)throws Exception  {
        int sum=0;
       int a=i;//ID no.
       int b=j;//amount deposit......
       JTextField jt1=jt;
    Class.forName("org.apache.derby.jdbc.ClientDriver");
 con=DriverManager.getConnection("jdbc:derby://localhost:1527/ss","hr","hr");
  
     Statement st=con.createStatement();
      
     //ResultSet rs= st.executeQuery("select amnt from deposit where id="+i);//both can be done...
       ResultSet rs= st.executeQuery("select * from deposit where id="+i);
     rs.next(); 
      // System.out.println(rs.getInt("amnt"));//it works....
      // System.out.println(rs.getInt(4));//what the fuck...........
      int amnt=rs.getInt("amnt");
      int ac=rs.getInt("ac"); 
      String ss=rs.getString("name");  
       
       sum=amnt+j;
      // PreparedStatement s=con.prepareStatement("update deposit set id='"+i+"', ac='"+ac+"', amnt='"+sum+"',name='"+ss+"' where id="+i);//fucking
      //fucking hell....
       PreparedStatement s=con.prepareStatement("update deposit set id=?, ac=?, amnt=?, name=? where id="+i);
       s.setInt(1,i);
         s.setInt(2,ac);
        s.setInt(3,sum);
       s.setString(4,ss);
       JOptionPane.showMessageDialog(null,"Transaction Successfull..."
                                           +ss+","+ "your new balance is Rs."+sum);
    jt1.setText("Rs."+sum);
      s.execute();
      con.close();
      s.close();
     st.close();
    
    }
    void delete(int a){
        int j=a;
        
        try{
         Connection cn=DriverManager.getConnection("jdbc:derby://localhost:1527/ss","hr","hr");
    int k=JOptionPane.showConfirmDialog(null,"Do you want to delete account with ID="+j+"?"); 
     if(k==0){
         Statement s=cn.createStatement();
    
        ResultSet rs=s.executeQuery("select * from deposit where id="+j);
        rs.next();
        int amnt=rs.getInt("amnt");//amount balance..
        
        
        s.executeUpdate("delete from deposit where id="+j);
        JOptionPane.showMessageDialog(null,"Your account has been deleted..and your balance of Rs."+amnt  +" is provided..");
      
     }//else if(k==1){
         
        // System.out.println("not deleted");}
     
     //else if(k==2){
         //System.out.println("cancel");
     //}
    cn.close();
        }catch(Exception e){
        
        JOptionPane.showMessageDialog(null,"sorry your request is not completed..");
   
        }
        
    }
    
}