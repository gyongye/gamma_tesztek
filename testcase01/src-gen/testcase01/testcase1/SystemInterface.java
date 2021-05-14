package testcase01.testcase1;

import testcase01.*;
import testcase01.interfaces.CommandInterface;

public interface SystemInterface {
	
	CommandInterface.Required getCommand();
	
	void reset();
	
	void runCycle();
	void runFullCycle();
	
}
