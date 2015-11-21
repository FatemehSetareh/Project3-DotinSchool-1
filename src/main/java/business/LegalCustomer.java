package business;


public class LegalCustomer extends Customer {
    private String corporationName;
    private String financialCode;
    private String registerDate;
    private Integer customerNumber;
    private String customerType;

    public LegalCustomer(Integer customerNumber,String customerType, String corporationName, String financialCode, String registerDate) {
        this.customerNumber = customerNumber;
        this.customerType = customerType;
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

    public String getCustomerType() {
        return customerType;
    }
}
