package com.epam.hnyp.task9.capcha.provider;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.task9.capcha.Capcha;

public abstract class AbstractCapchaProvider {
	public static final int DEFAULT_CAPCHA_TTL = 100;
	
	private int capchaTimeToLive;
	
	public AbstractCapchaProvider() {
		this(DEFAULT_CAPCHA_TTL);
	}
	
	public AbstractCapchaProvider(int capchaTimeToLive) {
		this.capchaTimeToLive = capchaTimeToLive;
	}

	/**
	 * Saves capcha on server side, passing to client capcha uid if needed
	 * @param req {@link HttpServletRequest}
	 * @param resp {@link HttpServletResponse}
	 * @param capcha
	 */
	public abstract void saveCapcha(HttpServletRequest req, HttpServletResponse resp, Capcha capcha);
	
	/**
	 * Retrieves capcha from the scope, reads capcha uid from request if needed
	 * @param req {@link HttpServletRequest}
	 * @return {@link Capcha} object
	 * @throws CapchaUidMissedException if implementation uses capcha uid 
	 * and it can not be retrieved from request
	 */
	public abstract Capcha getCapcha(HttpServletRequest req) throws CapchaUidMissedException;

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
	 * Clears all expired capcha from the scope
	 * @param req {@link HttpServletRequest}
	 */
	public abstract void clearAllExpiredCapcha(HttpServletRequest req);
	
	/**
	 * Is being thrown if capcha uid can not be retrieved from request
	 * @author Oleksandr_Hnyp
	 *
	 */
	public static class CapchaUidMissedException extends ServletException {
		public CapchaUidMissedException() {
		}

		public CapchaUidMissedException(String message) {
			super(message);
		}
	}
}
