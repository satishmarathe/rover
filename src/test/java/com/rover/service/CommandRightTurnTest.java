package com.rover.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.Rover;
import com.rover.factory.CommandFactory;

public class CommandRightTurnTest {
	
	@Test
	@DisplayName("Given a user input should return correct Command.The Command should change orientation of Rover appropriately ")
	public void shouldChangeOrientationAsExpected() {
		Command command = CommandFactory.getCommand('R');
		assertTrue( command instanceof CommandRightTurn);
		
		Rover rover = new Rover(0,0,'E');
		command.setRover(rover);
		command.execute();
		assertTrue('S' == rover.getOrientation());
		command.execute();
		assertTrue('W' == rover.getOrientation());
		command.execute();
		assertTrue('N' == rover.getOrientation());
		command.execute();
		assertTrue('E' == rover.getOrientation());
	}
}
