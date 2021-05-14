package testcase01.test01ts;

import testcase01.*; 		
public class Test01TSStatemachine {
	
	enum MainRegion {__Inactive__, _0, _1, _2}
	private boolean command_S2_Out;
	private boolean command_S1_Out;
	private MainRegion mainRegion;
	private boolean __assertionFailed;
	
	public Test01TSStatemachine() {
	}
	
	public void reset() {
		this.mainRegion = MainRegion.__Inactive__;
		clearOutEvents();
		clearInEvents();
		this.__assertionFailed = false;
		this.mainRegion = MainRegion.__Inactive__;
		this.command_S2_Out = false;
		this.command_S1_Out = false;
		this.mainRegion = MainRegion._0;
	}
	
	public void setCommand_S2_Out(boolean command_S2_Out) {
		this.command_S2_Out = command_S2_Out;
	}
	
	public boolean getCommand_S2_Out() {
		return command_S2_Out;
	}
	
	public void setCommand_S1_Out(boolean command_S1_Out) {
		this.command_S1_Out = command_S1_Out;
	}
	
	public boolean getCommand_S1_Out() {
		return command_S1_Out;
	}
	
	public void setMainRegion(MainRegion mainRegion) {
		this.mainRegion = mainRegion;
	}
	
	public MainRegion getMainRegion() {
		return mainRegion;
	}
	
	public void set__assertionFailed(boolean __assertionFailed) {
		this.__assertionFailed = __assertionFailed;
	}
	
	public boolean get__assertionFailed() {
		return __assertionFailed;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((((this.mainRegion == MainRegion._1)) && (true))))) {
			this.command_S2_Out = true;
			this.mainRegion = MainRegion._2;
		} else 
		if ((((((this.mainRegion == MainRegion._0)) && (true))))) {
			this.command_S1_Out = true;
			this.mainRegion = MainRegion._1;
		}
	}
	
	private void clearOutEvents() {
		command_S2_Out = false;
		command_S1_Out = false;
	}
	
	private void clearInEvents() {
	}
	
	@Override
	public String toString() {
		return
			"command_S2_Out = " + command_S2_Out + System.lineSeparator() +
			"command_S1_Out = " + command_S1_Out + System.lineSeparator() +
			"mainRegion = " + mainRegion + System.lineSeparator() +
			"__assertionFailed = " + __assertionFailed
		;
	}
	
}
