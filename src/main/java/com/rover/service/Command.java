package com.rover.service;

import java.util.List;
import java.util.Map;

import com.rover.Rover;

public abstract class Command {
	
	private int commandCount;
	private static int errorCommandCount;
	
	private Map<String,Character> plateauConfigMap ;
	private List<String> plateauRows;
	
	private Rover rover;
	
	public abstract void execute();
	
	public boolean keepRunning() {
		return true;
	}

	public int getCommandCount() {
		return commandCount;
	}

	public void setCommandCount(int commandCount) {
		this.commandCount = commandCount;
	}

	public static int getErrorCommandCount() {
		return errorCommandCount;
	}

	public static void setErrorCommandCount(int errorCommandCount) {
		Command.errorCommandCount = errorCommandCount;
	}

	public Map<String, Character> getPlateauConfigMap() {
		return plateauConfigMap;
	}

	public void setPlateauConfigMap(Map<String, Character> plateauConfigMap) {
		this.plateauConfigMap = plateauConfigMap;
	}

	public List<String> getPlateauRows() {
		return plateauRows;
	}

	public void setPlateauRows(List<String> plateauRows) {
		this.plateauRows = plateauRows;
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}
}
