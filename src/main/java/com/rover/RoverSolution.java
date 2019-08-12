package com.rover;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.rover.common.ConstantsIfc;
import com.rover.factory.CommandFactory;
import com.rover.notify.Notification;
import com.rover.service.Command;
import com.rover.utilities.FileUtility;

public class RoverSolution {
	//private static final LOGGER = Logger.getLogger(RoverSolution.class.getName());
	public static void main(String[] args) {
		RoverSolution roverSolution = new RoverSolution();
		/** read input file **/
		List<String> fileRows ;
		
		try{
			fileRows = FileUtility.readFile(ConstantsIfc.FILE_NAME);
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		roverSolution.process(fileRows);		
	}
	
	public void process(List<String> fileRows) {
		try {
			
			/** read input file **/
			//List<String> fileRows = readFile(ConstantsIfc.FILE_NAME);
			
			/** validate to ensure file is good **/
			Notification notification = validate(fileRows);
			
			/** check errors in file and if any then halt at this point **/
			if(notification.hasErrors()) {
				/** log each of the errors **/
				notification.getErrors().forEach(System.out::println);
				return;
			}
			
			/** all validations done Plateau is good - we can get started **/
			
			/** get a mapping representation of the Plateau in terms of a Java Map **/
			Map<String,Character> plateauConfigMap = initialisePlateau(fileRows);
			
			/** initialise Rover at 0,0 and orientation as East**/
			/** TODO create singleton **/
			Rover rover = new Rover(0,0,'E');
			
			/** place rover start position **/
			placeRoverAtStartingPosition(fileRows);
			
			System.out.println(ConstantsIfc.WELCOME_MSG);
			printPlateau(fileRows);
			
			Scanner userInputScan = new Scanner(System.in).useDelimiter("\\s");
			System.out.println(ConstantsIfc.PROMPT_MSG);
			System.out.println(ConstantsIfc.PROMPT);
			/** wait for user input **/
			String instruction = userInputScan.nextLine();
			boolean keepRunning = true;
			Command commandToEx = null;
			int instructionCount = 0;
			while(keepRunning) {
				if(null == instruction || instruction.trim().length() <= 0){
					instructionCount++;
					/** handle this separately **/
					commandToEx = CommandFactory.getCommand(null);
					populateCommand(commandToEx,instructionCount,fileRows,plateauConfigMap,rover);
					commandToEx.execute();					
				}else {
					/** multiple instructions can come in one go **/
					char[] charArr = instruction.toCharArray();
					for(char c:charArr) {
						instructionCount++;
						commandToEx = CommandFactory.getCommand(c);
						populateCommand(commandToEx,instructionCount,fileRows,plateauConfigMap,rover);
						commandToEx.execute();
						/** some of the instructions could lead to termination so check if we have such an instruction **/
						keepRunning = commandToEx.keepRunning();
						if(!keepRunning) {
							break;
						}
					}//for each char in instruction
					if(keepRunning) {
						/** if we are here all the instructions are good and executed now wait for user to enter next instruction **/
						printPlateau(fileRows);
						/** wait for user input **/
						System.out.println(ConstantsIfc.PROMPT_MSG);
						System.out.println(ConstantsIfc.PROMPT);
						instruction = userInputScan.nextLine();	
					}									
				}
			}//while
			
		}catch(Exception e) {
			/** log this exception **/
			e.printStackTrace();
		}
		
	}
	
	private void populateCommand(Command commandToEx,int countOfInstruction,List<String> fileRows,Map<String,Character> plateauConfigMap,Rover rover) {
		commandToEx.setCommandCount(countOfInstruction);
		commandToEx.setPlateauRows(fileRows);
		commandToEx.setPlateauConfigMap(plateauConfigMap);
		commandToEx.setRover(rover);
	}
	
	protected void printPlateau(List<String> fileRows) {
		System.out.println("''''''''''");
		fileRows.forEach(System.out::println);
		System.out.println("''''''''''");
	}
	
	protected void placeRoverAtStartingPosition(List<String> fileRows) {
		/** rover always starts at top left corner which is 0,0 and rover faces East **/
		StringBuilder firstRow = new StringBuilder(fileRows.get(0));
		firstRow.setCharAt(0,'X');
		fileRows.set(0, firstRow.toString());
		
	}
	protected Map<String,Character> initialisePlateau(List<String> fileRows){
		Map<String,Character> plateauConfigMap = new LinkedHashMap<>();
		int yAxisCounter = 0;
		/** for each row **/
		for(String row : fileRows) {
			/** for each character in row **/
			for(int x = 0;x<row.length();x++) {
				plateauConfigMap.put(String.valueOf(x)+","+ String.valueOf(yAxisCounter), row.charAt(x));
			}
			yAxisCounter++;
		}
		return plateauConfigMap;
	}
	
	
	
	public Notification validate(List<String> fileRows) {
		Notification notification = new Notification();
		if(null == fileRows || fileRows.size() <=0) {
			notification.addError(ConstantsIfc.ERROR_MSG_EMPTY_FILE);
			return notification;
		}
		if(fileRows.size() == 1 || fileRows.get(0).length() == 1 || (fileRows.size() == fileRows.get(0).length())) {
			/** single row file - does not qualify as a rectangle 
			 *  or even if a single row of the file is of width 1 then it is not acceptable
			 *  we dont check the width of other rows since we will be checking row width consistency later below 
			 **/
			notification.addError(ConstantsIfc.ERROR_MSG_RECTANGULAR_FILE);
		}else {
			/** check if we have irregular length of rows **/
			/** get length of first row as a sample size **/
			int sampleRowLength = fileRows.get(0).length();
			boolean areAllRowsOfSameLength = fileRows.stream().allMatch(row -> row.length() == sampleRowLength);
			if(!areAllRowsOfSameLength) {
				notification.addError(ConstantsIfc.ERROR_MSG_RECTANGULAR_FILE);
			}
		}
		
		/** content should be valid allowed chars **/
		boolean isFileContentValid = fileRows.stream().allMatch(row -> row.matches("[oR]+"));
		if(!isFileContentValid) {
			notification.addError(ConstantsIfc.ERROR_MSG_INVALID_CHARS);
		}
		
		/** Rock should not be present where the Rover is supposed to start at **/
		if(fileRows.get(0).charAt(0) == 'R') {
			notification.addError(ConstantsIfc.ERROR_MSG_INVALID_START_POSITION);
		}
		return notification;
	}
}
