// Lab 1 : Intelligent Agents
// Arnaud Pecoraro

package tddc17;

import aima.core.environment.liuvacuum.*;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.impl.*;

import java.util.Random;

class MyAgentState
{
	public int[][] world = new int[30][30];
	public int initialized = 0;
	final int UNKNOWN 	= 0;
	final int WALL 		= 1;
	final int CLEAR 	= 2;
	final int DIRT		= 3;
	final int HOME		= 4;
	final int ACTION_NONE 			= 0;
	final int ACTION_MOVE_FORWARD 	= 1;
	final int ACTION_TURN_RIGHT 	= 2;
	final int ACTION_TURN_LEFT 		= 3;
	final int ACTION_SUCK	 		= 4;

	public int agent_x_position = 1;
	public int agent_y_position = 1;
	public int agent_last_action = ACTION_NONE;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public int agent_direction = EAST;

	MyAgentState()
	{
		for (int i=0; i < world.length; i++)
			for (int j=0; j < world[i].length ; j++)
				world[i][j] = UNKNOWN;
		world[1][1] = HOME;
		agent_last_action = ACTION_NONE;
	}
	// Based on the last action and the received percept updates the x & y agent position
	public void updatePosition(DynamicPercept p)
	{
		Boolean bump = (Boolean)p.getAttribute("bump");

		if (agent_last_action==ACTION_MOVE_FORWARD && !bump){
			switch (agent_direction) {
			case MyAgentState.NORTH:
				agent_y_position--;
				break;
			case MyAgentState.EAST:
				agent_x_position++;
				break;
			case MyAgentState.SOUTH:
				agent_y_position++;
				break;
			case MyAgentState.WEST:
				agent_x_position--;
				break;
			}
	  }
	}

	public void updateWorld(int x_position, int y_position, int info)
	{
		world[x_position][y_position] = info;
	}

	public void printWorldDebug()
	{
		for (int i=0; i < world.length; i++)
		{
			for (int j=0; j < world[i].length ; j++)
			{
				if (world[j][i]==UNKNOWN)
					System.out.print(" ? ");
				if (world[j][i]==WALL)
					System.out.print(" # ");
				if (world[j][i]==CLEAR)
					System.out.print(" . ");
				if (world[j][i]==DIRT)
					System.out.print(" D ");
				if (world[j][i]==HOME)
					System.out.print(" H ");
			}
			System.out.println("");
		}
	}
}

class MyAgentProgram implements AgentProgram {

	private int initnialRandomActions = 10;
	private Random random_generator = new Random();

	public int iterationCounter = 10;
	public MyAgentState state = new MyAgentState();

	/**

		uMovement : movement counter when turning 180°, please refer to the
		isUTurning : boolean used to test if a u-turn (180) is performed
		turningDirection : 0 ==> right, 1 ==> left, when turning 180°

		isCleaned : to test if the grid is cleaned of its initial dirt
	*/

	public int phase = 1;
	public int uMovement = 0;
	public int turningDirection = 0;

	public Boolean isUTurning = false;
	public Boolean isCleaned = false;

	// moves the Agent to a random start position
	// uses percepts to update the Agent position - only the position, other percepts are ignored
	// returns a random action
	private Action moveToRandomStartPosition(DynamicPercept percept) {
		int action = random_generator.nextInt(6);
		initnialRandomActions--;
		state.updatePosition(percept);
		if(action==0) {
		    state.agent_direction = ((state.agent_direction-1) % 4);
		    if (state.agent_direction<0)
		    	state.agent_direction +=4;
		    state.agent_last_action = state.ACTION_TURN_LEFT;
			return LIUVacuumEnvironment.ACTION_TURN_LEFT;
		} else if (action==1) {
			state.agent_direction = ((state.agent_direction+1) % 4);
		    state.agent_last_action = state.ACTION_TURN_RIGHT;
		    return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
		}
		state.agent_last_action=state.ACTION_MOVE_FORWARD;
		return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
	}

	/**
	 moves the Agent back to the home position
	 use percepts as input to update the Agent position
	 returns an action

	 Given that that the default home position is (1,1), this method initiate
	 a sequence of actions ending up whith the agent on (1,1).
	 At the end, if the world is not clean (refering to the boolean isCleaned) it
	 initiates phase 2 : snaking.
	**/

	private Action moveToHomePosition(DynamicPercept percept) {

		System.out.println("Going home.");

		state.updatePosition(percept);

		while (state.agent_x_position != 1 ){

			while(state.agent_direction != 3){
				state.agent_direction = ((state.agent_direction+1) % 4);
				state.agent_last_action = state.ACTION_TURN_RIGHT;
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			}
			state.agent_last_action = state.ACTION_MOVE_FORWARD;
			return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;

		}

		while (state.agent_y_position != 1 ){

			while(state.agent_direction != 0){
				state.agent_direction = ((state.agent_direction+1) % 4);
				state.agent_last_action = state.ACTION_TURN_RIGHT;
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			}

			state.agent_last_action = state.ACTION_MOVE_FORWARD;
			return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
		}

		System.out.println("Arrived at home.");

		if (isCleaned == true) {
			System.out.println("Finished");
			return NoOpAction.NO_OP;
		}
		else {

			while(state.agent_direction != 1){
				state.agent_direction = ((state.agent_direction+1) % 4);
				state.agent_last_action = state.ACTION_TURN_RIGHT;
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			}
			System.out.println("Phase 2 : Snaking");
			phase ++;
			state.agent_last_action = state.ACTION_SUCK;
			return LIUVacuumEnvironment.ACTION_SUCK;
		}
	}

