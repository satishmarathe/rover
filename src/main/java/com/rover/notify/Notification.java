package com.rover.notify;

import java.util.ArrayList;
import java.util.List;

public class Notification {
	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	} 
	
	public void addError(String errorMessage) {
		errors.add(errorMessage);
	}
	
	public boolean hasErrors() {
		return ! errors.isEmpty();
	}
}
