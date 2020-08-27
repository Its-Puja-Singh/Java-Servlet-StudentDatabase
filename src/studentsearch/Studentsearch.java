/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsearch;

/**
 *
 * @author Kushagra
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Studentsearch
{
JFrame f1;
 JLabel l1,lid,lname,laddress, lblmsg, lblmt, lbl;
 JTextField txtId, tname, taddress;
 JButton btnSearch, btnDelete, btnUpdate;
 JPanel p1;
 Statement st;
 Connection con;
 Studentsearch() throws ClassNotFoundException,SQLException
 {

	 f1=new JFrame("Search Student Record");
	 l1=new JLabel("Enter Student Id");
	 lid=new JLabel("Student Id");
	 lname=new JLabel("Student Name");
	 laddress=new JLabel("Student Address");
	 lblmsg=new JLabel(" ");
	 lblmt=new JLabel("Message:");
	 lbl=new JLabel("~~~~~~~~~~ Enter the details to Update ~~~~~~~~~~");
	 txtId=new JTextField(30);
	 tname=new JTextField(30);
	 taddress=new JTextField(30);
	 btnSearch=new JButton("Search");
	 btnDelete=new JButton("Delete");
	 btnUpdate=new JButton("Update");
	 p1=new JPanel();
	 l1.setBounds(130,30,300,30);
	 txtId.setBounds(290,30,210,30);
	 btnSearch.setBounds(290,70,100,30);
	 btnDelete.setBounds(400,70,100,30);
	 btnUpdate.setBounds(350,110,100,30);
	 p1.setBounds(20,200,500,130);
	 p1.setBackground(Color.green);
	 lid.setBounds(30,20,150,30);
	 lname.setBounds(30,50,250,30);
	 laddress.setBounds(30,80,250,30);
	 lblmsg.setBounds(30,50,250,30);
	 lblmt.setBounds(600,30,400,50);
	 lbl.setBounds(560,200,300,30);
	 tname.setBounds(560,250,300,30);
	 taddress.setBounds(560,300,300,30);


	 f1.add(p1); f1.add(lblmt); f1.add(lbl);
	 f1.add(l1); f1.add(txtId); f1.add(btnSearch); f1.add(btnUpdate);
	 f1.add(btnSearch); f1.add(btnDelete); p1.add(lblmsg);
	 p1.add(lid); p1.add(lname); p1.add(laddress); 
	 f1.add(tname); f1.add(taddress);

	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");

	 btnSearch.addActionListener (new ActionListener()
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 ResultSet rs;
			 try
			 {
			 st=con.createStatement();
			 rs=st.executeQuery("select * from students where std_id='"+Integer.parseInt(txtId.getText())+"'");
			 if(rs.next())
			 {
				 lid.setText("Student Id: "+rs.getInt("std_id"));
				 lname.setText("Student Name: "+rs.getString("name"));
				 laddress.setText("Student Address: "+rs.getString("address"));
				 lblmsg.setText("");
				 lblmt.setText("Your searched Result is shown below:");

			 }
			 else 
			 {
				 JOptionPane.showMessageDialog(null,"Record Not Found","error in id",0);
			 }
 
		 } 
		 catch(SQLException ex)
		 {
			 JOptionPane.showMessageDialog(null,"exception" +ex.toString(),"exception",0);
		 }
		
        }
	
});
btnDelete.addActionListener (new ActionListener()
	 {
		 public void actionPerformed(ActionEvent e)
		 {
             try
{
st=con.createStatement();
st.execute("Delete from students where std_id='" +Integer.parseInt(txtId.getText())+"'");
lblmsg.setText("Record " +txtId.getText() + " has been Successfully Deleted ");
lid.setText("");
lname.setText("");
lblmt.setText("Deleted Successfully");
laddress.setText("");

}
catch(SQLException ex)
{
	lblmsg.setText(ex.toString());

}}});

btnUpdate.addActionListener (new ActionListener()
	 {
		 public void actionPerformed(ActionEvent e)
		 { 
		 	try
{
				st=con.createStatement();
				st.executeQuery("UPDATE students SET Name='"+tname.getText().trim()+"', address= '"+taddress.getText().trim()+"' where std_id= '"+Integer.parseInt(txtId.getText())+"'");
				lblmt.setText("Updated "+txtId.getText()+" Successfully ");
				lblmsg.setText("Record "+txtId.getText()+" has been Successfully Updated ");
				lid.setText("");
                lname.setText("");
                laddress.setText("");
				tname.setText("");
				taddress.setText("");
			}
			catch(SQLException ex)
			{
				lblmt.setText(ex.toString());
			}
		}
	}
	);

f1.setLayout(null);
p1.setLayout(null);
f1.setSize(1050,500);
f1.setVisible(true);
	}
	public static void main (String [] args) throws ClassNotFoundException,Exception
	{
		new Studentsearch();
	} }