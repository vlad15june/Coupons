package org.coupons.security.pojo;

import java.net.URI;
import java.time.Instant;

import org.coupons.pojo.Role;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenPayload {

	// user's info, the value of those tow claims is a case-sensitive string.
	// used to user's authentication and authorization
	private String userId;
	private Role userRole;

	// The "iss" (issuer) claim identifies the principal that issued the
	// JWT. The processing of this claim is generally application specific.
	// The "iss" value is a case-sensitive string containing a StringOrURI
	// value. Use of this claim is OPTIONAL.
	private String iss;

	// The "exp" (expiration time) claim identifies the expiration time on
	// or after which the JWT MUST NOT be accepted for processing. The
	// processing of the "exp" claim requires that the current date/time
	// MUST be before the expiration date/time listed in the "exp" claim.
	private Instant exp;

	// The URI that the user authorized to access.
	private URI resourceURI;

	
	
	public TokenPayload(String userId, Role userRole, String iss, Instant exp, URI resourceURI) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.iss = iss;
		this.exp = exp;
		this.resourceURI = resourceURI;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public Instant getExp() {
		return exp;
	}

	public void setExp(Instant exp) {
		this.exp = exp;
	}

	public URI getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(URI resourceURI) {
		this.resourceURI = resourceURI;
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
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
		result = prime * result + ((iss == null) ? 0 : iss.hashCode());
		result = prime * result + ((resourceURI == null) ? 0 : resourceURI.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
		TokenPayload other = (TokenPayload) obj;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		if (iss == null) {
			if (other.iss != null)
				return false;
		} else if (!iss.equals(other.iss))
			return false;
		if (resourceURI == null) {
			if (other.resourceURI != null)
				return false;
		} else if (!resourceURI.equals(other.resourceURI))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userRole != other.userRole)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TokenPayload [userId=" + userId + ", userRole=" + userRole + ", iss=" + iss + ", exp=" + exp
				+ ", resourceURI=" + resourceURI + "]";
	}

}
