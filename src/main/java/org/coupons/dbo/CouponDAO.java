package org.coupons.dbo;

import java.util.*;

import org.coupons.pojo.*;

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

	public static List<Coupon> getAllCoupons() {
		return new ArrayList<>();
	}

	public static List<Coupon> getCouponsByCategory() {
		return new ArrayList<>();
	}

	public static List<Coupon> getCouponsByPrice() {
		return new ArrayList<>();
	}

	public static Coupon getOneCoupon(int couponId) {
		return new Coupon();
	}

	public static int addCouponPurchase(int customerId, int couponId) {
		return 0;
	}

	public static int deleteCouponPurchase(int customerId, int couponId) {
		return 0;
	}

	public static int deleteCouponPurchase(int couponId) {
		return 0;
	}

}