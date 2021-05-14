package testcase01.testcase1;

import testcase01.*;
import testcase01.test01.*;

public class ReflectiveSystem implements ReflectiveComponentInterface {
	
	private System wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface test = null;
	
	
	public ReflectiveSystem() {
		wrappedComponent = new System();
	}
	
	public ReflectiveSystem(System wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public System getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "command" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "command":
				return new String[] { "S2", "S1" };
			default:
				throw new IllegalArgumentException("Not known port: " + port);
		}
	}
	
	public void raiseEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			case "command.S2":
				wrappedComponent.getCommand().raiseS2();
				break;
			case "command.S1":
				wrappedComponent.getCommand().raiseS1();
				break;
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
		return new String[] { "test"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "test":
				if (test == null) {
					test = new ReflectiveTest01(wrappedComponent.getTest());
				}
				return test;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
