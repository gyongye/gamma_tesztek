package testcase01.testcase1;

import testcase01.*;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class ExecutionTrace0 {
	
	private static ReflectiveTest01System reflectiveTest01System;
	
	@Before
	public void init() {
		reflectiveTest01System = new ReflectiveTest01System();
	}
	
	@After
	public void tearDown() {
		stop();
	}
	
	// Only for override by potential subclasses
	protected void stop() {
		reflectiveTest01System = null;				
	}
	
	@Test
	public void test() {
		finalStep0();
		return;
	}
	public void step0() {
		// Act
		reflectiveTest01System.reset();
		// Assert
		assertTrue(reflectiveTest01System.getComponent("Test01TS").isStateActive("MainRegion", "_0"));
		assertTrue(reflectiveTest01System.getComponent("system").getComponent("test").isStateActive("main_region", "StateA"));
		assertTrue(reflectiveTest01System.getComponent("system").getComponent("test").checkVariableValue("end", false));
	}
	
	public void step1() {
		step0();
		// Act
		reflectiveTest01System.schedule(null);
		// Assert
		assertTrue(reflectiveTest01System.getComponent("Test01TS").isStateActive("MainRegion", "_1"));
		assertTrue(reflectiveTest01System.getComponent("system").getComponent("test").isStateActive("main_region", "StateB"));
		assertTrue(reflectiveTest01System.getComponent("system").getComponent("test").checkVariableValue("end", false));
	}
	
	public void finalStep0() {
		step1();
		// Act
		reflectiveTest01System.schedule(null);
		// Assert
		assertTrue(reflectiveTest01System.getComponent("Test01TS").isStateActive("MainRegion", "_2"));
		assertTrue(reflectiveTest01System.getComponent("system").getComponent("test").checkVariableValue("end", true));
	}
	
}
