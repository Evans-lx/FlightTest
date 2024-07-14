public abstract class Person {
    private String firstName;
    private String secondName;
    private int age;
    private String gender;

    public Person() {}

    public Person(String firstName, String secondName, int age, String gender) {
        setFirstName(firstName);
        setSecondName(secondName);
        setAge(age);
        setGender(gender);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be a positive integer");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!gender.equals("Woman") && !gender.equals("Man") && !gender.equals("Non-binary | gender diverse") && !gender.equals("Prefer not to say") && !gender.equals("Other")) {
            throw new IllegalArgumentException("Invalid gender value");
        }
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name must not be empty");
        }
        if (firstName.matches("^[0-9].*") || firstName.matches("^[^a-zA-Z].*")) {
            throw new IllegalArgumentException("First name must not start with a number or symbol");
        }
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        if (secondName == null || secondName.isEmpty()) {
            throw new IllegalArgumentException("Second name must not be empty");
        }
        if (secondName.matches("^[0-9].*") || secondName.matches("^[^a-zA-Z].*")) {
            throw new IllegalArgumentException("Second name must not start with a number or symbol");
        }
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}