package searchCustom;

import java.util.Random;

public class CustomBreadthFirstSearch  extends CustomGraphSearch{

	public   CustomBreadthFirstSearch(int maxDepth){
		super(false); // The BFS uses a fifo queue, therefore insertFront is false
	}
};
