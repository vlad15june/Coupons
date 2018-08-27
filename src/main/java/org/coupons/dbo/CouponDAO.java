package org.coupons.dbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.coupons.pojo.*;
import org.coupons.util.ConnectionPool;

public final class CouponDAO {

	public static int addCoupon(Coupon coupon) {
		return 0;
	}

	public static int updateCoupon(Coupon coupon) {
		return 0;
	}

	public static int deleteCoupon(int CouponId) {
		return 0;
	}

	public static List<Coupon> getAllCoupons() throws SQLException {
		String sql = "select * from COUPONS";
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			ResultSet resultset = preparedStatement.executeQuery();
			List<Coupon> coupons = new ArrayList<>();

			while (resultset.next()) {
				Coupon coupon = new Coupon();
				coupon.setAmount(resultset.getInt("AMOUNT"));
				coupon.setCategory(Category.valueOf(resultset.getString("CATEGORY")));
				coupon.setCouponId(resultset.getString("COUPON_ID"));
				coupon.setCompanyId(resultset.getString("COMPANY_ID"));
				coupon.setDescription(resultset.getString("DESCRIPTION"));
				coupon.setExpiryDate(resultset.getDate("EXPIRY_DATE"));
				coupon.setImageUrl(resultset.getString("IMAGE_URL"));
				coupon.setPrice(resultset.getDouble("PRICE"));
				coupon.setStartDate(resultset.getDate("START_DATE"));
				coupon.setTitle(resultset.getString("TITLE"));
				coupons.add(coupon);
			}

			return coupons;
		}
	}

	public static List<Coupon> getCouponsByCategory(Category category) throws SQLException {
		String sql = "select * from COUPONS where CATEGORY like ?";

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, category.name());

			ResultSet resultset = preparedStatement.executeQuery();
			List<Coupon> coupons = new ArrayList<>();

			while (resultset.next()) {
				Coupon coupon = new Coupon();
				coupon.setAmount(resultset.getInt("AMOUNT"));
				coupon.setCategory(Category.valueOf(resultset.getString("CATEGORY")));
				coupon.setCouponId(resultset.getString("COUPON_ID"));
				coupon.setCompanyId(resultset.getString("COMPANY_ID"));
				coupon.setDescription(resultset.getString("DESCRIPTION"));
				coupon.setExpiryDate(resultset.getDate("EXPIRY_DATE"));
				coupon.setImageUrl(resultset.getString("IMAGE_URL"));
				coupon.setPrice(resultset.getDouble("PRICE"));
				coupon.setStartDate(resultset.getDate("START_DATE"));
				coupon.setTitle(resultset.getString("TITLE"));
				coupons.add(coupon);
			}

			return coupons;
		}
	}

	public static List<Coupon> getCouponsByPrice(double price) throws SQLException {
		String sql = "select * from COUPONS where PRICE <= ?";

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setDouble(1, price);

			ResultSet resultset = preparedStatement.executeQuery();
			List<Coupon> coupons = new ArrayList<>();

			while (resultset.next()) {
				Coupon coupon = new Coupon();
				coupon.setAmount(resultset.getInt("AMOUNT"));
				coupon.setCategory(Category.valueOf(resultset.getString("CATEGORY")));
				coupon.setCouponId(resultset.getString("COUPON_ID"));
				coupon.setCompanyId(resultset.getString("COMPANY_ID"));
				coupon.setDescription(resultset.getString("DESCRIPTION"));
				coupon.setExpiryDate(resultset.getDate("EXPIRY_DATE"));
				coupon.setImageUrl(resultset.getString("IMAGE_URL"));
				coupon.setPrice(resultset.getDouble("PRICE"));
				coupon.setStartDate(resultset.getDate("START_DATE"));
				coupon.setTitle(resultset.getString("TITLE"));
				coupons.add(coupon);
			}

			return coupons;
		}
	}

	public static Coupon getOneCoupon(int couponId) {
		return new Coupon();
	}

	public static int addPurchase(int customerId, int couponId) {
		return 0;
	}

	public static int deletePurchase(int customerId, int couponId) {
		return 0;
	}

	public static int deletePurchasesOfCustomer(int customerId) {
		return 0;
	}

	public static int deletePurchasesByCoupon(int couponId) {
		return 0;
	}

}