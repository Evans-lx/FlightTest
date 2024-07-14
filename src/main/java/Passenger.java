//this  whole code is written by dong zhenpeng
public class Passenger extends Person {
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private int securityCode;
    private String passport;

    public Passenger() {}

    public Passenger(String firstName, String secondName, int age, String gender, String email, String phoneNumber, String passport, String cardNumber, int securityCode) {
        super(firstName, secondName, age, gender);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassport(passport);
        setCardNumber(cardNumber);
        setSecurityCode(securityCode);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getSecondName() {
        return super.getSecondName();
    }

    public void setSecondName(String secondName) {
        super.setSecondName(secondName);
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getPassport() {
        return passport;
    }

    public void setGender(String gender) {
        super.setGender(gender);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setSecurityCode(int securityCode) {
        if (securityCode < 0) {
            throw new IllegalArgumentException("Invalid security code");
        }
        this.securityCode = securityCode;
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    public void setPassport(String passport) {
        if (passport == null || passport.length() > 9) {
            throw new IllegalArgumentException("Invalid passport number");
        }
        this.passport = passport;
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^0[45][0-9]{8}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String toString() {
        return "Passenger{" + " Fullname= " + super.getFirstName() + " " + super.getSecondName() +
                " ,email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
