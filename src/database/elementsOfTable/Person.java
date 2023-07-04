package database.elementsOfTable;

import java.time.LocalDate;

public class Person {

     private String surname;
     private String name;
     private LocalDate birthday;

     public Person() {}

    public Person(String surname, String name, LocalDate birthday) {
         this.surname = surname;
         this.name = name;
         this.birthday = birthday;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return (birthday.getDayOfMonth() +  "." + birthday.getMonthValue() + "." + birthday.getYear());
    }

    public LocalDate getBirthdayDate() {
        return birthday;
    }

    public String infoToString() {
         return (surname + " " + name);
    }

    @Override
    public String toString() {
        return (surname + " " + name + " " + getBirthday());
    }
}
