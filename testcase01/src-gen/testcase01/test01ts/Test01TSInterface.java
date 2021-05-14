package testcase01.test01ts;

import testcase01.interfaces.*;

public interface Test01TSInterface {

	public CommandInterface.Provided getCommand();
	
	void runCycle();
	void reset();

}
