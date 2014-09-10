package com.epam.hnyp.task3.subtask2;

import java.io.File;
import java.util.Date;

/**
 * Condition that checks file's modification date
 * @author Oleksandr_Hnyp
 *
 */
public class ModifyDateCondition extends BaseFileCondition {

	private Date to;
	private Date from;
	
	/**
	 * Creates condition
	 * @param from
	 * @param to
	 * @param next
	 * @throws IllegalArgumentException if from > to
	 */
	public ModifyDateCondition(Date from, Date to,BaseFileCondition next) {
		super(next);
		if (from.compareTo(to) > 0) {
			throw new IllegalArgumentException();
		}
		this.from = from;
		this.to = to;
	}

	@Override
	protected boolean internalCondition(File file) {
		Date modDate = new Date(file.lastModified());
		return modDate.compareTo(to) ;
	}

}
