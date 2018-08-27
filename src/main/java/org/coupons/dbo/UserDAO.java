package org.coupons.dbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.coupons.pojo.Role;
import org.coupons.pojo.User;
import org.coupons.util.Algorithms;
import org.coupons.util.ConnectionPool;
import org.coupons.util.Hasher;

public final class UserDAO {
	
	public static User getUser(String email, String password) throws SQLException {
		String hashedPass = Hasher.hashEncode(password.getBytes(), Algorithms.SHA256);
		String sql = "select * from USERS where EMAIL like ? and PASSWORD like ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, hashedPass);
			ResultSet resultset = preparedStatement.executeQuery();
			
			if(resultset.next()) {
				User user = new User();
				user.setEmail(resultset.getString("EMAIL"));
				user.setPassword(resultset.getString("PASSWORD"));
				user.setRole(Role.valueOf(resultset.getString("ROLE")));
				user.setUserId(resultset.getString("USER_ID"));
				return user;
			}else {
				System.out.println("Empty resultSet");
				return null;
			}
			
		}
		
	}

}
