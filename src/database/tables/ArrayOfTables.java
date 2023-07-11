package database.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayOfTables {

    private static int numberOfTables;
    private static List<Table> tables;
    private int numberOfTheCurrentTable;

    public ArrayOfTables() {
        numberOfTables = 0;
        tables = new ArrayList<>();
        numberOfTheCurrentTable = -1;

        addTable("Default table");
        System.out.println("\"Default table\" by number 0 has been created");
        System.out.println();
    }

    public void createNewTable() {
        Scanner scanner = new Scanner(System.in);

        Table table = new Table();
        String title;
        do {
            System.out.print("Enter unique title: ");
            title = scanner.nextLine();
            if (table.checkTitle(title)) {
                break;
            }

            System.out.println("Need to enter unique title");
        } while (true);

        table.setTitle(title);
        addTable(table);
        System.out.println(table + " has been created and become the current table");
        System.out.println();
    }

    public void addTable(Table table) {
        tables.add(table);
        numberOfTables += 1;
        numberOfTheCurrentTable = numberOfTables - 1;
    }

    public void addTable(String title) {
        Table table = new Table(title);
        tables.add(table);
        numberOfTables += 1;
        numberOfTheCurrentTable = numberOfTables - 1;
    }

    public void changeTheTitleOfTheCurrentTable() {
        tables.get(numberOfTheCurrentTable).changeTitle();
        System.out.println("The title has been updated successfully");
        System.out.println();
    }

    public void changeTheCurrentTable() {
        if (checkNumberOfTheTables()) {
            return;
        }

        numberOfTheCurrentTable = enterNumberOfTheTable();
        System.out.println("The number of the current table has been updated successfully");
        System.out.println();
    }

    public void displayTheCurrentTable() {
        tables.get(numberOfTheCurrentTable).display();
        System.out.println();
    }

    public void displayTable() {
        if (checkNumberOfTheTables()) {
            tables.get(0).display();
            return;
        }

        int numberOfTheTable = enterNumberOfTheTable();
        tables.get(numberOfTheTable).display();
        System.out.println();
    }

    public void displayAllTables() {
        tables.forEach(table -> table.display());
        System.out.println();
    }

    public void addPersonInTheCurrentTable() {
        tables.get(numberOfTheCurrentTable).addPerson();
        System.out.println();
    }

    public void sortTheCurrentTable() {
        if (tables.get(numberOfTheCurrentTable).getNumberOfPeople() == 0) {
            System.out.println("You haven't been added people to the current table");
            return;
        }

        if (tables.get(numberOfTheCurrentTable).getNumberOfPeople() == 1) {
            System.out.println("You have been added only one person to the current table");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            System.out.print("Enter a number from 1 to 3 (1 - sort by surname; 2 - sort by name; 3 - sort by birthday): ");
            String numberStr = scanner.nextLine();

            try {
                number = Integer.parseInt(numberStr);
            } catch (Exception exception) {
                System.out.println("Enter a number");
                continue;
            }

            if (number < 1 || number > 3) {
                System.out.println("Enter a number from 1 to 3");
                continue;
            }

            break;
        } while (true);

        tables.get(numberOfTheCurrentTable).sort(number);
        System.out.println("The current table after sort:");
        tables.get(numberOfTheCurrentTable).display();
        System.out.println();
    }

    public void changeInfoAboutPersonInTheCurrentTable() {
        tables.get(numberOfTheCurrentTable).changeInfoAboutPerson();
        System.out.println();
    }

    public void removePersonFromTheCurrentTable() {
        tables.get(numberOfTheCurrentTable).removePerson();
        System.out.println();
    }

    public void removeAllPeopleFromTheCurrentTable() {
        tables.get(numberOfTheCurrentTable).removeAllPeople();
        System.out.println();
    }

    public void deleteTheCurrentTable() {
        if (numberOfTheCurrentTable == 0) {
            System.out.println("The current table is \"Default table\", you can't delete that table");
            System.out.println();
            return;
        }

        tables.remove(numberOfTheCurrentTable);
        numberOfTables--;

        int counter = 0;
        for (Table table : tables) {
            if (counter >= numberOfTheCurrentTable) {
                table.minusNumberOfTheTable();
            }

            counter++;
        }
    }

    public void deleteTable() {
        if (numberOfTheCurrentTable == 0) {
            System.out.println("You have only \"Default table\", you can't delete that table");
            System.out.println();
            return;
        }

        int numberOfTheTable = enterNumberOfTheTable();

        tables.remove(numberOfTheTable);
        numberOfTables -= 1;

        int counter = 0;
        for (Table table : tables) {
            if (counter >= numberOfTheCurrentTable) {
                table.minusNumberOfTheTable();
            }

            counter++;
        }
    }

    public void deleteAllTables() {
        if (checkNumberOfTheTables()) {
            System.out.println();
            return;
        }

        tables.subList(0, tables.size()).clear();
        numberOfTables = 0;
        addTable("Default table");
        System.out.println("All tables deleted and new \"Default table\" by number 0 has been created");
        System.out.println();
    }

    private void displayInfoAboutTheCurrentTable() {
        System.out.println(tables.get(numberOfTheCurrentTable).toString());
    }

    private void displayInfoAboutAllTables() {
        System.out.println("Tables:");
        tables.forEach(table -> System.out.println(table.toString()));
    }

    public boolean checkNumberOfTheTables() {
        if (numberOfTables <= 0) {
            System.out.println("You have only \"Default table\"");
            return true;
        }

        return false;
    }

    private int enterNumberOfTheTable() {
        Scanner scanner = new Scanner(System.in);

        displayInfoAboutAllTables();
        int numberOfTheTable;

        do {
            System.out.print("Enter the number of the table (from 0 to " + (numberOfTables - 1) + "): ");
            String number = scanner.next();

            try {
                numberOfTheTable = Integer.parseInt(number);
            } catch (Exception exception) {
                System.out.println("It's not a number. Enter a number");
                continue;
            }

            if (numberOfTheTable < 0 || numberOfTheTable >= numberOfTables) {
                System.out.println("You should enter number from 0 to " + (numberOfTables - 1));
                continue;
            }

            break;
        } while (true);

        return numberOfTheTable;
    }

    public static int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTheCurrentTable(int numberOfTheCurrentTable) {
        this.numberOfTheCurrentTable = numberOfTheCurrentTable;
    }

    public int getNumberOfTheCurrentTable() {
        return numberOfTheCurrentTable;
    }

    public static List<Table> getTables() {
        return tables;
    }
}
