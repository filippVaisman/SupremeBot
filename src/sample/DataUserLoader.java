package sample;

public class DataUserLoader {
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String address;
    private String city;
    private String postCode;
    private String country;
    // Card info

    private String cardType;
    private String creditCardNumber;
    private String monthCardValid;
    private int yearCardValid;
    private int cvv;
    
    
    
    public DataUserLoader(String name, String surname, String email, String telephone, String address, String city, String postCode, String country, String cardType, String creditCardNumber, String monthCardValid, int yearCardValid, int cvv) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.cardType = cardType;
        this.creditCardNumber = creditCardNumber;
        this.monthCardValid = monthCardValid;
        this.yearCardValid = yearCardValid;
        this.cvv = cvv;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCardType() {
        return cardType;
    }

    public String getcreditCardNumber() {
        return creditCardNumber;
    }

    public String getmonthCardValid() {
        return monthCardValid;
    }

    public int getYearCardValid() {
        return yearCardValid;
    }

    public int getCvv() {
        return cvv;
    }
}
