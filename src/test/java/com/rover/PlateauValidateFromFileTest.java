package com.rover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rover.common.ConstantsIfc;
import com.rover.notify.Notification;

/**
 * In order for Rover to navigate
 * As NASA 
 * I want to ensure the plateau configuration is valid
 *
 */
public class PlateauValidateFromFileTest {

	@Test
	@DisplayName("Should Perform Validation and return a Notification object which should not be null")
	public void shouldValidateAndReturnNotification() {
		String validFileName = "empty.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(validFileName);

		Notification notification =  solution.validate(fileRows);

		assertNotNull(notification);
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file is empty")
	public void shouldReturnPopulatedNotificationForEmptyFile() {
		String emptyfileName = "empty.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(emptyfileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_EMPTY_FILE,notification.getErrors().get(0));
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file contains only a single row")
	public void shouldReturnNotificationWhenFileSingleRow() {
		String fileName = "singlerow.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(fileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_RECTANGULAR_FILE,notification.getErrors().get(0));
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file contains single column rows")
	public void shouldReturnNotificationWhenFileSingleColRows() {
		String fileName = "singlecoltworows.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(fileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_RECTANGULAR_FILE,notification.getErrors().get(0));
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file is a square and not a rectangle")
	public void shouldReturnNotificationWhenFileIsSquare() {
		String fileName = "square.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(fileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_RECTANGULAR_FILE,notification.getErrors().get(0));
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file has rows of different width ")
	public void shouldReturnNotificationWhenFileHasUnequalRows() {
		String fileName = "irregular_rows.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(fileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_RECTANGULAR_FILE,notification.getErrors().get(0));
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file has invalid chars ")
	public void shouldReturnNotificationWhenFileHasInvalidChars() {
		String fileName = "invalid_content.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(fileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_INVALID_CHARS,notification.getErrors().get(0));
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file has Rock at 00 coordinates ")
	public void shouldReturnNotificationWhenFileHasRockAt00() {
		String fileName = "invalid_starting_position.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = readFile(fileName);

		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertTrue(notification.hasErrors());
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_INVALID_START_POSITION,notification.getErrors().get(0));
	}
	
	
	private List<String> readFile(String fileName) {
		List<String> testList = new ArrayList<>();

		InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			if (in != null) {                            
				String str;
				while ((str = reader.readLine()) != null) {    
					testList.add(str);
				}                
			}
		}catch(Exception e) {
			
		}finally {
			try { in.close(); } catch (Throwable ignore) {}
		}
		return testList;
	}
	
	

}
