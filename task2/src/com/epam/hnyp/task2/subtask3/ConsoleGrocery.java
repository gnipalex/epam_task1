package com.epam.hnyp.task2.subtask3;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.MainMenuCommand;

public class ConsoleGrocery {
	public static void main(String[] args) {
		AbstractCommand main = new MainMenuCommand();
		main.execute(new String[0]);
	}
}
