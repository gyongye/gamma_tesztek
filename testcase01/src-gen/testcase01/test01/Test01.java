package testcase01.test01;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import testcase01.*;
import testcase01.TimerInterface.*;
import testcase01.interfaces.*;
import testcase01.test01.Test01Statemachine.*;

public class Test01 implements Test01Interface {
	// Port instances
	private Command command = new Command();
	// Wrapped statemachine
	private Test01Statemachine test01;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public Test01() {
		test01 = new Test01Statemachine();
	}
	
	public void reset() {
		// Clearing the in events
		insertQueue = true;
		processQueue = false;
		eventQueue1.clear();
		eventQueue2.clear();
		//
		test01.reset();
		timer.saveTime(this);
		notifyListeners();
	}

	/** Changes the event queues of the component instance. Should be used only be the container (composite system) class. */
	public void changeEventQueues() {
		insertQueue = !insertQueue;
		processQueue = !processQueue;
	}
	
	/** Changes the event queues to which the events are put. Should be used only be a cascade container (composite system) class. */
	public void changeInsertQueue() {
		insertQueue = !insertQueue;
	}
	
	/** Returns whether the eventQueue containing incoming messages is empty. Should be used only be the container (composite system) class. */
	public boolean isEventQueueEmpty() {
		return getInsertQueue().isEmpty();
	}
	
	/** Returns the event queue into which events should be put in the particular cycle. */
	private Queue<Event> getInsertQueue() {
		if (insertQueue) {
			return eventQueue1;
		}
		return eventQueue2;
	}
	
	/** Returns the event queue from which events should be inspected in the particular cycle. */
	private Queue<Event> getProcessQueue() {
		if (processQueue) {
			return eventQueue1;
		}
		return eventQueue2;
	}
	
	public class Command implements CommandInterface.Required {
		private List<CommandInterface.Listener.Required> listeners = new LinkedList<CommandInterface.Listener.Required>();
		@Override
		public void raiseS1() {
			getInsertQueue().add(new Event("Command.S1"));
		}
		@Override
		public void raiseS2() {
			getInsertQueue().add(new Event("Command.S2"));
		}
		@Override
		public void registerListener(CommandInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<CommandInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Command getCommand() {
		return command;
	}
	
	public void runCycle() {
		changeEventQueues();
		runComponent();
	}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "Command.S1": 
					test01.setCommand_S1_In(true);
				break;
				case "Command.S2": 
					test01.setCommand_S2_In(true);
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		test01.runCycle();
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
	}
	
	public void setTimer(TimerInterface timer) {
		this.timer = timer;
	}
	
	public boolean isStateActive(String region, String state) {
		switch (region) {
			case "main_region":
				return test01.getMain_region() == Main_region.valueOf(state);
		}
		return false;
	}
	
	public boolean getEnd() {
		return test01.getEnd();
	}
	
	@Override
	public String toString() {
		return test01.toString();
	}
}
