package com.edu.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDataOperations
{
	static Connection scon=null;
	
	static ResultSet rs=null;
	static Statement st=null;

		static Scanner sc=new Scanner(System.in);
		static int sid;
		static String sname;
		static String scourse;

	public static void insertStudentInfo() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("My connection"+scon);
			
			//Input data from user
			System.out.println("Enter id of student");
			 sid=sc.nextInt();
			//Check id exists
			String sql="select * from edustudent where sid= "+sid;
			rs=st.executeQuery(sql);
			
			if(!rs.next()) {
				System.out.println("Enter student name");
				sname=sc.next();
				System.out.println("Enter student Course name");
				scourse=sc.next();
				
		String ins="insert into edustudent values("+sid+",'"+sname+"','"+scourse+"')";
				int  i =st.executeUpdate(ins);
				if(i>0) {
					System.out.println("Student information is registered");
				}
			}else {
				System.out.println("Id already exists Choose another id");
			}
} catch (Exception e) {
			
			e.printStackTrace();
		}  

		
	}

	public static void updateStudentInfo()
	{
		System.out.println("enter id to update record");
		int id=sc.nextInt();
		try {
			
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			String sql="select * from edustudent where sid= "+id;
			rs=st.executeQuery(sql);
			if(rs.next())
			{
				System.out.println("which field you want to update");
				System.out.println("1. to update name");
				System.out.println("2. to update course");
				System.out.println("3. to update name and course");
				
				int choice=sc.nextInt();
				switch(choice)
				{ case 1:System.out.println("enter name to update record");
				         String name=sc.next();
			             String upname="update edustudent set sname='"+name+"'where sid="+id;
			             int i=st.executeUpdate(upname);
			 			if(i>0)
			 			{
			 				System.out.println("record is updated");
			 			}
			 			break;
				case 2:System.out.println("enter name to update record");
				       String course=sc.next();
			           String upcourse="update edustudent set scourse='"+course+"'where sid="+id;
				       int j=st.executeUpdate(upcourse);
			           if(j>0)
			           {
				       System.out.println("record is updated");
		               }
			           break;
				case 3:System.out.println("name and course to update record");
				       System.out.println("enter the name: ");
		               String sname=sc.next();
		               System.out.println("enter the course: ");
		               String scourse=sc.next();
	                   String upnamecourse="update edustudent set sname=+'"+sname+"',scourse=+'"+scourse+"'where sid= "+id;
	                   int k=st.executeUpdate(upnamecourse);
	 			       if(k>0){
	 				System.out.println("record is updated");}
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void deleteStudentInfo() 
	{
		
		System.out.println("enter id to delete record");
		int id=sc.nextInt();
		try {
			
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			String sql="select * from edustudent where sid="+id;
			rs=st.executeQuery(sql);
			if(rs.next())
			{
		    String del="delete from edustudent where sid="+id;
			
			int i=st.executeUpdate(del);
			if(i>0)
			{
				System.out.println("record is deleted");
			}
			}
			else
			{
				System.out.println("record not exists");
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}
   public static void selectStudentInfo()
	{
		System.out.println("enter id to select record");
		int id=sc.nextInt();
	
     try {
			
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			String sql1="select * from edustudent where sid ="+id;
			rs=st.executeQuery(sql1);
			if(rs.next()) {
				id=rs.getInt(1);
				String name=rs.getString(2);
				String course=rs.getString(3);
				System.out.println(id+"\t"+name+"\t"+course);
			}
}catch(Exception e) {
	e.printStackTrace();
}}
	public static void displayStudentInfo()
	{
		scon=DbConnect.getConnection();
		System.out.println("My connection"+scon);
		try {
			st=scon.createStatement();
			String sql="select * from edustudent ";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String course=rs.getString(3);
				System.out.println(id+"\t\t"+name+"\t\t"+course);
			}
			
			rs=st.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}


}
