	package searchCustom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import searchShared.NodeQueue;
import searchShared.Problem;
import searchShared.SearchObject;
import searchShared.SearchNode;

import world.GridPos;

public class CustomGraphSearch implements SearchObject {

	private HashSet<SearchNode> explored;
	private NodeQueue frontier;
	protected ArrayList<SearchNode> path;
	private boolean insertFront;

    public CustomGraphSearch(boolean bInsertFront) {
			insertFront = bInsertFront;
    }


	public ArrayList<SearchNode> search(Problem p) {

		frontier = new NodeQueue();
		explored = new HashSet<SearchNode>();
		GridPos startState = (GridPos) p.getInitialState();
		frontier.addNodeToFront(new SearchNode(startState));

		path = new ArrayList<SearchNode>();

		while(true){

			if (frontier.isEmpty()){
				System.out.println("Frontier is empty");
				return path;
				}

			SearchNode currentNode = frontier.removeFirst();
			System.out.println("currentNode is " +currentNode.getState().getX()+" " + currentNode.getState().getY()  );
			if (p.isGoalState(currentNode.getState())) {
				System.out.println("Goal founded");
				path = currentNode.getPathFromRoot();
				return path;
			}
			else {
				explored.add(currentNode);
			}

			ArrayList<GridPos> childStates = p.getReachableStatesFrom(currentNode.getState());

			Iterator iter = childStates.iterator();
			while(iter.hasNext()) {
				SearchNode childNode = new SearchNode((GridPos)iter.next(), currentNode);
			//	System.out.println(childNode.getState().getX() + " " + childNode.getState().getY());

				if ((!explored.contains(childNode)) && (!frontier.contains(childNode))) {
					if (insertFront) {
						frontier.addNodeToFront(childNode);
					}
					else	{
						frontier.addNodeToBack(childNode);
					}
				}
			}


		}

		/*
		loop do
			if the frontier is empty then return failure

			choose a leaf node and remove it from the frontier

			if the node contains a goal state then return the corresponding solution
			add the node to the explored set

			expand the chosen node, adding the resulting nodes to the frontier
			only if not in the frontier or explored set

		*/

		//path = node.getPathFromRoot();

		/* Some hints:
		 * -SearchNodes are the nodes of the search tree and contains the relevant problem state, in this case x,y position (GridPos) of the agent
		 * --You can create a new search node from a state by: SearchNode childNode = new SearchNode(childState, currentNode);
		 * --You can also extract the state by .getState() method
		 * --All search structures use search nodes, but the problem object only speaks in state, so you may need to convert between them
		 *
		 * -The frontier is a queue of search nodes, open this class to find out what you can do with it!
		 *
		 * -If you are unfamiliar with Java, the "HashSet<SearchNode>" used for the explored set means a set of SearchNode objects.
		 * --You can add nodes to the explored set, or check if it contains a node!
		 *
		 * -To get the child states (adjacent grid positions that are not walls) of a particular search node, do: ArrayList<GridPos> childStates = p.getReachableStatesFrom(currentState);
		 *
		 * -Depending on the addNodesToFront boolean variable, you may need to do something with the frontier... (see book)
		 *
		 * -You can check if you have reached the goal with p.isGoalState(NodeState)
		 *
		 *  When the goal is found, the path to be returned can be found by: path = node.getPathFromRoot();
		 */
		/* Note: Returning an empty path signals that no path exists */
	//	return path;
	}

	public ArrayList<SearchNode> getPath() {
		return path;
	}

	public ArrayList<SearchNode> getFrontierNodes() {
		return new ArrayList<SearchNode>(frontier.toList());
	}
	public ArrayList<SearchNode> getExploredNodes() {
		return new ArrayList<SearchNode>(explored);
	}
	public ArrayList<SearchNode> getAllExpandedNodes() {
		ArrayList<SearchNode> allNodes = new ArrayList<SearchNode>();
		allNodes.addAll(getFrontierNodes());
		allNodes.addAll(getExploredNodes());
		return allNodes;
	}

}
