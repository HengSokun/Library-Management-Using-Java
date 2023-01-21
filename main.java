import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nocrala.tools.texttablefmt.BorderStyle;

import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class Main {

    //---------------------Color------------------------//
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    //-------------------------------------------------//

    static int column = 5;

    static String bookID = "ID";
    static String bookTitle = "TITLE";
    static String bookAuthor = "AUTHOR";
    static String publishYear = "PUBLISH DATE";
    static String status = "STATUS";

    static int count = 4;
    static String libraryName, libraryLocation;
    static Scanner input = new Scanner(System.in);
    static Books[] book = new Books[99];

    // validation method check if not number
    public static boolean validNumber(String checkN) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher match = pattern.matcher(checkN);
        return match.matches();
    }

    // Add books method
    static void addBooks(){
        book[count] = new Books();

        System.out.println("========= ADD BOOK INFO =========");
        System.out.print("=> Enter Book's Name : ");
        book[count].setBookTitle(input.next());

        System.out.print("=> Enter Book Author Name: ");
        input.nextLine();
        book[count].setAuthorName(input.next());

        System.out.print("=> Enter Author Year Active: ");
        input.nextLine();
        book[count].setAuthorActive(input.next());

        System.out.print("=> Enter Published Year : ");
        input.nextLine();
        book[count].setPublishDate(input.next());

        book[count].setBookID(count+1);
        book[count].setBookStatus("Available");
        count++;


        systemMenu();
    }

    // Show all books method
    static void showAllBooks(){


        Table table = new Table(column, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);

        System.out.println(ANSI_CYAN + "------------------------- ALL BOOK INFO --------------------------");
        table.addCell(bookID);
        table.addCell(bookTitle);
        table.addCell(bookAuthor);
        table.addCell(publishYear);
        table.addCell(status);

        for (Books books : book) {
            if (books != null) {
                table.addCell(String.valueOf(books.getBookID()));
                table.addCell(books.getBookTitle());
                table.addCell(books.getAuthorName() + " " + books.getAuthorActive());
                table.addCell(books.getPublishDate());
                table.addCell(books.getBookStatus());
            }
        }

        System.out.println(table.render());
        System.out.println(ANSI_PURPLE + "1: Next, 2: Back, 3: Return To Menu");
        System.out.print("Options: ");
        String tableInput = input.next();

        systemMenu();
    }

    // Show only available books method
    static void showAvailableBooks(){

        Table table = new Table(column, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        System.out.println(ANSI_CYAN + "------------------------- ALL BOOK INFO --------------------------");

        String bookID = "ID";
        String bookTitle = "TITLE";
        String bookAuthor = "AUTHOR";
        String publishYear = "PUBLISH DATE";
        String status = "STATUS";

        table.addCell(bookID);
        table.addCell(bookTitle);
        table.addCell(bookAuthor);
        table.addCell(publishYear);
        table.addCell(status);

        for (Books books : book) {
            if (books != null) {
                if (Objects.equals(books.getBookStatus(), "Available")) {

                    table.addCell(String.valueOf(books.getBookID()));
                    table.addCell(books.getBookTitle());
                    table.addCell(books.getAuthorName() + " (" + books.getAuthorActive() + ")");
                    table.addCell(books.getPublishDate());
                    table.addCell(books.getBookStatus());
                }
            }
        }
        System.out.println(table.render());

        System.out.println(ANSI_PURPLE + "1: Next, 2: Back, 3: Return To Menu;");
        System.out.print("Options: ");
        String tableInput = input.next();

        systemMenu();
    }

    // Borrow book method
    static void borrowBooks(){
        System.out.println(ANSI_CYAN + "----------------------- BORROW BOOK INFO -------------------------");
        System.out.print(ANSI_PURPLE + "=> Enter Book ID to Borrow : ");
        String string_borrowID = input.next();

        if (validNumber(string_borrowID)){
            int borrowID = Integer.parseInt(string_borrowID);
            for (Books books : book) {
                if (books != null) {
                    if (borrowID == books.getBookID()){
                        books.setBookStatus("Unavailable");
                        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                        System.out.printf("%sBook ID: %s%s%n", ANSI_PURPLE, ANSI_YELLOW, borrowID);
                        System.out.printf("%sStatus:%s Borrowed successfully!%n", ANSI_PURPLE, ANSI_YELLOW);
                        System.out.println(ANSI_CYAN  + "------------------------------------------------------------------");
                        System.out.println(ANSI_PURPLE + "Name:" + ANSI_GREEN + " " + books.getBookTitle());
                        System.out.println(ANSI_PURPLE + "Published Date:" + ANSI_GREEN + " " + books.getPublishDate());
                        System.out.println(ANSI_PURPLE + "Author:" + ANSI_GREEN + " " + books.getAuthorName() + " (%s)".formatted(books.getAuthorActive()));
                        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                        break;
                    }
                }
                else {
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.printf("%sBook ID: %s%s%n", ANSI_PURPLE, ANSI_YELLOW, borrowID);
                    System.out.printf("%sStatus:%s Book doesn't exist!%n", ANSI_PURPLE, ANSI_RED);
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    break;
                }
            }
        } else {
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.printf("%s=> Wrong input, %sPlease try again!%n", ANSI_RED, ANSI_BLUE);
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        }

        systemMenu();
    }

    static void returnBooks(){
        System.out.println(ANSI_CYAN + "----------------------- RETURN BOOK INFO -------------------------");
        System.out.print(ANSI_PURPLE + "=> Enter Book ID to Return : ");
        String string_returnID = input.next();

        if (validNumber(string_returnID)){
            int returnID = Integer.parseInt(string_returnID);
            for (Books books : book) {
                if (books != null) {
                    if (returnID == books.getBookID()){
                        books.setBookStatus("Available");
                        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                        System.out.printf("%sBook ID: %s%s%n", ANSI_PURPLE, ANSI_YELLOW, returnID);
                        System.out.printf("%sStatus:%s Returned successfully!%n", ANSI_PURPLE, ANSI_YELLOW);
                        System.out.printf("%s------------------------------------------------------------------%n", ANSI_CYAN);
                        System.out.println(ANSI_PURPLE + "Book Name:" + ANSI_GREEN + " " + books.getBookTitle());
                        System.out.println(ANSI_PURPLE + "Published Date:" + ANSI_GREEN + " " + books.getPublishDate());
                        System.out.println(ANSI_PURPLE + "Author:" + ANSI_GREEN + " " + books.getAuthorName() + " (%s)".formatted(books.getAuthorActive()));
                        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                        break;
                    }
                }
                else {
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.printf("%sBook ID: %s%s%n", ANSI_PURPLE, ANSI_YELLOW, returnID);
                    System.out.printf("%sStatus:%s failed to return!%n", ANSI_PURPLE, ANSI_RED);
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    break;
                }
            }
        } else {
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.printf("%s=> Wrong input, %sPlease try again!%n", ANSI_RED, ANSI_BLUE);
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        }

        systemMenu();
    }

    // System menu
    static void systemMenu(){

        String menuBanner = """
                      %s        ==== %s%s%s, %s%s%s ==== %s
        | 1- Add Book                                                    |
        | 2- Show All Books                                              |
        | 3- Show Available Books                                        |
        | 4- Borrow Book                                                 |
        | 5- Return Book                                                 |
        | 6- Exit %s                                                       |
        ==================================================================""".formatted(ANSI_CYAN, ANSI_YELLOW, libraryName, ANSI_CYAN, ANSI_YELLOW, libraryLocation, ANSI_CYAN, ANSI_PURPLE, ANSI_CYAN);

        System.out.println(menuBanner);
        System.out.print(ANSI_PURPLE + "=> Choose option(1-6) : ");
        String choice = input.next();

        if (validNumber(choice)) {
            switch (choice) {
                case "1" -> addBooks();
                case "2" -> showAllBooks();
                case "3" -> showAvailableBooks();
                case "4" -> borrowBooks();
                case "5" -> returnBooks();
                case "6" -> {
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.out.printf("%s=> Good bye, %sSee you again!%n", ANSI_RED, ANSI_BLUE);
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.exit(0);
                }
                default -> {
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.out.printf("%sPlease choose to correct option!%n", ANSI_RED);
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                }
            }
        } else {
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.printf("%s=> Wrong input, %sPlease try again!%n", ANSI_RED, ANSI_BLUE);
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        }

        systemMenu();
    }

    // set up methods
    static void setUp(){

        String banner = ANSI_GREEN + """
        ------------------------------------------------------------------
                            █▀ █▀▀ ▀█▀ ▄▄ █░█ █▀█
                            ▄█ ██▄ ░█░ ░░ █▄█ █▀▀
        ------------------------------------------------------------------""";
        System.out.println(banner);
        System.out.println( ANSI_CYAN  + "----------------------- Set up library ---------------------------");
        System.out.print(ANSI_PURPLE + "=> Enter Library's Name: ");
        libraryName = input.next();

        System.out.print("=> Enter Library's Address: ");
        libraryLocation = input.next();

        java.util.Date date = new java.util.Date();

        System.out.println( ANSI_CYAN  + "---------------- Library created successfully --------------------");
        System.out.println(ANSI_BLUE + "=> Name: %s%s".formatted(ANSI_YELLOW, libraryName));
        System.out.println(ANSI_BLUE + "=> Location: %s%s".formatted(ANSI_YELLOW, libraryLocation));
        System.out.println(ANSI_BLUE + "=> Date: %s%s".formatted(ANSI_YELLOW, date));
        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");

        systemMenu();

    }

    // main functions
    public static void main(String[] args) {

        book[0] = new Books(1, "Dooms Day", "1995", "Ubisoft", "1980-2010","Available");
        book[1] = new Books(2, "Cthulhu Mythos", "1969", "Howard Phillips", "1890-1937","Available");
        book[2] = new Books(3, "The Manifold", "1300", "Downstreamer", "1950-2005","Unavailable");
        book[3] = new Books(4, "Puss in Boot", "2010", "Dreamwork", "1970-2020","Available");

        setUp();
    }

}
