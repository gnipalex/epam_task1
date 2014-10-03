package com.epam.hnyp.task2.subtask3.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class MainMenuCommandTest {
	
	@Test
	public void testCommandCall() {
		final String comandKey = "1";
		
		IOProvider ioProviderMock = Mockito.mock(IOProvider.class);
		when(ioProviderMock.readLine()).thenReturn(comandKey).thenReturn(String.valueOf(MainMenuCommand.QUIT_SYMBOL));
		
		AbstractCommand commandMock = Mockito.mock(AbstractCommand.class);
		
		MainMenuCommand testCommand = new MainMenuCommand(ioProviderMock);
		testCommand.addCommand(comandKey, commandMock);
		testCommand.execute();
		
		verify(commandMock).execute();
	}
	

}
