package testcase01.testcase1;

import java.util.List;
import java.util.LinkedList;

import testcase01.*;
import testcase01.interfaces.*;
import testcase01.test01ts.*;
import testcase01.testcase1.*;

public class Test01System implements Test01SystemInterface {
	// Component instances
	private Test01TS Test01TS;
	private System system;
	// Port instances
	
	
	public Test01System() {
		Test01TS = new Test01TS();
		system = new System();
		init();
	}
	
	/** Resets the contained statemachines recursively. Must be called to initialize the component. */
	@Override
	public void reset() {
		Test01TS.reset();
		system.reset();
		// Setting only a single queue for cascade statecharts
		Test01TS.changeInsertQueue();
		clearPorts();
		// Initializing chain of listeners and events 
		notifyAllListeners();
	}
	
	/** Creates the channel mappings and enters the wrapped statemachines. */
	private void init() {
		// Registration of simple channels
		Test01TS.getCommand().registerListener(system.getCommand());
		system.getCommand().registerListener(Test01TS.getCommand());
		// Registration of broadcast channels
	}
	
	// Inner classes representing Ports
	
	/** Clears the the boolean flags of all out-events in each contained port. */
	private void clearPorts() {
	}
	
	/** Notifies all registered listeners in each contained port. */
	public void notifyAllListeners() {
		Test01TS.notifyAllListeners();
		system.notifyAllListeners();
		notifyListeners();
	}
	
	public void notifyListeners() {
	}
	
	
	/** Returns whether all event queues of the contained component instances are empty. 
	Should be used only be the container (composite system) class. */
	public boolean isEventQueueEmpty() {
		return Test01TS.isEventQueueEmpty() && system.isEventQueueEmpty();
	}
	
	/** Initiates cycle runs until all event queues of component instances are empty. */
	@Override
	public void runFullCycle() {
		do {
			runCycle();
		}
		while (!isEventQueueEmpty());
	}
	
	/** Changes event queues and initiates a cycle run.
	 * This should be the execution point from an asynchronous component. */
	@Override
	public void runCycle() {
		// Composite type-dependent behavior
		runComponent();
	}
	
	/** Initiates a cycle run without changing the event queues.
	 * Should be used only be the container (composite system) class. */
	public void runComponent() {
		// Starts with the clearing of the previous out-event flags
		clearPorts();
		// Running contained components
		Test01TS.runComponent();
		system.runCycle();
		// Notifying registered listeners
		notifyListeners();
	}

	
	/**  Getter for component instances, e.g., enabling to check their states. */
	public Test01TS getTest01TS() {
		return Test01TS;
	}
	
	public System getSystem() {
		return system;
	}
	
}
