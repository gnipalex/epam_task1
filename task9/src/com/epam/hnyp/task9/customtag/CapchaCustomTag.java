package com.epam.hnyp.task9.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CapchaCustomTag extends SimpleTagSupport {
	private String capchaId;
	private String name;
	private Boolean useHidden;
	private String url;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter wr = getJspContext().getOut();
		wr.write("<img src=\"" + url + "\" alt=\"capcha\" />");
		if (useHidden) {
			wr.write("<input type=\"hidden\" name=\"" + name + "\" value=\"" + capchaId + "\" />");
		}
		wr.flush();
	}

	public String getCapchaId() {
		return capchaId;
	}

	public void setCapchaId(String capchaId) {
		this.capchaId = capchaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getUseHidden() {
		return useHidden;
	}

	public void setUseHidden(Boolean useHidden) {
		this.useHidden = useHidden;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
