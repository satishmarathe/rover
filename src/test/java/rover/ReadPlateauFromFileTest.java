package rover;

import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rover.RoverSolution;
import com.rover.common.ConstantsIfc;
import com.rover.notify.Notification;

/**
 * In order for Rover to navigate
 * As NASA 
 * I want to read the plateau configuration from a file
 *
 */
public class ReadPlateauFromFileTest {
	@Test
	@DisplayName("Should throw NoSuchFileException")
	public void shouldThrowCorrectException() {
		/**
		 * Given we do not have the plateau configuration file
		 * When we try to read a file 
		 * Then we should report the exception 
		 */
		String invalidFileName = "doesnotexist.txt";
		RoverSolution solution = new RoverSolution();
		assertThrows(NoSuchFileException.class,() -> {
			solution.readFile(invalidFileName);
		});
	}
	
	@Test
	@DisplayName("Should Not throw Any Exception")
	public void shouldNotThrowException() {
		/**
		 * Given we  have the plateau configuration file
		 * When we try to read a file 
		 * Then we should successfully load the file into a List and not report any exceptions 
		 */
		String validFileName = "plateau.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(validFileName);
		});
		
		assertNotNull(fileRows);
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object which should not be null")
	public void shouldValidateAndReturnNotification() {
		/**
		 * Given we  have EMPTY plateau configuration file
		 * After we read a file 
		 * Then we report the exception and exit the program 
		 */
		String validFileName = "empty.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(validFileName);
		});
		
		Notification notification =  solution.validate(fileRows);
		
		assertNotNull(notification);
	}
	
	@Test
	@DisplayName("Should Perform Validation and return a Notification object populated with correct error message when file is empty")
	public void shouldReturnPopulatedNotificationForEmptyFile() {
		String fileName = "empty.txt";
		RoverSolution solution = new RoverSolution();
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
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
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
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
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
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
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
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
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
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
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
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
		List <String> fileRows = assertDoesNotThrow(() -> {
			return solution.readFile(fileName);
		});
		
		Notification notification =  solution.validate(fileRows);
		/** this should not be empty **/
		assertFalse(notification.getErrors().isEmpty());
		assertEquals(ConstantsIfc.ERROR_MSG_INVALID_START_POSITION,notification.getErrors().get(0));
	}
	
	
}
