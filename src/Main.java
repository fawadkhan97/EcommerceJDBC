import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) throws Exception {

        Scanner input = new Scanner(System.in);
        String choice;
        String userchoice = "";
        AdminOperationsImplementation admin;
        CustomerOperationsImplementation customer;
        GetItemsOperations getitems;
        DbOperations dbOperations;
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

                    dbOperations = new DbOperations();
                    // select item base on item id
                    System.out.print("\n Select items from above list (itemid): ");
                    do {
                        do {
                            int itemid = input.nextInt();//get order product quantity
                            int itemPrice = dbOperations.getItemPrice(itemid);
                            if (itemPrice == -1) {
                                getitems.getItemsDetails();
                                System.out.println("\nincorrect item id.....  :-)");
                                System.out.print("\n please enter correct id from above list  again : ");
                                break;
                            } else {
                                System.out.print("\n Enter quantity: ");
                                int quantity = input.nextInt();
                                try {
                                    customer.createOrder_items(itemid, quantity, itemPrice);
                                    System.out.println("do you want to Purchase any more items.  Yes or No ?");
                                    do {
                                        userchoice = input.next().toLowerCase();
                                        switch (userchoice) {
                                            case "yes":
                                                getitems.getItemsDetails();
                                                System.out.print("\n Select items from above list (itemid): ");
                                                userchoice="!";
                                                break;
                                            case "no":
                                                System.out.println("Are you telenor Customer . True or False");
                                                Boolean telenorCustomer=input.nextBoolean();
                                                int orderid = dbOperations.getOrderId();
                                                     customer.printInvoice(orderid,telenorCustomer);
                                                     System.exit(0);
                                                break;
                                            default:
                                                System.out.println("incorrect options was entered .  Do you want to purchase items. Yes or No");
                                        }
                                    } while (userchoice != "!");

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        } while (true);
                        System.out.print("\n Select items from above list (itemid): ");
                    } while (userchoice != "0");
                    break;
                default:
                    System.out.println("Enter correct option again \n");
            }
        } while (!choice.equals('0'));
    }
}
