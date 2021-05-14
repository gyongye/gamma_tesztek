package testcase01.test01;

import testcase01.*;

public class ReflectiveTest01 implements ReflectiveComponentInterface {
	
	private Test01 wrappedComponent;
	// Wrapped contained components
	
	
	public ReflectiveTest01() {
		wrappedComponent = new Test01();
	}
	
	public ReflectiveTest01(Test01 wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Test01 getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "Command" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "Command":
				return new String[] { "S2", "S1" };
			default:
				throw new IllegalArgumentException("Not known port: " + port);
		}
	}
	
	public void raiseEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			case "Command.S2":
				wrappedComponent.getCommand().raiseS2();
				break;
			case "Command.S1":
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
		return wrappedComponent.isStateActive(region, state);
	}
	
	public String[] getRegions() {
		return new String[] { "main_region" };
	}
	
	public String[] getStates(String region) {
		switch (region) {
			case "main_region":
				return new String[] { "StateB", "StateA", "FinalState1" };
		}
		throw new IllegalArgumentException("Not known region: " + region);
	}
	
	public void schedule(String instance) {
		wrappedComponent.runCycle();
	}
	
	public String[] getVariables() {
		return new String[] { "end" };
	}
	
	public Object getValue(String variable) {
		switch (variable) {
			case "end":
				return wrappedComponent.getEnd();
		}
		throw new IllegalArgumentException("Not known variable: " + variable);
	}
	
	public String[] getComponents() {
		return new String[] { };
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			// If the class name is given, then it will return itself
			case "Test01":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
