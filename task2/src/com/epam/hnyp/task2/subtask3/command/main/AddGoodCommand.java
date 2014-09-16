package com.epam.hnyp.task2.subtask3.command.main;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.creator.GoodCreator;

public class AddGoodCommand extends AbstractCommand {

	public static final String CREATOR_CLASS_PARAM = "AddGoodCommand_CREATOR_CLASS";

	@Override
	public void execute(String... args) {
		// в args должна быть задана строка задающая режим работы
		GoodCreator goodCreator = buildCreator(args);
		if (goodCreator == null) {
			return;
		}
		//менюшка с товарами
	}
	
	private GoodCreator buildCreator(String[] args) {
		GoodCreator goodCreator = null;
		String className = null;
		for (String s : args) {
			if (s.startsWith(CREATOR_CLASS_PARAM)) {
				String[] split = s.split(":");
				if (split == null || split.length < 2) {
					System.out
							.println("##error, config implementation to add good##");
					return null;
				}
				className = split[1];
				break;
			}
		}
		try {
			goodCreator = (GoodCreator) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			System.out
					.println("##error, implementation of good creator not found##");
			return null;
		} catch (InstantiationException e) {
			System.out
					.println("##error, implementation instantiation##");
			return null;
		} catch (Exception e) {
			System.out.println("##error good cannot be added##");
			return null;
		}
		return goodCreator;
	}

	@Override
	public String about() {
		return "add new good to store";
	}

}
