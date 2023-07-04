package database.tables;

public interface TableFunctions {

    public boolean checkTitle(String title);

    public void changeTitle();

    public void addPerson();

    public void changeInfoAboutPerson();

    public void removePerson();

    public void removeAllPeople();

    public void sort(int number);

    public void display();
}
