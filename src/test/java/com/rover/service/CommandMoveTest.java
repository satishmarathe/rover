package com.rover.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.Rover;
import com.rover.factory.CommandFactory;

public class CommandMoveTest {
	
	@Test
	@DisplayName("Given a user input Command the Command should move the rover appropriately ")
	public void shouldMoveRover() {
		Command command = CommandFactory.getCommand('M');
		assertTrue( command instanceof CommandMove);
		
		/** input file **/
		List<String> plateau = new ArrayList<>();
		plateau.add("ooooooRRRR");
		plateau.add("ooRooooooo");
		plateau.add("ooooooRRoo");
		
		command.setPlateauRows(plateau);
		command.setRover(new Rover(0,0,'E'));
		command.setPlateauConfigMap(getMap(plateau));
		/** explicitly setting to 0 to validate error count behaviour **/
		command.setErrorCommandCount(0);
		
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		/** should successfully move Rover to the Right by 1 position to new coordinates x = 1 , y = 0 **/
		command.execute();
		assertTrue(command.getPlateauRows().get(0).charAt(0) == 'o');
		assertTrue(command.getPlateauRows().get(0).charAt(1) == 'X');
		assertTrue(command.getRover().getxCordinate() == 1);
		assertTrue(command.getRover().getyCordinate() == 0);
		assertTrue(command.getRover().getOrientation() == 'E');
		assertTrue(command.getErrorCommandCount() == 0);
		
		System.out.println("----- after instruction to move  -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		System.out.println("----- successfully moved rover -------");
		
		/** should not move Rover since this will take rover outside of boundary **/
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		
		
		command.getRover().setOrientation('N');
		
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		
		command.execute();
		assertTrue(command.getRover().getOrientation() == 'N');
		assertTrue(command.getRover().getxCordinate() == 1);
		assertTrue(command.getRover().getyCordinate() == 0);
		assertTrue(command.getPlateauRows().get(0).charAt(0) == 'o');
		assertTrue(command.getPlateauRows().get(0).charAt(1) == 'X');
		assertTrue(command.getErrorCommandCount() == 1);
		
		System.out.println("----- after instruction to move  -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		System.out.println("----- Cannot move rover outside perimeter -------");
		
		/** should not move Rover since this will take rover outside of boundary **/
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		
		command.execute();
		assertTrue(command.getRover().getOrientation() == 'N');
		assertTrue(command.getRover().getxCordinate() == 1);
		assertTrue(command.getRover().getyCordinate() == 0);
		assertTrue(command.getPlateauRows().get(0).charAt(0) == 'o');
		assertTrue(command.getPlateauRows().get(0).charAt(1) == 'X');
		assertTrue(command.getErrorCommandCount() == 2);
		
		System.out.println("----- after instruction to move  -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		System.out.println("----- Cannot move rover outside perimeter -------");
		
		command.getRover().setOrientation('S');
		
		/** should  move Rover successfully **/
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		
		command.execute();
		assertTrue(command.getRover().getOrientation() == 'S');
		assertTrue(command.getRover().getxCordinate() == 1);
		assertTrue(command.getRover().getyCordinate() == 1);
		assertTrue(command.getPlateauRows().get(0).charAt(0) == 'o');
		assertTrue(command.getPlateauRows().get(1).charAt(1) == 'X');
		System.out.println("----- after instruction to move  -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		System.out.println("----- successfully moved rover -------");
		
		/** should  hit rock and not move Rover  **/
		command.getRover().setOrientation('E');
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		
		command.execute();
		assertTrue(command.getRover().getOrientation() == 'E');
		assertTrue(command.getRover().getxCordinate() == 1);
		assertTrue(command.getRover().getyCordinate() == 1);
		assertTrue(command.getPlateauRows().get(0).charAt(0) == 'o');
		assertTrue(command.getPlateauRows().get(1).charAt(1) == 'X');
		assertTrue(command.getErrorCommandCount() == 3);
		
		System.out.println("----- after instruction to move  -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		System.out.println("----- Unsuccessful move rover hit a rock -------");
		
		/** should move successfully **/
		command.getRover().setOrientation('W');
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		
		command.execute();
		assertTrue(command.getRover().getOrientation() == 'W');
		assertTrue(command.getRover().getxCordinate() == 0);
		assertTrue(command.getRover().getyCordinate() == 1);
		assertTrue(command.getPlateauRows().get(0).charAt(0) == 'o');
		assertTrue(command.getPlateauRows().get(1).charAt(0) == 'X');
		
		System.out.println("----- after instruction to move  -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		System.out.println("----- successfully moved rover -------");
	}
	
	@Test
	@DisplayName("Rover error count of commands should be calculated correctly ")
	public void shouldCalculateErrorCount() {
		Command command = CommandFactory.getCommand('M');
		assertTrue( command instanceof CommandMove);
		
		/** input file **/
		List<String> plateau = new ArrayList<>();
		plateau.add("oRooooRRRR");
		plateau.add("ooRooooooo");
		plateau.add("ooooooRRoo");
		
		command.setPlateauRows(plateau);
		command.setRover(new Rover(0,0,'E'));
		command.setPlateauConfigMap(getMap(plateau));
		
		/** setting a number to which  further increment should occur **/ 
		command.setErrorCommandCount(6);
		
		System.out.println("----- before move instruction -------");
		printPlateau(plateau);
		System.out.println("---- Rover orientation = " + command.getRover().getOrientation());
		/** should not move Rover **/
		command.execute();
		
		assertTrue(command.getErrorCommandCount() == 7);
	}
	
	private Map<String,Character> getMap(List<String> rows) {
		Map<String,Character> plateauMap = new LinkedHashMap<>();
		int yAxisCounter = 0;
		/** for each row **/
		for(String row : rows) {
			/** for each character in row **/
			for(int x = 0;x<row.length();x++) {
				plateauMap.put(String.valueOf(x)+","+ String.valueOf(yAxisCounter), row.charAt(x));
			}
			yAxisCounter++;
		}
		return plateauMap;
	}
	
	private void printPlateau(List<String> fileRows) {
		System.out.println("''''''''''");
		fileRows.forEach(System.out::println);
		System.out.println("''''''''''");
	}
}
