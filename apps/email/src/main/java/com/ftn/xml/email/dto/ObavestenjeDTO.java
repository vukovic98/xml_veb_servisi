package com.ftn.xml.email.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObavestenjeDTO {

	private String naslov;
	private String to;
	private String content;
	private String pdf;
	private String html;

	public ObavestenjeDTO() {
		super();
	}

	public ObavestenjeDTO(String naslov, String content, String pdf, String html) {
		super();
		this.naslov = naslov;
		this.content = content;
		this.pdf = pdf;
		this.html = html;
	}

	public String getNaslov() {
		return naslov;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

}
