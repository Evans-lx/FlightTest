import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void testPersonCreation() {
        Person person = new ConcretePerson("John", "Doe", 30, "Man");
        assertNotNull(person);
    }

    @Test
    public void testPersonFields() {
        Person person = new ConcretePerson("Jane", "Smith", 25, "Woman");
        assertEquals("Jane", person.getFirstName());
        assertEquals("Smith", person.getSecondName());
        assertEquals(25, person.getAge());
        assertEquals("Woman", person.getGender());
    }

    @Test
    public void testSetAge() {
        Person person = new ConcretePerson("John", "Doe", 30, "Man");
        person.setAge(35);
        assertEquals(35, person.getAge());
    }

    @Test
    public void testSetGender() {
        Person person = new ConcretePerson("Jane", "Smith", 25, "Woman");
        person.setGender("Non-binary | gender diverse");
        assertEquals("Non-binary | gender diverse", person.getGender());
    }

    @Test
    public void testSetFirstName() {
        Person person = new ConcretePerson("John", "Doe", 30, "Man");
        person.setFirstName("Johnny");
        assertEquals("Johnny", person.getFirstName());
    }

    @Test
    public void testSetSecondName() {
        Person person = new ConcretePerson("Jane", "Smith", 25, "Woman");
        person.setSecondName("Doe");
        assertEquals("Doe", person.getSecondName());
    }

    @Test
    public void testToString() {
        Person person = new ConcretePerson("John", "Doe", 30, "Man");
        String expectedString = "Person{firstName='John', secondName='Doe', age=30, gender='Man'}";
        assertEquals(expectedString, person.toString());
    }

    @Test
    public void testInvalidFirstNameStartingWithNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("1John", "Doe", 30, "Man");
        });
        assertEquals("First name must not start with a number or symbol", exception.getMessage());
    }

    @Test
    public void testInvalidSecondNameStartingWithNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("John", "2Doe", 30, "Man");
        });
        assertEquals("Second name must not start with a number or symbol", exception.getMessage());
    }

    @Test
    public void testInvalidGender() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("John", "Doe", 30, "InvalidGender");
        });
        assertEquals("Invalid gender value", exception.getMessage());
    }

    @Test
    public void testInvalidAgeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("John", "Doe", -1, "Man");
        });
        assertEquals("Age must be a positive integer", exception.getMessage());
    }

    @Test
    public void testInvalidAgeZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("John", "Doe", 0, "Man");
        });
        assertEquals("Age must be a positive integer", exception.getMessage());
    }

    @Test
    public void testInvalidFirstNameEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("", "Doe", 30, "Man");
        });
        assertEquals("First name must not be empty", exception.getMessage());
    }

    @Test
    public void testInvalidSecondNameEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConcretePerson("John", "", 30, "Man");
        });
        assertEquals("Second name must not be empty", exception.getMessage());
    }
}
