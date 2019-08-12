package com.rover.factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.service.CommandExit;
import com.rover.service.CommandInvalid;
import com.rover.service.CommandLeftTurn;
import com.rover.service.CommandMove;
import com.rover.service.CommandRightTurn;

public class CommandFactoryTest {
	
	@Test
	@DisplayName("Given a user input CommandFactory should return correct Command object ")
	public void shouldReturnCorrectCommandTypeTest() {
		Object objL = CommandFactory.getCommand('L');
		assertTrue( objL instanceof CommandLeftTurn);
		
		Object objR = CommandFactory.getCommand('R');
		assertTrue( objR instanceof CommandRightTurn);
		
		Object objM = CommandFactory.getCommand('M');
		assertTrue( objM instanceof CommandMove);
		
		Object objX = CommandFactory.getCommand('X');
		assertTrue( objX instanceof CommandExit);
		
		Object objx = CommandFactory.getCommand('x');
		assertTrue( objx instanceof CommandExit);
		
		Object objInvalid = CommandFactory.getCommand(null);
		assertTrue( objInvalid instanceof CommandInvalid);
		
		
	}
}
