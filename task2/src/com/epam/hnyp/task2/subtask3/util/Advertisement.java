package com.epam.hnyp.task2.subtask3.util;

import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Good;

public interface Advertisement {
	void put(Good g);
	Collection<Good> getLastGoods();
}
