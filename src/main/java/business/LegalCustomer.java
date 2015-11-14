package business;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class LegalCustomer extends Customer{
    private String corporationName;
    private String financialCode;
    private String registerDate;

    public LegalCustomer(String corporationName, String financialCode, String registerDate) {
        this.corporationName = corporationName;
        this.financialCode = financialCode;
        this.registerDate = registerDate;
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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

}
