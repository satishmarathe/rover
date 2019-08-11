package com.rover.service;

import com.rover.common.ConstantsIfc;

public class CommandLeftTurn extends Command {

	@Override
	public void execute() {
		changeRoverOrientationToLeft();
		
	}
	
	private void changeRoverOrientationToLeft() {
		getRover().setOrientation(ConstantsIfc.LEFT_TURN.get(getRover().getOrientation()));
	}

}
