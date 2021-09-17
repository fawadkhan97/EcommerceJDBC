import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) {

        Scanner input = new Scanner(System.in);
        String choice;
        String userchoice = "";
        AdminOperationsImplementation admin;
        CustomerOperationsImplementation customer;
        GetItemsOperations getitems;
        Items items;

        do {
            System.out.println("\n\n  -------------------------\n \t 1. Select type of User\n\t1.Admin\n\t2.Customer\n-------------------------");

            System.out.print("Enter your choice: ");
            choice = input.next();
            switch (choice) {
                //  get items from database
                // admin operations
                case "1":
                    getitems = new GetItemsOperations();
                    try {
                        getitems.getItemsDetails();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    do {
                        System.out.println("\n Press A: add new items  \n Press M : Modify Existing Items ");
                        userchoice = input.next().toLowerCase();
                        switch (userchoice) {
                            case "a":
                                items = new Items();
                                System.out.println("please enter items details \n");

                                System.out.print("enter item Name: ");
                                items.setItemName(input.next());
                                System.out.print("Enter item quantity: ");
                                items.setQuantity(input.nextInt());
                                System.out.print("Enter items Price: ");
                                items.setItemprice(input.nextInt());

                                System.out.println("added items are ");
                                System.out.println(items.getItemName() + " " + items.getQuantity() + " " + items.getItemprice());

                                try {
                                    admin = new AdminOperationsImplementation();
                                    admin.addNewitems(items);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case "m":
                                items = new Items();
                                System.out.println("please enter itemsID you want to change");
                                int newId = input.nextInt();
                                System.out.print("enter item Name: ");
                                items.setItemName(input.next());
                                System.out.print("Enter item quantity: ");
                                items.setQuantity(input.nextInt());
                                System.out.print("Enter items Price: ");
                                items.setItemprice(input.nextInt());
                                try {
                                    admin = new AdminOperationsImplementation();
                                    admin.modifyItems(newId, items);
                                    System.out.println("added items successfully ");
                                    System.out.println(items.getItemName() + " " + items.getQuantity() + " " + items.getItemprice());
                                    do {
                                        System.out.print("\n do you want to continue ?  Y or N :  ");
                                        userchoice = input.next().toLowerCase();
                                        switch (userchoice) {
                                            case "y":
                                                userchoice = ";";
                                                break;
                                            case "n":
                                                System.out.println("-------------good bye----------");
                                                System.exit(0);
                                            default:
                                                System.out.println("Incorrect,Enter correct option again ");
                                        }
                                    } while (userchoice != ";");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }


                                break;
                            default:
                                System.out.println("Incorrect options was entered ");

                        }
                    } while (userchoice != "0");
                    break;
                // child operations
                case "2":
                    getitems = new GetItemsOperations();
                    try {
                        getitems.getItemsDetails();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    customer = new CustomerOperationsImplementation();
                    // create order , same order id will be use for all products user purchase
                    try {
                        customer.createOrder();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    do {
                        System.out.print("\nSelect items from above list (itemid): ");
                        int itemid = input.nextInt();

                        System.out.print("\n Enter quantity: ");
                        int quantity = input.nextInt();
                        try {
                            customer.createOrder_items(itemid, quantity);


                            System.out.println("do you want to Purchase any more items ?");
                            userchoice = input.next().toLowerCase();
                            switch (userchoice) {
                                case "y":
                                    System.out.println("Y");
                                    break;
                                case "n":
                                    customer.printOrderDetails();
                                    break;
                                default:
                                    System.out.println("incorrect options was entered");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } while (userchoice != "0");
                    break;
                default:
                    System.out.println("Enter correct option again \n");
            }
        } while (!choice.equals('0'));
    }
}
