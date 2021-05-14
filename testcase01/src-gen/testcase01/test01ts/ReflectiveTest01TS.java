package testcase01.test01ts;

import testcase01.*;

public class ReflectiveTest01TS implements ReflectiveComponentInterface {
	
	private Test01TS wrappedComponent;
	// Wrapped contained components
	
	
	public ReflectiveTest01TS() {
		wrappedComponent = new Test01TS();
	}
	
	public ReflectiveTest01TS(Test01TS wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Test01TS getWrappedComponent() {
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
			default:
				throw new IllegalArgumentException("Not known port-in event combination: " + portEvent);
		}
	}
	
	public boolean isRaisedEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			case "command.S2":
				if (wrappedComponent.getCommand().isRaisedS2()) {
					return true;
				}
				break;
			case "command.S1":
				if (wrappedComponent.getCommand().isRaisedS1()) {
					return true;
				}
				break;
			default:
				throw new IllegalArgumentException("Not known port-out event combination: " + portEvent);
		}
		return false;
	}
	
	public boolean isStateActive(String region, String state) {
		return wrappedComponent.isStateActive(region, state);
	}
	
	public String[] getRegions() {
		return new String[] { "MainRegion" };
	}
	
	public String[] getStates(String region) {
		switch (region) {
			case "MainRegion":
				return new String[] { "_0", "_1", "_2" };
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
		return new String[] { };
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			// If the class name is given, then it will return itself
			case "Test01TS":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
