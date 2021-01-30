package com.ftn.xml.dto;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenStateDTO {

	private String authenticationToken;
	private int expiresAt;
	private String email;
	private String uloga;

	public UserTokenStateDTO() {
	}

	public UserTokenStateDTO(String authenticationToken, int expiresAt) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
	}

	public UserTokenStateDTO(String authenticationToken, int expiresAt, String email) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
		this.email = email;

	}

	public UserTokenStateDTO(String authenticationToken, int expiresAt, String email, String uloga) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
		this.email = email;
		this.uloga = uloga;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public int getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
