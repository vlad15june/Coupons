package org.coupons.facade;

import org.coupons.dbo.*;
import org.coupons.pojo.*;

public class AdminFacade {

	public static void addCompany(Company company) {
		CompanyDAO.addCompany(company);
	}

}
