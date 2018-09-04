package org.coupons;

import org.coupons.handlers.admin.AdminCreateCompanyHandlerPost;
import org.coupons.handlers.admin.AdminCreateCustomerHandlerPost;
import org.coupons.handlers.admin.AdminGetCompaniesHandlerGet;
import org.coupons.handlers.admin.AdminGetCompanyHandlerGet;
import org.coupons.handlers.admin.AdminGetCustomerHandlerGet;
import org.coupons.handlers.admin.AdminGetCustomersHandlerGet;
import org.coupons.handlers.admin.AdminRemoveCompanyHandlerDelete;
import org.coupons.handlers.admin.AdminRemoveCustomerHandlerDelete;
import org.coupons.handlers.admin.AdminUpdateCompanyHandlerPut;
import org.coupons.handlers.admin.AdminUpdateCustomerHandlerPut;
import org.coupons.handlers.company.CompanyCreateCouponHandlerPost;
import org.coupons.handlers.company.CompanyGetCouponHandlerGet;
import org.coupons.handlers.company.CompanyGetCouponsCategoryHandlerGet;
import org.coupons.handlers.company.CompanyGetCouponsHandlerGet;
import org.coupons.handlers.company.CompanyGetCouponsPriceHandlerGet;
import org.coupons.handlers.company.CompanyRemoveCouponHandlerDelete;
import org.coupons.handlers.company.CompanySelfDetailsHandlerGet;
import org.coupons.handlers.company.CompanyUpdateCouponHandlerPut;
import org.coupons.handlers.customer.CustomerGetCouponsCategoryHnadlerGet;
import org.coupons.handlers.customer.CustomerGetCouponsHandlerGet;
import org.coupons.handlers.customer.CustomerGetCouponsPriceHandlerGet;
import org.coupons.handlers.customer.CustomerPurchaseCouponHandlerPost;
import org.coupons.handlers.customer.CustomerSelfDetailsHandlerGet;
import org.coupons.handlers.login.CheckLoginHandlerGet;
import org.coupons.handlers.login.LoginHandlerPost;
import org.coupons.util.Constants;

import com.networknt.handler.HandlerProvider;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.util.Methods;

public class PathHandlerProvider implements HandlerProvider {

	private static final String ADMIN_USER_ROUTE = "/coupons/users/admin";
	private static final String CUSTOMER_USER_ROUTE = "/coupons/users/customer/{customerId}";
	private static final String COMPANY_USER_ROUTE = "/coupons/users/company/{companyId}";

	@Override
	public HttpHandler getHandler() {
		return Handlers.routing()
				// LOGIN
				.add(Methods.POST, Constants.LOGIN_END_POINT, new LoginHandlerPost())
				.add(Methods.GET, Constants.LOGIN_END_POINT, new CheckLoginHandlerGet())

				// ADMIN OPS ON COMPANY
				.add(Methods.POST, ADMIN_USER_ROUTE + "/companies", new AdminCreateCompanyHandlerPost())
				.add(Methods.GET, ADMIN_USER_ROUTE + "/companies", new AdminGetCompaniesHandlerGet())
				.add(Methods.PUT, ADMIN_USER_ROUTE + "/companies", new AdminUpdateCompanyHandlerPut())
				.add(Methods.DELETE, ADMIN_USER_ROUTE + "/companies/{companyId}", new AdminRemoveCompanyHandlerDelete())
				.add(Methods.GET, ADMIN_USER_ROUTE + "/companies/{companyId}", new AdminGetCompanyHandlerGet())

				// ADMIN OPS ON CUSTOMER
				.add(Methods.POST, ADMIN_USER_ROUTE + "/customers", new AdminCreateCustomerHandlerPost())
				.add(Methods.DELETE, ADMIN_USER_ROUTE + "/customers/{customerId}",
						new AdminRemoveCustomerHandlerDelete())
				.add(Methods.PUT, ADMIN_USER_ROUTE + "/customers", new AdminUpdateCustomerHandlerPut())
				.add(Methods.GET, ADMIN_USER_ROUTE + "/customers/{customerId}", new AdminGetCustomerHandlerGet())
				.add(Methods.GET, ADMIN_USER_ROUTE + "/customers", new AdminGetCustomersHandlerGet())

				// COMPANY
				.add(Methods.POST, COMPANY_USER_ROUTE + "/coupons", new CompanyCreateCouponHandlerPost())
				.add(Methods.DELETE, COMPANY_USER_ROUTE + "/coupons/{couponId}", new CompanyRemoveCouponHandlerDelete())
				.add(Methods.PUT, COMPANY_USER_ROUTE + "/coupons", new CompanyUpdateCouponHandlerPut())
				.add(Methods.GET, COMPANY_USER_ROUTE + "/coupons/{couponId}", new CompanyGetCouponHandlerGet())
				.add(Methods.GET, COMPANY_USER_ROUTE + "/coupons", new CompanyGetCouponsHandlerGet())
				.add(Methods.GET, COMPANY_USER_ROUTE + "/coupons/category/{category}",
						new CompanyGetCouponsCategoryHandlerGet())
				.add(Methods.GET, COMPANY_USER_ROUTE + "/coupons/price/{price}", new CompanyGetCouponsPriceHandlerGet())
				.add(Methods.GET, COMPANY_USER_ROUTE, new CompanySelfDetailsHandlerGet())

				// CUSTOMER
				.add(Methods.POST, CUSTOMER_USER_ROUTE + "/coupons/{couponId}", new CustomerPurchaseCouponHandlerPost())
				.add(Methods.GET, CUSTOMER_USER_ROUTE + "/coupons", new CustomerGetCouponsHandlerGet())
				.add(Methods.GET, CUSTOMER_USER_ROUTE + "/coupons/category/{category}",
						new CustomerGetCouponsCategoryHnadlerGet())
				.add(Methods.GET, CUSTOMER_USER_ROUTE + "/coupons/price/{price}",
						new CustomerGetCouponsPriceHandlerGet())
				.add(Methods.GET, CUSTOMER_USER_ROUTE, new CustomerSelfDetailsHandlerGet());
	}
}