	// Turn right function
	// Returns an Action
	private Action turnRight(DynamicPercept percept) {
		System.out.println("BUM -> choosing TURN_RIGHT action!");
		state.agent_direction = ((state.agent_direction+1) % 4);
		if (state.agent_direction<0)
			state.agent_direction +=4;

		state.agent_last_action = state.ACTION_TURN_RIGHT;
		return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
	}

	// Turn left function
	// Returns an Action
	private Action turnLeft(DynamicPercept percept){
		System.out.println("BUM -> choosing TURN_LEFT action!");
		state.agent_direction = ((state.agent_direction-1) % 4);
		if (state.agent_direction<0)
			state.agent_direction +=4;

		state.agent_last_action = state.ACTION_TURN_LEFT;
		return LIUVacuumEnvironment.ACTION_TURN_LEFT;
	}

	@Override
	public Action execute(Percept percept) {
		// DO NOT REMOVE this if condition!!!
    	if (initnialRandomActions>0) {
    		return moveToRandomStartPosition((DynamicPercept) percept);
    	} else if (initnialRandomActions==0) {
    		// process percept for the last step of the initial random actions
    		initnialRandomActions--;
    		state.updatePosition((DynamicPercept) percept);
			System.out.println("Processing percepts after the last execution of moveToRandomStartPosition()");
			state.agent_last_action=state.ACTION_SUCK;
	    	return LIUVacuumEnvironment.ACTION_SUCK;
    	}
    	// This example agent program will update the internal agent state while only moving forward.
    	// START HERE - code below should be modified!

			// When the program start (phase1), the vacuum cleaner goes back to home
			// Depending on the number of initial random actions, this choice can be
			// arguable(Please refer to the report for more details)
			while(phase == 1) {
				return moveToHomePosition((DynamicPercept) percept);
			}

    	System.out.println("x=" + state.agent_x_position);
    	System.out.println("y=" + state.agent_y_position);
    	System.out.println("dir=" + state.agent_direction);

	    iterationCounter--;

	    DynamicPercept p = (DynamicPercept) percept;
	    Boolean bump = (Boolean)p.getAttribute("bump");
	    Boolean dirt = (Boolean)p.getAttribute("dirt");
	    Boolean home = (Boolean)p.getAttribute("home");
	    System.out.println("percept: " + p);

	    // State update based on the percept value and the last action
	    state.updatePosition((DynamicPercept)percept);
	    if (bump) {
			switch (state.agent_direction) {
				case MyAgentState.NORTH:
					state.updateWorld(state.agent_x_position,state.agent_y_position-1,state.WALL);
					break;
				case MyAgentState.EAST:
					state.updateWorld(state.agent_x_position+1,state.agent_y_position,state.WALL);
					break;
				case MyAgentState.SOUTH:
					state.updateWorld(state.agent_x_position,state.agent_y_position+1,state.WALL);
					break;
				case MyAgentState.WEST:
					state.updateWorld(state.agent_x_position-1,state.agent_y_position,state.WALL);
					break;
				}
	    }
	    if (dirt)
	    	state.updateWorld(state.agent_x_position,state.agent_y_position,state.DIRT);
	    else
	    	state.updateWorld(state.agent_x_position,state.agent_y_position,state.CLEAR);

	    state.printWorldDebug();

	    // Next action selection based on the percept value

		/*
		 This condition is use to continue an already initiated 180° turning
		 sequence while the Vacuum cleaner is snaking during phase 2.
		 When the sequence is almost over, the boolean isUTurning is set to false,
		 the last turning is performed, and next turn the VC moves forward.
		 */
		if (isUTurning == true) {
			System.out.println("Turning : uMovement " + uMovement);
			if (uMovement == 1) {
				uMovement++;
				state.updateWorld(state.agent_x_position,state.agent_y_position,state.CLEAR);
				state.agent_last_action = state.ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
			else{

				if (bump == true) {
					phase = 1;
					isCleaned = true;
					state.printWorldDebug();
					return turnRight((DynamicPercept)percept);
				}
				else{
					uMovement = 0;
					isUTurning = false;
					if (turningDirection == 0) {
						return turnRight((DynamicPercept) percept);
					}
					else {
						return turnLeft((DynamicPercept) percept);
					}
				}
			}
		}

	    if (dirt) {
	    	System.out.println("DIRT -> choosing SUCK action!");
	    	state.agent_last_action=state.ACTION_SUCK;
	    	return LIUVacuumEnvironment.ACTION_SUCK;
	    }
	    else
	    {
				if (bump && state.agent_direction == 1){
						System.out.println("Start RIGHT TURN 180");
						isUTurning = true;
						uMovement++;
						turningDirection = 0;
						return turnRight((DynamicPercept)percept);
				}
				else if (bump && state.agent_direction == 3){
						System.out.println("Start LEFT TURN 180");
						isUTurning = true;
						uMovement++;
						turningDirection = 1;
						return turnLeft((DynamicPercept)percept);
				}

				state.agent_last_action = state.ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
	    }
	}
}

public class MyVacuumAgent extends AbstractAgent {
    public MyVacuumAgent() {
    	super(new MyAgentProgram());
	}
}
