package com.epam.hnyp.task2.subtask3.command;

public abstract class AbstractCommand {

	public abstract void execute(String ...args);
	
	public abstract String about();
	
	public static class WrongInputArgumentsException extends Exception {
		public WrongInputArgumentsException() {
		}
		
		public WrongInputArgumentsException(String message) {
			super(message);
		}
	}
}
