package com.rover.factory;

import java.util.HashMap;
import java.util.Map;

import com.rover.service.Command;
import com.rover.service.CommandExit;
import com.rover.service.CommandInvalid;
import com.rover.service.CommandLeftTurn;
import com.rover.service.CommandMove;
import com.rover.service.CommandRightTurn;

public final class CommandFactory {
	private CommandFactory() {		
	}
	
	public static final Map<Character,Command> commandMap = new HashMap<>();
	
	static {
		commandMap.put('L',new CommandLeftTurn());
		commandMap.put('R',new CommandRightTurn());
		commandMap.put('M',new CommandMove());
		commandMap.put('X',new CommandExit());
		commandMap.put('x',new CommandExit());
		commandMap.put(null,new CommandInvalid());
	}
	
	public static Command getCommand(Character c) {
		/** required to handle invalid characters apart from null **/
		return null == commandMap.get(c) ? commandMap.get(null) : commandMap.get(c);
	}
}
