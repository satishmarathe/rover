package com.rover.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.factory.CommandFactory;

public class CommandExitTest {
	
	@Test
	@DisplayName("Given a user input the returned Command should be of correct type and the Command should return appropriate indicator to Exit ")
	public void shouldReturnWithFlagToExit() {
		Command command = CommandFactory.getCommand('X');
		assertTrue( command instanceof CommandExit);		
		assertFalse(command.keepRunning());
		
		Command command1 = CommandFactory.getCommand('x');
		assertTrue( command1 instanceof CommandExit);		
		assertFalse(command1.keepRunning());
	}
}
