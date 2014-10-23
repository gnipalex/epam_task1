package com.epam.hnyp.task9.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.epam.hnyp.task9.util.Capcha;

public abstract class AbstractCapchaService {
	public static final int DEFAULT_CAPCHA_TTL = 100;
	public static final String CAPCHA_MAP_IN_SCOPE_KEY = "com.epam.hnyp.task9.service.CAPCHA_MAP_IN_SCOPE_KEY";
	private int capchaTimeToLive;

	public AbstractCapchaService() {
		this(DEFAULT_CAPCHA_TTL);
	}
	
	/**
	 * @param capchaTimeToLive - time in seconds during which capcha still actual
	 */
	public AbstractCapchaService(int capchaTimeToLive) {
		this.capchaTimeToLive = capchaTimeToLive;
	}
	
	/**
	 * 
	 * @param req {@link HttpServletRequest} object
	 * @param uid unique id of capcha
	 * @param capcha {@link Capcha} object
	 * @throws ScopeNotFoundException if capcha cannot be stored in storage scope
	 */
	public void saveCapcha(HttpServletRequest req, String uid, Capcha capcha) 
			throws ScopeNotFoundException {
		Map<String, Capcha> capchaMap = getCapchaMap(req, CAPCHA_MAP_IN_SCOPE_KEY);
		capchaMap.put(uid, capcha);
	}
	
	/**
	 * 
	 * @param req {@link HttpServletRequest}
	 * @param uid unique id of capcha
	 * @throws ScopeNotFoundException if capcha cannot be stored in storage scope
	 * @return null if capcha object not found
	 */
	public Capcha getCapcha(HttpServletRequest req, String uid) 
			throws ScopeNotFoundException {
		Map<String, Capcha> capchaMap = getCapchaMap(req, CAPCHA_MAP_IN_SCOPE_KEY);
		return capchaMap.get(uid);
	}
	
	/**
	 * 
	 * @param req {@link HttpServletRequest}
	 * @param uid unique id of capcha
	 * @throws ScopeNotFoundException if capcha cannot be stored in storage scope
	 */
	public void removeCapcha(HttpServletRequest req, String uid) throws ScopeNotFoundException {
		Map<String, Capcha> capchaMap = getCapchaMap(req, CAPCHA_MAP_IN_SCOPE_KEY);
		capchaMap.remove(uid);
	}
	
	/**
	 * If since capcha creation passed more than capchaTimeToLive seconds capcha considered expired
	 * @param capcha {@link Capcha} object
	 * @param tTL int (time to live) time during which capcha still actual
	 * @return false if capcha is expired
	 */
	public boolean isCapchaExpired(Capcha capcha) {
		long capchaSeconds = capcha.getCreatedTime().getTime()/1000;
		long curentSeconds = new Date().getTime()/1000;
		return curentSeconds - capchaSeconds > capchaTimeToLive;
	}
	
	/**
	 * Clears all expired capcha from scope
	 * @param req
	 */
	public void clearAllExpiredCapcha(HttpServletRequest req) {
		try {
			Map<String, Capcha> capchaMap = getCapchaMap(req, CAPCHA_MAP_IN_SCOPE_KEY);
			synchronized (capchaMap) {
				for (String key : new HashSet<>(capchaMap.keySet())) {
					Capcha capcha = capchaMap.get(key);
					if (isCapchaExpired(capcha)) {
						capchaMap.remove(key);
					}
				}
			}
		} catch (ScopeNotFoundException e) {
			//log
		}
	}
	
	/**
	 * Retrieves {@link Map} of capcha UID and {@link Capcha} objects
	 * @param req {@link HttpServletRequest} object
	 * @return
	 */
	protected abstract Map<String, Capcha> getCapchaMap(HttpServletRequest req, String key) throws ScopeNotFoundException;
	
	public int getCapchaTimeToLive() {
		return capchaTimeToLive;
	}
	
	public void setCapchaTimeToLive(int capchaTimeToLive) {
		this.capchaTimeToLive = capchaTimeToLive;
	}

	/**
	 * Is being thrown if scope for capcha storing unavailable or doesn't exist
	 * @author Oleksandr_Hnyp
	 *
	 */
	public static class ScopeNotFoundException extends ServletException {

		public ScopeNotFoundException() {
			super();
		}

		public ScopeNotFoundException(String message, Throwable rootCause) {
			super(message, rootCause);
		}

		public ScopeNotFoundException(String message) {
			super(message);
		}

		public ScopeNotFoundException(Throwable rootCause) {
			super(rootCause);
		}
		
	}
}
