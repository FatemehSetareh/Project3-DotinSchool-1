package business;

import java.util.Date;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class CorporateCustomer {
    private String corporationName;
    private String financialCode;
    private Date registerDate;
    private Integer customerNumber;

    public Integer generateCustomerNumber(){
        customerNumber = Integer.parseInt(getFinancialCode())*5;
        return customerNumber;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }
}
