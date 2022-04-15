package myproject.onetomany.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import myproject.onetomany.entity.Department;
import myproject.onetomany.entity.Employee;

public class Test {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/myproject/onetomany/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Employee e1 = new Employee();
			e1.setEno(111);
			e1.setEname("AAA");
			e1.setEsal(5000);
			e1.setEaddr("IND");
			
			
			Employee e2 = new Employee();
			e2.setEno(222);
			e2.setEname("BBB");
			e2.setEsal(6000);
			e2.setEaddr("BPL");
			
			Employee e3 = new Employee();
			e3.setEno(333);
			e3.setEname("CCC");
			e3.setEsal(7000);
			e3.setEaddr("MUM");
			
			Set<Employee> emps = new HashSet<Employee>();
			emps.add(e1);
			emps.add(e2);
			emps.add(e3);
			
			Department dept = new Department();
			dept.setDid("D-111");
			dept.setDname("Admin");
			dept.setEmps(emps);
			
			String pk = (String)session.save(dept);
			System.out.println("Department PK : "+pk);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
		
		
		
	}
}
