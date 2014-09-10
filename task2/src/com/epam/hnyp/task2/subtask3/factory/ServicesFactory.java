package com.epam.hnyp.task2.subtask3.factory;

public interface ServicesFactory {
	/**
	 * 
	 * @param advMax max count of elements of advertisement
	 * @return
	 */
	ServicesContainer buildServicesContainer(int advMax);
}
