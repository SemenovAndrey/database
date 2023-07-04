package database.tables;

import database.elementsOfTable.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table implements TableFunctions {

    private String title;
    private int numberOfTheTable;
    private int numberOfPeople;
    private final List<Person> people;

    public Table(String title) {
        this.title = title;
        numberOfTheTable = ArrayOfTables.getNumberOfTables();
        numberOfPeople = 0;
        people = new ArrayList<>();
    }

    public Table() {
        numberOfTheTable = ArrayOfTables.getNumberOfTables();
        numberOfPeople = 0;
        people = new ArrayList<>();
    }


    @Override
    public boolean checkTitle(String title) {
        for (Table table : ArrayOfTables.getTables()) {
            if (table.getTitle().equals(title)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void changeTitle() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter new unique title: ");
            String newTitle = scanner.nextLine();
            if (checkTitle(newTitle)) {
                setTitle(newTitle);
                break;
            }

            System.out.println("New title should be unique");
        } while (true);
    }

    @Override
    public void addPerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        LocalDate date;
        do {
            System.out.print("Enter birthday (dd.mm.yyyy): ");
            String birthday = scanner.next();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            try {
                date = LocalDate.parse(birthday, dtf);
            } catch (DateTimeException exception) {
                System.out.println("Enter the correct date");
                continue;
            }

            LocalDate today = LocalDate.now();
            if (date.getYear() < today.getYear()) {
                break;
            }

            if (date.getYear() == today.getYear() && date.getMonthValue() < today.getMonthValue()) {
                break;
            }

            if (date.getYear() == today.getYear() && date.getMonthValue() == today.getMonthValue() && date.getDayOfMonth() <= today.getDayOfMonth()) {
                break;
            }

            System.out.println("Enter the correct date");
        } while (true);

        Person person = new Person(surname, name, date);
        people.add(person);
        numberOfPeople += 1;

        System.out.println(person.infoToString() + " has been added");
    }

    @Override
    public void changeInfoAboutPerson() {
        if (checkNumberOfPeople()) {
            return;
        }

        Scanner scanner = new Scanner(System.in);

        int numberOfPerson = enterNumberOfThePerson();

        System.out.print("Enter new surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        LocalDate date;
        do {
            System.out.print("Enter new birthday (dd.mm.yyyy): ");
            String birthday = scanner.next();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            try {
                date = LocalDate.parse(birthday, dtf);
                break;
            } catch (DateTimeException exception) {
                System.out.println("Enter correct date");
            }
        } while (true);

        int counter = 0;
        for (Person person : people) {
            if (counter == (numberOfPerson - 1)) {
                person.setSurname(surname);
                person.setName(name);
                person.setBirthday(date);
                System.out.println("Information changed: " + person.toString());
                return;
            } else {
                counter += 1;
            }
        }
    }

    @Override
    public void removePerson() {
        if (checkNumberOfPeople()) {
            return;
        }

        display();
        int numberOfPerson = enterNumberOfThePerson();

        people.remove(numberOfPerson - 1);
        numberOfPeople--;
        System.out.println("The deletion was successful");
    }

    @Override
    public void removeAllPeople() {
        if (checkNumberOfPeople()) {
            return;
        }

        if (numberOfPeople > 0) {
            people.subList(0, people.size()).clear();
        }

        numberOfPeople = 0;
        System.out.println("All people has been removed from the current table");
    }

    @Override
    public void sort(int number) {
        int inner, outer;
        Person temp;
        int h = 1;

        while (h <= numberOfPeople / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (outer = h; outer < numberOfPeople; outer++) {
                temp = people.get(outer);
                inner = outer;

                if (number == 1) {
                    while (inner > h - 1 && people.get(inner - h).getSurname().compareTo(temp.getSurname()) > 0) {
                        people.set(inner, people.get(inner - h));
                        inner -= h;
                    }
                } else if (number == 2) {
                    while (inner > h - 1 && people.get(inner - h).getName().compareTo(temp.getName()) > 0) {
                        people.set(inner, people.get(inner - h));
                        inner -= h;
                    }
                } else if (number == 3) {
                    while (inner > h - 1 && people.get(inner - h).getBirthdayDate().isAfter(temp.getBirthdayDate())) {
                        people.set(inner, people.get(inner - h));
                        inner -= h;
                    }
                }
                people.set(inner, temp);
            }

            h = (h - 1) / 3;
        }
    }

    @Override
    public void display() {
        System.out.println(toString());
        if (numberOfPeople > -1) {
            int counter = 1;
            for (Person person : people) {
                System.out.println(counter + ". " + person.toString());
                counter++;
            }
        }
    }

    @Override
    public String toString() {
        return ("Table №" + numberOfTheTable + " \"" + title + "\"");
    }

    private int enterNumberOfThePerson() {
        Scanner scanner = new Scanner(System.in);

        display();
        int numberOfPerson;

        do {
            System.out.print("Enter a number of person: ");
            String numberOfPersonStr = scanner.nextLine();

            try {
                numberOfPerson = Integer.parseInt(numberOfPersonStr);
            } catch (Exception exception) {
                System.out.println("Enter a correct number");
                continue;
            }

            if (this.numberOfPeople < numberOfPerson && numberOfPerson <= 0) {
                System.out.println("Not a correct number");
                continue;
            }

            break;
        } while (true);

        return numberOfPerson;
    }

    private boolean checkNumberOfPeople() {
        if (this.numberOfPeople < 1) {
            System.out.println("No one people added");
            return true;
        }

        return false;
    }

    protected void minusNumberOfTheTable() {
        numberOfTheTable -= 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getNumberOfTheTable() {
        return numberOfTheTable;
    }
}
