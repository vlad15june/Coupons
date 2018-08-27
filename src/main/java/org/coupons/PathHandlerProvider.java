package org.coupons;

import org.coupons.handlers.admin.*;
import org.coupons.handlers.company.*;
import org.coupons.handlers.coupons.GetAllCouponsCategoryHandlerGet;
import org.coupons.handlers.coupons.GetAllCouponsHandlerGet;
import org.coupons.handlers.coupons.GetAllCouponsPriceHandlerGet;
import org.coupons.handlers.customer.*;
import org.coupons.handlers.login.*;

import com.networknt.handler.*;

import io.undertow.*;
import io.undertow.server.*;
import io.undertow.util.*;

public class PathHandlerProvider implements HandlerProvider {

	private static final String ADMIN_USER_ROUTE = "/coupons/users/admin";
	private static final String CUSTOMER_USER_ROUTE = "/coupons/users/customer/{customerId}";
	private static final String COMPANY_USER_ROUTE = "/coupons/users/company/{companyId}";

	@Override
	public HttpHandler getHandler() {
		return Handlers.routing()
				// LOGIN
				.add(Methods.POST, "/coupons/login", new LoginHandlerPost())

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
				.add(Methods.GET, CUSTOMER_USER_ROUTE, new CustomerSelfDetailsHandlerGet())
				
				//COUPONS
				.add(Methods.GET, "/coupons", new GetAllCouponsHandlerGet())
				.add(Methods.GET, "/coupons/price/{price}", new GetAllCouponsPriceHandlerGet())
				.add(Methods.GET, "/coupons/category/{category}", new GetAllCouponsCategoryHandlerGet());
		
			
	}
}
