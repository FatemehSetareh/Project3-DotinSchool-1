package business;


public class LegalCustomer extends Customer{
    private String corporationName;
    private String financialCode;
    private String registerDate;
    private Integer customerNumber;

    public LegalCustomer(Integer customerNumber, String corporationName, String financialCode, String registerDate) {
        this.customerNumber = customerNumber;
        this.corporationName = corporationName;
        this.financialCode = financialCode;
        this.registerDate = registerDate;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public String getFinancialCode() {
        return financialCode;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

}
