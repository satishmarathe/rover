package com.rover.service;

public class CommandExit extends Command {

	@Override
	public void execute() {
		System.out.println("Sent " + getCommandCount() + " command(s) / " + getErrorCommandCount() + " failed");
		System.out.println("Mars Rover v1.0 closed.");		
	}
	
	@Override
	public boolean keepRunning() {
		/** we dont want to keep running any longer **/
		return false;
	}

}
