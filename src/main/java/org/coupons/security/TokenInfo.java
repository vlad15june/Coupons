package org.coupons.security;

import java.time.Instant;

import org.coupons.pojo.Role;
import org.coupons.security.pojo.JWTComtentType;

public class TokenInfo {
	
    private String userId;
    private Role role;
    private Instant issued;
    private Instant expires;
    private JWTComtentType cty;
   
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Instant getIssued() {
        return issued;
    }
    public void setIssued(Instant issued) {
        this.issued = issued;
    }
    public Instant getExpires() {
        return expires;
    }
    public void setExpires(Instant expires) {
        this.expires = expires;
    }
}