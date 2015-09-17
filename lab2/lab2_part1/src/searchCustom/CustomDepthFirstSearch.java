package searchCustom;

import java.util.Random;


public class CustomDepthFirstSearch extends CustomGraphSearch{
	public CustomDepthFirstSearch(int maxDepth){
		super(true); // The DFS uses a lifo, therefore insertFront is true
	}
};
