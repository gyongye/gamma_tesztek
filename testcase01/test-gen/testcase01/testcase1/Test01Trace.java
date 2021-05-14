package testcase01.testcase1;

import testcase01.*;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class Test01Trace {
	
	private static ReflectiveSystem reflectiveSystem;
	
	@Before
	public void init() {
		reflectiveSystem = new ReflectiveSystem();
	}
	
	@After
	public void tearDown() {
		stop();
	}
	
	// Only for override by potential subclasses
	protected void stop() {
		reflectiveSystem = null;				
	}
	
	@Test
	public void test() {
		finalStep0();
		return;
	}
	public void step0() {
		// Act
		reflectiveSystem.reset();
		// Assert
		assertTrue(reflectiveSystem.getComponent("test").isStateActive("main_region", "StateA"));
		assertTrue(reflectiveSystem.getComponent("test").checkVariableValue("end", false));
	}
	
	public void step1() {
		step0();
		// Act
		reflectiveSystem.raiseEvent("command", "S1", new Object[] {});
		reflectiveSystem.schedule(null);
		// Assert
		assertTrue(reflectiveSystem.getComponent("test").isStateActive("main_region", "StateB"));
		assertTrue(reflectiveSystem.getComponent("test").checkVariableValue("end", false));
	}
	
	public void finalStep0() {
		step1();
		// Act
		reflectiveSystem.raiseEvent("command", "S2", new Object[] {});
		reflectiveSystem.schedule(null);
		// Assert
		assertTrue(reflectiveSystem.getComponent("test").checkVariableValue("end", true));
	}
	
}
