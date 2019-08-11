package com.rover.service;

import com.rover.common.ConstantsIfc;

public class CommandInvalid extends Command{

	@Override
	public void execute() {
		System.err.println(ConstantsIfc.ERROR_MSG_INVALID_INPUT);
		setErrorCommandCount(getErrorCommandCount()+1);
	}

}
