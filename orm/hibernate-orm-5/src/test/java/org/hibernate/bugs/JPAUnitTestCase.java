package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.bugs.bean.SomeValue;
import org.hibernate.bugs.entity.TestEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.

	//https://hibernate.atlassian.net/browse/HHH-15097 has break Mariadb UUID Stored as BINARY(16) with AttributeConverter
	//https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#basic-uuid
	//The default UUID mapping is the binary one because it uses a more efficient column storage.
	//Probably break by this commit with VARCHAR (always) : https://github.com/hibernate/hibernate-orm/pull/4860/commits/0ea2c11dff943ae8063fd355d4e7fd6cff9e6a92
	@Test
	public void HHH15097Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		TestEntity e = new TestEntity();
		UUID uuid;
		e.setSomeValue( new SomeValue( uuid = UUID.randomUUID() ) );
		entityManager.persist(e);
		Integer newId = e.getId();
		entityManager.getTransaction().commit();


		// retrieve the record and verify values.
		final TestEntity e2 = entityManager.find( TestEntity.class, newId );
		assertEquals( uuid, e2.getSomeValue().getUuid() );

	}
}
