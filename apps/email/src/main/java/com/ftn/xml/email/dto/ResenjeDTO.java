package com.ftn.xml.email.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResenjeDTO {
	private String naslov;
	private String to;
	private String content;
	private String pdf;
	private String html;

	public ResenjeDTO() {
		super();
	}

	public ResenjeDTO(String naslov, String to, String content, String pdf, String html) {
		super();
		this.naslov = naslov;
		this.to = to;
		this.content = content;
		this.pdf = pdf;
		this.html = html;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
