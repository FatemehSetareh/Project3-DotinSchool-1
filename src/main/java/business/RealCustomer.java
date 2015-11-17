package business;


public class RealCustomer extends Customer {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalCode;
    private String birthDate;
    private Integer customerNumber;

    public RealCustomer(String firstName, String lastName, String fatherName, String nationalCode, String birthDate, Integer customerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.nationalCode = nationalCode;
        this.birthDate = birthDate;
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }
}
