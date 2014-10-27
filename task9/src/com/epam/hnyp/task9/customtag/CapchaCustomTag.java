package com.epam.hnyp.task9.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CapchaCustomTag extends SimpleTagSupport {
	private String capchaId;
	private String hiddenName;
	private Boolean useHidden;
	private String url;
	private Boolean useCapchaField;
	private String capchaFieldName;
	private String divCss;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter wr = getJspContext().getOut();
		divCss = divCss != null ? divCss : "";
		wr.println("<div class=\"" + divCss + "\">");
		wr.println("<img src=\"" + url + "\" alt=\"capcha\" />");
		wr.println("</div>");
		if (useCapchaField) {
			wr.println("<input class=\"text\" type=\"text\" value=\"\" maxlength=\"50\" size=\"30\" name=\"" + capchaFieldName +  "\" />");
		}	
		if (useHidden) {
			wr.println("<input type=\"hidden\" name=\"" + hiddenName + "\" value=\"" + capchaId + "\" />");
		}
		wr.flush();
	}

	public String getCapchaId() {
		return capchaId;
	}

	public void setCapchaId(String capchaId) {
		this.capchaId = capchaId;
	}

	public String getHiddenName() {
		return hiddenName;
	}

	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
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

	public Boolean getUseCapchaField() {
		return useCapchaField;
	}

	public void setUseCapchaField(Boolean useCapchaField) {
		this.useCapchaField = useCapchaField;
	}

	public String getCapchaFieldName() {
		return capchaFieldName;
	}

	public void setCapchaFieldName(String capchaFieldName) {
		this.capchaFieldName = capchaFieldName;
	}

	public String getDivCss() {
		return divCss;
	}

	public void setDivCss(String divCss) {
		this.divCss = divCss;
	}
	
}
