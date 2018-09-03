package org.coupons.security.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenHeader {

	// Token type
	private final String typ = "JWT";

	// Token signing algorithm
	private SignatureAlgorithm alg;

	// Content type parameter, in this case the value is ACCESS or REFRESH.
	private JWTComtentType cty;

	public TokenHeader(SignatureAlgorithm alg, JWTComtentType cty) {
		super();
		this.alg = alg;
		this.cty = cty;
	}

	public TokenHeader(JWTComtentType cty) {
		this(SignatureAlgorithm.HS256, cty);
	}

	public SignatureAlgorithm getAlg() {
		return alg;
	}

	public void setAlg(SignatureAlgorithm alg) {
		this.alg = alg;
	}

	public JWTComtentType getCty() {
		return cty;
	}

	public void setCty(JWTComtentType cty) {
		this.cty = cty;
	}

	public String getTyp() {
		return typ;
	}

	public String toJsonString() throws JsonProcessingException{
	
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string
		String jsonString = mapper.writeValueAsString(this);
		return jsonString;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alg == null) ? 0 : alg.hashCode());
		result = prime * result + ((cty == null) ? 0 : cty.hashCode());
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
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
		TokenHeader other = (TokenHeader) obj;
		if (alg != other.alg)
			return false;
		if (cty != other.cty)
			return false;
		if (typ == null) {
			if (other.typ != null)
				return false;
		} else if (!typ.equals(other.typ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TokenHeader [typ=" + typ + ", alg=" + alg + ", cty=" + cty + "]";
	}
	
}
