package database.main;

import database.tables.ArrayOfTables;

import java.util.Scanner;

public class App {

    // !!
    // сделать вывод в файл
    // сделать чтение из файла

    // !
    // сделать вывод в excel файл

    private final ArrayOfTables arrayOfTables = new ArrayOfTables();
    private int number;

    public App() {
        showFunctions();

        enterTheNumber();
    }

    public static void showFunctions() {
        System.out.println("Possible functions:");
        System.out.println("1. Create new table");
        System.out.println("2. Change title in the current table");
        System.out.println("3. Change the current table");
        System.out.println("4. Show the current table");
        System.out.println("5. Show the table by number");
        System.out.println("6. Show all tables");
        System.out.println("7. Add person in current table");
        System.out.println("8. Sort the current table");
        System.out.println("9. Change information about person in the current table");
        System.out.println("10. Remove person from the current table");
        System.out.println("11. Remove all people from the current table");
        System.out.println("12. Delete the current table");
        System.out.println("13. Delete the table by number");
        System.out.println("14. Delete all tables");
        System.out.println("!help - show possible functions");
        System.out.println("!stop - finish work");
        System.out.println();
    }

    private void enterTheNumber() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter the number of the necessary function: ");
            String function = scanner.nextLine();

            if (function.equals("!help")) {
                showFunctions();
                continue;
            }

            if (function.equals("!stop")) {
                System.out.println("Work finished");
                break;
            }

            try {
                number = Integer.parseInt(function);
            } catch (Exception exception) {
                System.out.println("Enter a number");
                continue;
            }

            checkTheNumber();
        } while (true);
    }

    private void checkTheNumber() {
        if (number < 1 || number > 14) {
            System.out.println("Enter possible number");
            return;
        }

        if (number == 1) {
            createNewTable();
            return;
        }

        if (number == 2) {
            changeTitle();
            return;
        }

        if (number == 3) {
            changeTheCurrentTable();
            return;
        }

        if (number == 4) {
            showTheCurrentTable();
            return;
        }

        if (number == 5) {
            showTheTable();
            return;
        }

        if (number == 6) {
            showAllTables();
            return;
        }

        if (number == 7) {
            addPersonInTheCurrentTable();
            return;
        }

        if (number == 8) {
            sort();
            return;
        }

        if (number == 9) {
            changeInfoAboutPersonInTheCurrentTable();
            return;
        }

        if (number == 10) {
            removePersonFromTheCurrentTable();
            return;
        }

        if (number == 11) {
            removeAllPeopleFromTheCurrentTable();
            return;
        }

        if (number == 12) {
            deleteTheCurrentTable();
            return;
        }

        if (number == 13) {
            deleteTheTable();
            return;
        }

        deleteAllTables();
    }

    private void createNewTable() {
        arrayOfTables.createNewTable();
    }

    private void changeTitle() {
        arrayOfTables.changeTheTitleOfTheCurrentTable();
    }

    private void changeTheCurrentTable() {
        arrayOfTables.changeTheCurrentTable();
    }

    private void showTheCurrentTable() {
        arrayOfTables.displayTheCurrentTable();
    }

    private void showTheTable() {
        arrayOfTables.displayTable();
    }

    private void showAllTables() {
        arrayOfTables.displayAllTables();
    }

    private void addPersonInTheCurrentTable() {
        arrayOfTables.addPersonInTheCurrentTable();
    }

    private void sort() {
        arrayOfTables.sortTheCurrentTable();
    }

    private void changeInfoAboutPersonInTheCurrentTable() {
        arrayOfTables.changeInfoAboutPersonInTheCurrentTable();
    }

    private void removePersonFromTheCurrentTable() {
        arrayOfTables.removePersonFromTheCurrentTable();
    }

    private void removeAllPeopleFromTheCurrentTable() {
        arrayOfTables.removeAllPeopleFromTheCurrentTable();
    }

    private void deleteTheCurrentTable() {
        arrayOfTables.deleteTheCurrentTable();
    }

    private void deleteTheTable() {
        arrayOfTables.deleteTable();
    }

    private void deleteAllTables() {
        arrayOfTables.deleteAllTables();
    }
}
