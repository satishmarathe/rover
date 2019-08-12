package com.rover.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.factory.CommandFactory;

public class CommandInvalidTest {
	
	@Test
	@DisplayName("Given a user input this Command should correctly increase count of erroneous commands ")
	public void shouldIncreaseErrorCount() {
		Command command = CommandFactory.getCommand('Y');
		assertTrue( command instanceof CommandInvalid);
		
		command.execute();
		assertTrue(command.getErrorCommandCount() == 1);
		
		command.execute();
		assertTrue(command.getErrorCommandCount() == 2);
		
		Command command1 = CommandFactory.getCommand(null);
		assertTrue( command1 instanceof CommandInvalid);
		
		command1.execute();
		assertTrue(command1.getErrorCommandCount() == 3);
	}
}
