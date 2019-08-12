package com.rover.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.Rover;
import com.rover.factory.CommandFactory;

public class CommandLeftTurnTest {
	
	@Test
	@DisplayName("Given a user input should return correct Command.The Command should change orientation of Rover appropriately ")
	public void shouldChangeOrientationAsExpected() {
		Command command = CommandFactory.getCommand('L');
		assertTrue( command instanceof CommandLeftTurn);
		
		
		Rover rover = new Rover(0,0,'E');
		command.setRover(rover);
		command.execute();
		assertTrue('N' == rover.getOrientation());
		command.execute();
		assertTrue('W' == rover.getOrientation());
		command.execute();
		assertTrue('S' == rover.getOrientation());
		command.execute();
		assertTrue('E' == rover.getOrientation());
	}
}
