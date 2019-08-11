package com.rover.common;

import java.util.HashMap;
import java.util.Map;

public final class ConstantsIfc {
	static {
		
	}
	
	public static final String ERROR_MSG_EMPTY_FILE = "Plateau cannot be empty";
	public static final String ERROR_MSG_RECTANGULAR_FILE = "Plateau needs to be rectangular";
	public static final String ERROR_MSG_INVALID_CHARS = "Plateau contains invalid content";
	public static final String ERROR_MSG_INVALID_START_POSITION="Plateau cannot have a Rock at starting position of Rover";
	public static final String ERROR_MSG_INVALID_INPUT = "Invalid command valid characters are 'L' , 'R' . 'M'";
	
	public static final String FILE_NAME = "plateau.txt";
	
	public static final String WELCOME_MSG = "Mars Rover v1.0 running,plateau configuration is:";
	public static final String PROMPT_MSG = " Waiting for commands.";
	public static final String PROMPT = ">";
	
	public static final Map<Character,Character> LEFT_TURN = new HashMap<>();
	public static final Map<Character,Character> RIGHT_TURN = new HashMap<>();
	
	static {
		LEFT_TURN.put('N', 'W');
		LEFT_TURN.put('W', 'S');
		LEFT_TURN.put('S', 'E');
		LEFT_TURN.put('E', 'N');
		
		RIGHT_TURN.put('N', 'E');
		RIGHT_TURN.put('E', 'S');
		RIGHT_TURN.put('S', 'W');
		RIGHT_TURN.put('W', 'N');
	}
}
