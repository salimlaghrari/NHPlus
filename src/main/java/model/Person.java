package model;

public abstract class Person {
    private String firstname;
    private String surname;

    public Person(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
