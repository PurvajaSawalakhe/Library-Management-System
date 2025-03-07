import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

class Portal extends JFrame implements ActionListener
{

	JButton b1,b2,b3,b4;
	Portal()
	{
		super("Portal");
		setSize(500,600);
		
		JLabel b=new JLabel(new ImageIcon("C:\\Users\\purvja\\Desktop\\bk.png"));
		b.setBounds(0,0,500,600);
		add(b);
		
		JLabel l=new JLabel("Details");
		l.setFont(new Font("Arial", Font.BOLD, 22));
		l.setBounds(200,100,100,30);

		b1=new JButton("Issue");
		b1.setFont(new Font("Arial", Font.BOLD, 15));
		b1.setBackground(Color.gray);
		b1.setForeground(Color.white);
		b1.setBounds(150,150,200,50);
		b1.addActionListener(this);

		b2=new JButton("Return");
		b2.setFont(new Font("Arial", Font.BOLD, 15));
		b2.setBackground(Color.gray);
		b2.setForeground(Color.white);
		b2.setBounds(150,250,200,50);
		b2.addActionListener(this);
		
		b3=new JButton("Book");
		b3.setFont(new Font("Arial", Font.BOLD, 15));
		b3.setBackground(Color.DARK_GRAY);
		b3.setForeground(Color.white);
		b3.setBounds(150,350,200,50);
		b3.addActionListener(this);
		
		b4=new JButton("Student");
		b4.setFont(new Font("Arial", Font.BOLD, 15));
		b4.setBackground(Color.black);
		b4.setForeground(Color.white);
		b4.setBounds(150,450,200,50);
		b4.addActionListener(this);

		b.add(l);
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b4);
		
		b.setLayout(null);
		setUndecorated(true);
		setBackground(new Color(0, 255, 0, 1));
		setLocationRelativeTo(null); 
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==b1)
			new Student();	
		else if(e.getSource()==b2)
			new Return();
		else if(e.getSource()==b3)
			new Book();
		else
			new Occupy();
	}

}


class Student extends JFrame implements ActionListener 
{

	JTextField l,t;
	JLabel d,d2;
	
	Student() 
	{

		super("Student Details");	
		l=new JTextField("Book ID");
		l.setBounds(150,50,200,50);
		t=new JTextField("Name of Reciepent ...");
		t.setBounds(150,150,200,50);
		Calendar calendar = Calendar.getInstance(); 
		d=new JLabel("Today : "+calendar.getTime());
		d.setBounds(150,250,300,30);
		calendar.add(Calendar.DATE, +30);  
		d2=new JLabel("Expiry : "+calendar.getTime());
		d2.setBounds(150,350,300,30);
	
		JButton b1=new JButton("Submit");
		b1.setFont(new Font("Arial", Font.BOLD, 15));
		b1.setBackground(Color.DARK_GRAY);
		b1.setForeground(Color.white);
		b1.setBounds(200,450,100,50);
		b1.addActionListener(this);

		add(l);
		add(t);
		add(d);
		add(d2);
		add(b1);
		setBackground(Color.white);
		setLocationRelativeTo(null); 
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
		setLayout(null);
		setSize(500,600);
		setLocationRelativeTo(null); 
		setVisible(true);		

	}

	public void actionPerformed(ActionEvent e)
	{

		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","root");
			Statement st=con.createStatement();
			st.executeUpdate("create table if not exists issue(bid varchar(20),name varchar(20),date varchar(100),expiry varchar(100));");
			st.executeUpdate("insert into issue values('"+l.getText().toString()+"','"+t.getText().toString()+"','"+d.getText().toString()+"','"+d2.getText().toString()+"');");
			con.close();
		}
		catch(Exception ex)
		{System.out.println(ex);}
	}

}

class Book extends JFrame implements ActionListener
{
	JTextField l;
	JLabel r;
	
	Book()
	{

		super("Book Details");	
		l=new JTextField("Book ID");
		l.setBounds(150,200,200,50);
	
		r=new JLabel("Name Of Recepient..");
		r.setBounds(200,250,200,50);
		
		JButton b1=new JButton("Submit");
		b1.setFont(new Font("Arial", Font.BOLD, 15));
		b1.setBackground(Color.DARK_GRAY);
		b1.setForeground(Color.white);
		b1.setBounds(200,300,100,50);
		b1.addActionListener(this);

		add(l);
		add(b1);
		add(r);
		setLayout(null);
		setBackground(Color.white);
		setLocationRelativeTo(null); 
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
		setSize(500,500);
		setLocationRelativeTo(null); 
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e)
	{

		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from issue where bid='"+l.getText().toString()+"';");
			rs.next();
			r.setText("Occupied by , "+rs.getString(2));
			con.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}

class Return extends JFrame implements ActionListener
{
	JTextField l;
	JLabel r;
	JButton b1;
	
	Return()
	{

		super("Book Details");	
		l=new JTextField("Book ID");
		l.setBounds(150,200,200,50);
		
		r=new JLabel("Name Of Recepient..");
		r.setBounds(200,250,200,50);
	
		b1=new JButton("Submit");
		b1.setBounds(200,300,100,50);
		b1.addActionListener(this);

		add(l);
		add(b1);
		add(r);
		setLayout(null);
		setBackground(Color.white);
		setLocationRelativeTo(null); 
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
		setSize(500,500);
		setLocationRelativeTo(null); 
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(b1.getText().equalsIgnoreCase("Submit"))
		{
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","root");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from issue where bid='"+l.getText().toString()+"';");
				rs.next();
				r.setText(rs.getString(2));
				con.close();
				b1.setText("Return");
			}
			catch(Exception ex)
			{}
		}
		else
		{
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","root");
				Statement st=con.createStatement();
				st.executeUpdate("delete from issue where bid='"+l.getText().toString()+"' and name='"+r.getText().toString()+"';");
				con.close();
				JOptionPane.showMessageDialog(null,"Returned...");
				b1.setText("Submit");
			}
			catch(Exception ex)
			{System.out.print(ex);}
		}
	}
	
}


class Occupy extends JFrame implements ActionListener
{
	JTextField l;
	JFrame f=new JFrame("Student Occupation");
	JButton b1;
	
	Occupy()
	{	
		l=new JTextField("Name");
		l.setBounds(150,50,200,50);
	
		b1=new JButton("Submit");
		b1.setBounds(200,400,100,50);
		b1.addActionListener(this);

		f.add(l);
		f.add(b1);
		f.setLayout(null);
		f.setBackground(Color.white);
		f.setLocationRelativeTo(null); 
		f.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
		f.setSize(500,600);
		f.setLocationRelativeTo(null); 
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int x,y,i=0;
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","root");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from issue where name='"+l.getText().toString()+"';");
				
				f.setVisible(false);
				x=250;
				y=200;
				JLabel ar[]=new JLabel[6];
				while(rs.next())
				{
					ar[i]=new JLabel(rs.getString(1));
					ar[i].setBounds(x,y,200,50);
					f.add(ar[i]);
					y+=50;
					i++;
				}
				f.setVisible(true);
				con.close();
			}
			catch(Exception ex)
			{}
		
	}
	
}



public class Library
{
	public static void main(String ar[])throws Exception
	{
		Portal p=new Portal();
	}
}