package testcase01.test01;

import testcase01.interfaces.*;

public interface Test01Interface {

	public CommandInterface.Required getCommand();
	
	void runCycle();
	void reset();

}
