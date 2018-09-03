package org.coupons.security.pojo;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JSONWebToken {

	private TokenHeader header;
	private TokenPayload payload;

	public JSONWebToken(TokenHeader header, TokenPayload payload) {
		super();
		this.header = header;
		this.payload = payload;
	}

	public TokenHeader getHeader() {
		return header;
	}

	public void setHeader(TokenHeader header) {
		this.header = header;
	}

	public TokenPayload getPayload() {
		return payload;
	}

	public void setPayload(TokenPayload payload) {
		this.payload = payload;
	}

	public String toJWTFormatedString() throws JsonProcessingException{
		
		final String jsonHeader = Base64.encodeBase64String(this.header.toJsonString().getBytes());
		final String jsonPayload = Base64.encodeBase64String(this.payload.toJsonString().getBytes());
		
		return jsonHeader + "." + jsonPayload;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONWebToken other = (JSONWebToken) obj;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JSONWebToken [header=" + header + ", payload=" + payload + "]";
	}

}
