package org.coupons.dbo;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.coupons.pojo.*;
import org.coupons.util.Algorithms;
import org.coupons.util.ConnectionPool;
import org.coupons.util.Hasher;

public final class UserDAO {
	
	public static User getUser(User user) throws SQLException {
		String password = Hasher.hashEncode(user.getPassword().getBytes(), Algorithms.SHA256);
		String sql = String.format("select * from USERS where EMAIL='%s' AND PASSWORD='%s' AND ROLE='%s'",
				user.getEmail(), password, user.getRole().name());
		
		User resUser = null;
		
		try (Connection connection = ConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery()) {

			if(resultset.next()) {
				resUser = new User();
				resUser.setEmail(resultset.getString("EMAIL"));
				resUser.setId(resultset.getString("ID"));
				resUser.setPassword(resultset.getString("PASSWORD"));
				resUser.setRole(Role.valueOf(resultset.getString("ROLE")));
			}
		}
		
		return resUser;
	}

}
