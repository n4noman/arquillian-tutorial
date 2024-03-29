package org.arquillian.example;

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
public class GreeterTest {
	
	@Inject
	Greeter greeter;
	
	@Test
	public void greeterTest(){
		Assert.assertEquals("Hello, Ahmad!", greeter.createGreeting("Ahmad"));
		greeter.greet(System.out,"Ahmad");
	}
	
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
						.addClasses(Greeter.class,PhraseBuilder.class)
						.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}
}
