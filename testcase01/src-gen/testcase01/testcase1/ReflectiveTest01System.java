package testcase01.testcase1;

import testcase01.*;
import testcase01.test01ts.*;
import testcase01.testcase1.*;

public class ReflectiveTest01System implements ReflectiveComponentInterface {
	
	private Test01System wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface test01TS = null;
	private ReflectiveComponentInterface system = null;
	
	
	public ReflectiveTest01System() {
		wrappedComponent = new Test01System();
	}
	
	public ReflectiveTest01System(Test01System wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Test01System getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] {  };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			default:
				throw new IllegalArgumentException("Not known port: " + port);
		}
	}
	
	public void raiseEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			default:
				throw new IllegalArgumentException("Not known port-in event combination: " + portEvent);
		}
	}
	
	public boolean isRaisedEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			default:
				throw new IllegalArgumentException("Not known port-out event combination: " + portEvent);
		}
	}
	
	public boolean isStateActive(String region, String state) {
		return false;
	}
	
	public String[] getRegions() {
		return new String[] {  };
	}
	
	public String[] getStates(String region) {
		switch (region) {
		}
		throw new IllegalArgumentException("Not known region: " + region);
	}
	
	public void schedule(String instance) {
		wrappedComponent.runCycle();
	}
	
	public String[] getVariables() {
		return new String[] {  };
	}
	
	public Object getValue(String variable) {
		switch (variable) {
		}
		throw new IllegalArgumentException("Not known variable: " + variable);
	}
	
	public String[] getComponents() {
		return new String[] { "Test01TS", "system"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "Test01TS":
				if (test01TS == null) {
					test01TS = new ReflectiveTest01TS(wrappedComponent.getTest01TS());
				}
				return test01TS;
			case "system":
				if (system == null) {
					system = new ReflectiveSystem(wrappedComponent.getSystem());
				}
				return system;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
