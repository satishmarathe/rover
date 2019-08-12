package com.rover;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlateauTest {
	@Test
	@DisplayName("Should be able to handle a empty List with no exceptions")
	public void shouldNotThrowException() {
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = new ArrayList<>();

		Map<String,Character> map =  solution.initialisePlateau(fileRows);

		assertTrue(map.isEmpty());
		
	}
	
	@Test
	@DisplayName("Should populate Plateau with correct data")
	public void shouldPopulatePlateau() {
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = new ArrayList<>();
		fileRows.add("ooR");
		fileRows.add("oXo");

		Map<String,Character> map =  solution.initialisePlateau(fileRows);

		assertTrue(!map.isEmpty());
		assertTrue(map.size() == 6);
		assertTrue(map.get("0,0") == 'o');
		assertTrue(map.get("1,0") == 'o');
		assertTrue(map.get("2,0") == 'R');
		
		assertTrue(map.get("0,1") == 'o');
		assertTrue(map.get("1,1") == 'X');
		assertTrue(map.get("2,1") == 'o');
		
	}
	
	@Test
	@DisplayName("Should correctly place Rover at starting position which is 0,0")
	public void shouldPlaceRoverCorrectly() {
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = new ArrayList<>();
		fileRows.add("ooR");
		fileRows.add("oRo");

		Map<String,Character> map =  solution.initialisePlateau(fileRows);
		solution.placeRoverAtStartingPosition(fileRows);
		
		assertTrue(fileRows.get(0).charAt(0) == 'X');
		
	}
	
		

}
