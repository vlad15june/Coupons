package org.coupons.login;

import java.sql.SQLException;

import org.coupons.dbo.*;
import org.coupons.pojo.*;

public final class LoginManager {

	public static boolean login(User user) throws SQLException {
		User tempUser = UserDAO.getUser(user.getEmail(), user.getPassword());
		if (tempUser != null) {
			// TODO:
			// add id and Role as Claim to JWT payload
			return true;
		} else
			return false;
	}

}
