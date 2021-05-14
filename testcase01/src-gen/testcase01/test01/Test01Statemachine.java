package testcase01.test01;

import testcase01.*; 		
public class Test01Statemachine {
	
	enum Main_region {__Inactive__, StateB, StateA, FinalState1}
	private boolean Command_S2_In;
	private boolean Command_S1_In;
	private Main_region main_region;
	private boolean __assertionFailed;
	private boolean end;
	
	public Test01Statemachine() {
	}
	
	public void reset() {
		this.main_region = Main_region.__Inactive__;
		clearOutEvents();
		clearInEvents();
		this.__assertionFailed = false;
		this.end = false;
		this.main_region = Main_region.__Inactive__;
		this.Command_S2_In = false;
		this.Command_S1_In = false;
		this.main_region = Main_region.StateA;
		if ((this.main_region == Main_region.FinalState1)) {
			this.end = true;
		}
	}
	
	public void setCommand_S2_In(boolean Command_S2_In) {
		this.Command_S2_In = Command_S2_In;
	}
	
	public boolean getCommand_S2_In() {
		return Command_S2_In;
	}
	
	public void setCommand_S1_In(boolean Command_S1_In) {
		this.Command_S1_In = Command_S1_In;
	}
	
	public boolean getCommand_S1_In() {
		return Command_S1_In;
	}
	
	public void setMain_region(Main_region main_region) {
		this.main_region = main_region;
	}
	
	public Main_region getMain_region() {
		return main_region;
	}
	
	public void set__assertionFailed(boolean __assertionFailed) {
		this.__assertionFailed = __assertionFailed;
	}
	
	public boolean get__assertionFailed() {
		return __assertionFailed;
	}
	
	public void setEnd(boolean end) {
		this.end = end;
	}
	
	public boolean getEnd() {
		return end;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((((this.main_region == Main_region.StateB)) && ((((this.Command_S2_In == true)) && (!(this.end)))))))) {
			this.main_region = Main_region.FinalState1;
			this.end = true;
		} else 
		if ((((((this.main_region == Main_region.StateA)) && ((((this.Command_S1_In == true)) && (!(this.end)))))))) {
			this.main_region = Main_region.StateB;
		}
	}
	
	private void clearOutEvents() {
	}
	
	private void clearInEvents() {
		Command_S2_In = false;
		Command_S1_In = false;
	}
	
	@Override
	public String toString() {
		return
			"Command_S2_In = " + Command_S2_In + System.lineSeparator() +
			"Command_S1_In = " + Command_S1_In + System.lineSeparator() +
			"main_region = " + main_region + System.lineSeparator() +
			"__assertionFailed = " + __assertionFailed + System.lineSeparator() +
			"end = " + end
		;
	}
	
}
