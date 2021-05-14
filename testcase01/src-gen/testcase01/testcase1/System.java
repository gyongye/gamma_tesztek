package testcase01.testcase1;

import java.util.List;
import java.util.LinkedList;

import testcase01.*;
import testcase01.interfaces.*;
import testcase01.test01.*;

public class System implements SystemInterface {
	// Component instances
	private Test01 test;
	// Port instances
	private Command command;
	
	
	public System() {
		test = new Test01();
		command = new Command();
		init();
	}
	
	/** Resets the contained statemachines recursively. Must be called to initialize the component. */
	@Override
	public void reset() {
		test.reset();
		clearPorts();
		// Initializing chain of listeners and events 
		notifyAllListeners();
	}
	
	/** Creates the channel mappings and enters the wrapped statemachines. */
	private void init() {
		// Registration of simple channels
		// Registration of broadcast channels
	}
	
	// Inner classes representing Ports
	public class Command implements CommandInterface.Required {
		private List<CommandInterface.Listener.Required> listeners = new LinkedList<CommandInterface.Listener.Required>();
		
		public Command() {
			// Registering the listener to the contained component
			test.getCommand().registerListener(new CommandUtil());
		}
		
		@Override
		public void raiseS2() {
			test.getCommand().raiseS2();
		}
		
		@Override
		public void raiseS1() {
			test.getCommand().raiseS1();
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class CommandUtil implements CommandInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(CommandInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<CommandInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public Command getCommand() {
		return command;
	}
	
	/** Clears the the boolean flags of all out-events in each contained port. */
	private void clearPorts() {
		getCommand().clear();
	}
	
	/** Notifies all registered listeners in each contained port. */
	public void notifyAllListeners() {
		test.notifyAllListeners();
		notifyListeners();
	}
	
	public void notifyListeners() {
		getCommand().notifyListeners();
	}
	
	/** Changes the event and process queues of all component instances. Should be used only be the container (composite system) class. */
	public void changeEventQueues() {
		test.changeEventQueues();
	}
	
	/** Returns whether all event queues of the contained component instances are empty. 
	Should be used only be the container (composite system) class. */
	public boolean isEventQueueEmpty() {
		return test.isEventQueueEmpty();
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
		// Changing the insert and process queues for all synchronous subcomponents
		changeEventQueues();
		// Composite type-dependent behavior
		runComponent();
	}
	
	/** Initiates a cycle run without changing the event queues.
	 * Should be used only be the container (composite system) class. */
	public void runComponent() {
		// Starts with the clearing of the previous out-event flags
		clearPorts();
		// Running contained components
		test.runComponent();
		// Notifying registered listeners
		notifyListeners();
	}

	
	/**  Getter for component instances, e.g., enabling to check their states. */
	public Test01 getTest() {
		return test;
	}
	
}
