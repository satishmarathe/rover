package com.rover;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoverSolutionTest {
	@Test
	@DisplayName("Rover should behave correctly based on user instructions")
	public void validateRoverBehaviour() {
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = new ArrayList<>();
		fileRows.add("ooR");
		fileRows.add("oRo");
		/** To get around Scanner waiting for user input **/
		ByteArrayInputStream in = new ByteArrayInputStream("MX".getBytes());
		
		System.setIn(in);		
		
		solution.process(fileRows);
				
		assertTrue(fileRows.get(0).charAt(0) == 'o');
		assertTrue(fileRows.get(0).charAt(1) == 'X');		
	}
}
