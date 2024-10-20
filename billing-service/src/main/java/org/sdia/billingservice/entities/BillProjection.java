package org.sdia.billingservice.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

//http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?customerId=1&projection=fullBill
@Projection(name = "fullBill", types = Bill.class)
public interface BillProjection {
    Long getId();
    Date getBillDate();
    Long getCustomerId();
}
