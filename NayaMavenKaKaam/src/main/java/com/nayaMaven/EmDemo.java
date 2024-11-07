package com.nayaMaven;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmDemo {
	
	public static void main(String[] args) {
		
		 SessionFactory factory = new Configuration().configure().buildSessionFactory();
		  
		 Student student1 = new Student();
		 student1.setId(123);
		 student1.setName("Sukesh");
		 student1.setCity("Lahore");
		 
		 Certificate certificate = new Certificate();
		 certificate.setCourse("Android");
		 certificate.setDuration("2 Months");
		 student1.setCerti(certificate);
		 
		 Student student2 = new Student();
		 student2.setId(124);
		 student2.setName("Mukesh");
		 student2.setCity("Bengal");
		 
		 Certificate certificate1 = new Certificate();
		 certificate1.setCourse("Hibernate");
         certificate1.setDuration("1.5 Months");
         student2.setCerti(certificate1);
         
		 Session s = factory.openSession();
		 Transaction tx = s.beginTransaction();
		 
		 s.save(student1);
		 s.save(student2);
		 
		 tx.commit();
		 s.close();
		 factory.close();
		
	}

}
