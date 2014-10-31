package com.epam.hnyp.task9.capcha.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiddenCapchaProvider extends AbstractContextCapchaProvider {
	public static final String HIDDEN_PARAM_NAME = "capchaUid";

	public HiddenCapchaProvider(int capchaTimeToLive) {
		super(capchaTimeToLive);
	}

	@Override
	protected void setCapchaUidToClient(HttpServletRequest req,
			HttpServletResponse resp, String uid) {
		req.setAttribute(HIDDEN_PARAM_NAME, uid);
	}

	@Override
	protected String getCapchaUidFromClient(HttpServletRequest req)
			throws CapchaUidMissedException {
		String capchaUidHidden = req.getParameter(HIDDEN_PARAM_NAME);
		if (capchaUidHidden != null && !capchaUidHidden.isEmpty()) {
			return capchaUidHidden;
		}
		return null;
	}

}
