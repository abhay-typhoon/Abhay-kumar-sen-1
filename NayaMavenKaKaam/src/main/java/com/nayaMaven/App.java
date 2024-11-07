package com.nayaMaven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
      
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        
        Address ad = new Address();
        ad.setStreet("street1");
        ad.setCity("DELHI");
        ad.setOpen(true);
        ad.setAddedDate(new Date());
        ad.setX(123.234);
        
        FileInputStream fis = new FileInputStream("src/main/java/Sql.jpg");
        byte[] data= new byte[fis.available()];
        fis.read(data);
        ad.setImage(data);
        
        
        
        Student st = new Student();
        st.setId(102);
        st.setCity("faridbad");
        st.setName("Abhay");
        System.out.println(st);
        
        
        
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        //session.save(st);
        session.save(ad);
        tx.commit();
        session.close();
        
        
        
        
    }
}
