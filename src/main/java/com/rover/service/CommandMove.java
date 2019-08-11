package com.rover.service;

import java.util.List;

public class CommandMove extends Command {

	@Override
	public void execute() {
		Character currentRoverOrientation = getRover().getOrientation();
		
		int oldX = getRover().getxCordinate();
		int oldY = getRover().getyCordinate();
		
		/** when rover moves it will have new co-ordinates **/
		int newX = oldX;
		int newY = oldY;
		
		/** calculate new co-ordinates **/
		if(currentRoverOrientation == 'N') {
			/** --y **/
			newY = oldY -1;
		}else if(currentRoverOrientation == 'S') {
			/** ++ y **/
			newY = oldY +1;
		}else if(currentRoverOrientation == 'W') {
			/** -- x **/
			newX = oldX  - 1;
		}else if(currentRoverOrientation == 'E') {
			/** ++ x **/
			newX = oldX  + 1;
		}
		
		/** using these new co-ordinates find corresponding object on plateau **/
		String newMappingKey = newX + "," + newY;
		
		Character newDestinationObjectType = getPlateauConfigMap().get(newMappingKey);
		
		/**
		 * if we have stepped outside of the plateau perimeter we will get a null
		 * or we could get a valid location to place Rover
		 * or we could get a Rock on which we cannot place the Rover
		 */
		
		if(null == newDestinationObjectType) {
			/** the instruction is not valid since this places Rover outside the perimeter **/
			setErrorCommandCount(getErrorCommandCount()+1);
			System.err.println("<<< Invalid command - we cannot go beyond the Perimeter of Plateau !!! ");
			
		}else if(newDestinationObjectType == 'R') {
			/** its a Rock we cant go here **/
			setErrorCommandCount(getErrorCommandCount()+1);
			System.err.println("<<< Invalid command - we hit a ROCK !!! ");
			
		}else if(newDestinationObjectType == 'o') {
			/** this is favourable terrain Rover can be placed here 
			 *  Orientation of rover will not change only its position
			 **/
			getRover().setxCordinate(newX);
			getRover().setyCordinate(newY);
			
			setRoverPositionOnPlateau(getPlateauRows(),oldX,oldY,newX,newY);
			
		}		
	}
	
	private void setRoverPositionOnPlateau(List<String> plateauRows,int oldX,int oldY,int newX,int newY) {
		/** change old position of Plateau to a non rock again since Rover has moved from this place **/
		resetPlateauPositionAsPlain(plateauRows,oldX,oldY);
		
		if(oldY == newY && (oldX != newX)) {
			/** change is on X-axis in same row **/
			StringBuilder rowOldPosition = new StringBuilder(plateauRows.get(oldY));
			rowOldPosition.setCharAt(newX, 'X');
			plateauRows.set(oldY, rowOldPosition.toString());
		}else if(oldX == newX && (oldY != newY)) {
			/** change is on Y-axis in a different row **/
			StringBuilder rowNewPosition = new StringBuilder(plateauRows.get(newY));
			rowNewPosition.setCharAt(oldX, 'X');
			plateauRows.set(newY, rowNewPosition.toString());
		}
	}
	
	private void resetPlateauPositionAsPlain(List<String> plateauRows,int oldX,int oldY) {
		/** Rover has moved to a new position so this old position needs to be reset **/
		StringBuilder rowOldPosition = new StringBuilder(plateauRows.get(oldY));
		rowOldPosition.setCharAt(oldX, 'o');
		plateauRows.set(oldY, rowOldPosition.toString());
	}

}
