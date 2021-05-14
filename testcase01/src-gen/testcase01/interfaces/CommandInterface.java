package testcase01.interfaces;

import java.util.List;
import testcase01.*;

public interface CommandInterface {

	interface Provided extends Listener.Required {
		
		boolean isRaisedS2();
		boolean isRaisedS1();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
	interface Provided {
		void raiseS2();
		void raiseS1();
		}
		
	interface Required {
		}
		
	}

}
