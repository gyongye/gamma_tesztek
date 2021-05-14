package testcase01.test01ts;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import testcase01.*;
import testcase01.TimerInterface.*;
import testcase01.interfaces.*;
import testcase01.test01ts.Test01TSStatemachine.*;

public class Test01TS implements Test01TSInterface {
	// Port instances
	private Command command = new Command();
	// Wrapped statemachine
	private Test01TSStatemachine test01TS;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public Test01TS() {
		test01TS = new Test01TSStatemachine();
	}
	
	public void reset() {
		// Clearing the in events
		insertQueue = true;
		processQueue = false;
		eventQueue1.clear();
		eventQueue2.clear();
		//
		test01TS.reset();
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
	
	public class Command implements CommandInterface.Provided {
		private List<CommandInterface.Listener.Provided> listeners = new LinkedList<CommandInterface.Listener.Provided>();
		@Override
		public boolean isRaisedS2() {
			return test01TS.getCommand_S2_Out();
		}
		@Override
		public boolean isRaisedS1() {
			return test01TS.getCommand_S1_Out();
		}
		@Override
		public void registerListener(CommandInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<CommandInterface.Listener.Provided> getRegisteredListeners() {
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
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		test01TS.runCycle();
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
		if (command.isRaisedS2()) {
			for (CommandInterface.Listener.Provided listener : command.getRegisteredListeners()) {
				listener.raiseS2();
			}
		}
		if (command.isRaisedS1()) {
			for (CommandInterface.Listener.Provided listener : command.getRegisteredListeners()) {
				listener.raiseS1();
			}
		}
	}
	
	public void setTimer(TimerInterface timer) {
		this.timer = timer;
	}
	
	public boolean isStateActive(String region, String state) {
		switch (region) {
			case "MainRegion":
				return test01TS.getMainRegion() == MainRegion.valueOf(state);
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return test01TS.toString();
	}
}
