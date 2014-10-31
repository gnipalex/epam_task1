package com.epam.hnyp.task9.capcha.provider;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.task9.capcha.Capcha;

public abstract class AbstractContextCapchaProvider extends AbstractCapchaProvider {
	public static final String CONTEXT_CAPCHA_MAP_KEY = 
			"com.epam.hnyp.task9.capchaservice.CONTEXT_CAPCHA_MAP_KEY";
	
	private final Map<String, Capcha> capchaMap = new ConcurrentHashMap<>();
	
	public AbstractContextCapchaProvider() {
	}

	public AbstractContextCapchaProvider(int capchaTimeToLive) {
		super(capchaTimeToLive);
	}

	@Override
	public void saveCapcha(HttpServletRequest req, HttpServletResponse resp,
			Capcha capcha) {
		String uid = UUID.randomUUID().toString();
		capchaMap.put(uid, capcha);
		setCapchaUidToClient(req, resp, uid);
	}

//	private Map<String, Capcha> getCapchaMap(HttpServletRequest req) {
//		Object obj = req.getServletContext().getAttribute(
//				CONTEXT_CAPCHA_MAP_KEY);
//		Map<String, Capcha> resultMap = null;
//		if (obj != null && obj instanceof Map) {
//			resultMap = (Map<String, Capcha>) obj;
//		} else {
//			resultMap = new ConcurrentHashMap<>();
//			req.getServletContext().setAttribute(CONTEXT_CAPCHA_MAP_KEY,
//					resultMap);
//		}
//		return resultMap;
//	}

	@Override
	public Capcha getCapcha(HttpServletRequest req)
			throws CapchaUidMissedException {
		String uid = getCapchaUidFromClient(req);
		return capchaMap.get(uid);
	}

	@Override
	public void clearAllExpiredCapcha(HttpServletRequest req) {
		Iterator<Capcha> capchaIterator = capchaMap.values().iterator();
		while (capchaIterator.hasNext()) {
			if (isCapchaExpired(capchaIterator.next())) {
				capchaIterator.remove();
			}
		}
	}

	protected abstract void setCapchaUidToClient(HttpServletRequest req,
			HttpServletResponse resp, String uid);

	protected abstract String getCapchaUidFromClient(HttpServletRequest req)
			throws CapchaUidMissedException;

}
