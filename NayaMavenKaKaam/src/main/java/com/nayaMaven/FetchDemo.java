package com.nayaMaven;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchDemo {
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Student student=(Student)session.get(Student.class, 101);
		System.out.println(student);
		
		Address ad = (Address)session.get (Address.class, 5);
		System.out.println(ad.getStreet()+ " : "+ad.getCity());
		
		session.close();
		factory.close();
		
	}

}
