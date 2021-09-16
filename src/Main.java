import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) {

        Scanner input = new Scanner(System.in);
        String choice;
        String userchoice;
        AdminOperationsImplementation admin;
        Items items;

        do {
            System.out.println("\n\n  -------------------------\n \t 1. Select type of User\n\t1.Admin\n\t2.Customer\n-------------------------");

            System.out.print("Enter your choice: ");
            choice = input.next();
            switch (choice) {
                // to create user type
                case "1":
                    GetItemsOperations getitems = new GetItemsOperations();
                    try {
                        getitems.getItemsDetails();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println(" \n Press M : Modify Existing Items \n Press A: add new items ");
                    userchoice = input.next().toLowerCase();

                    switch (userchoice) {
                        case "m":
                            break;
                        case "a":
                            items = new Items();
                            System.out.println("please enter items details \n");

                            System.out.print("enter item Name: ");
                            items.setItemName(input.next());
                            System.out.print("Enter item quantity: ");
                            items.setQuantity(input.nextInt());
                            System.out.print("Enter items Price: ");
                            items.setUnitPrice(input.nextInt());

                            System.out.println("added items are ");
                            System.out.println(items.getItemName() + " " + items.getQuantity() + " " + items.getUnitPrice());

                            try {
                                admin = new AdminOperationsImplementation();
                                admin.addNewitems(items);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            break;

                        default:
                            System.out.println("Incorrect options was entered");
                    }


                    break;
                case "2":
                    System.out.println("user selected");
                    break;
                default:
                    System.out.println("Enter correct option again \n");
            }
        } while (!choice.equals('0'));
    }
}
