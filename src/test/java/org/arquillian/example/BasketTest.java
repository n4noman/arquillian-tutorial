package org.arquillian.example;

import javax.ejb.EJB;
import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BasketTest {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
									.addClasses(Basket.class, OrderRepository.class,SingleOrderRepository.class)
									.addAsManifestResource(EmptyAsset.INSTANCE, "benas.xml");

		return jar;
	}
	
	@Inject
	Basket basket;
	@EJB
	OrderRepository repo;
	
	@Test
	public void placeOrderShouldAddOrder(){
		basket.addItem("Sunglasses");
		basket.placeOrder();
		Assert.assertEquals(1,repo.getOrderCount());
	}
	
}
