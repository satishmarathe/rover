package com.rover.service;

import com.rover.common.ConstantsIfc;

public class CommandRightTurn extends Command {

	@Override
	public void execute() {
		changeRoverOrientationToRight();		
	}
	
	private void changeRoverOrientationToRight() {
		getRover().setOrientation(ConstantsIfc.RIGHT_TURN.get(getRover().getOrientation()));
	}

}
