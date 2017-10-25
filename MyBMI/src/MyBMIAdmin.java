import localhost.axis.MyBMIAdmin_jws.MyBMIAdminServiceLocator;

import java.rmi.RemoteException;
import java.util.Scanner;

import static java.lang.System.exit;

public class MyBMIAdmin {

    private static localhost.axis.MyBMIAdmin_jws.MyBMIAdmin MBA;
    private static Scanner CONSOLE;

    public static void main(String[] args)
    {
        // Init MyBMIServer
        MyBMIAdminServiceLocator mbal = new MyBMIAdminServiceLocator();
        MyBMIAdmin.MBA = mbal.getMyBMIAdmin();

        // Init Scanner
        MyBMIAdmin.CONSOLE = new Scanner(System.in);

        // Run the menu
        MyBMIAdmin.runMenu();
    }

    /**
     * Menu based UI
     */
    private static void runMenu() {
        // Print menu
        System.out.println("Please enter one of the following options:");
        System.out.println("1 - Add a BMI Range");
        System.out.println("2 - Delete a BMI Range");
        System.out.println("3 - Change a BMI Range name");
        System.out.println("4 - List Web Service call count");
        System.out.println("5 - Exit");

        // Get input and iterate for validation
        int choice = MyBMIAdmin.CONSOLE.nextInt();
        while (choice < 1 || choice > 5) {
            System.out.println("Please select 1, 2 or 3.");
            choice = MyBMIAdmin.CONSOLE.nextInt();
        }

        // Parse input selection
        String user, pwd, lower, upper, oldName, name;
        boolean normal;
        switch (choice) {
            case 1:
                System.out.println("Username:");
                user = MyBMIAdmin.CONSOLE.next();
                System.out.println("Password:");
                pwd = MyBMIAdmin.CONSOLE.next();
                System.out.println("Enter the BMI range lower value:");
                lower = MyBMIAdmin.CONSOLE.next();
                System.out.println("Enter the BMI range upper value:");
                upper = MyBMIAdmin.CONSOLE.next();
                System.out.println("Enter the BMI range name:");
                name = MyBMIAdmin.CONSOLE.next();
                System.out.println("Is this the 'Normal' range? 1 - Yes, 0 - No:");
                normal = MyBMIAdmin.CONSOLE.nextBoolean();
                MyBMIAdmin.wsAddRange(user, pwd, lower, upper, name, normal);
                break;
            case 2:
                System.out.println("Username:");
                user = MyBMIAdmin.CONSOLE.next();
                System.out.println("Password:");
                pwd = MyBMIAdmin.CONSOLE.next();
                System.out.println("Enter the name of the BMI range to delete:");
                name = MyBMIAdmin.CONSOLE.next();
                MyBMIAdmin.wsDeleteRange(user, pwd, name);
                break;
            case 3:
                System.out.println("Username:");
                user = MyBMIAdmin.CONSOLE.next();
                System.out.println("Password:");
                pwd = MyBMIAdmin.CONSOLE.next();
                System.out.println("Enter the name of the BMI range to update:");
                oldName = MyBMIAdmin.CONSOLE.next();
                System.out.println("Enter the new name:");
                name = MyBMIAdmin.CONSOLE.next();
                MyBMIAdmin.wsSetName(user, pwd, oldName, name);
                break;
            case 4:
                System.out.println("Username:");
                user = MyBMIAdmin.CONSOLE.next();
                System.out.println("Password:");
                pwd = MyBMIAdmin.CONSOLE.next();
                MyBMIAdmin.wsCallCount(user, pwd);
                break;
            case 5:
                System.out.println("Bye :)");
                exit(0);
                break;
            default:
                break;
        }

        // Re-run menu
        MyBMIAdmin.runMenu();
    }

    private static void wsAddRange(String user, String pwd, String lower, String upper, String name, boolean normal) {
        try {
            MyBMIAdmin.MBA.addRange(user, pwd, lower, upper, name, normal);
        } catch (RemoteException ex) {
            System.out.println("There was a problem with your connection, make sure the remote server is running and try again.");
        }
    }

    private static void wsDeleteRange(String user, String pwd, String name) {
        try {
            MyBMIAdmin.MBA.deleteRange(user, pwd, name);
        } catch (RemoteException ex) {
            System.out.println("There was a problem with your connection, make sure the remote server is running and try again.");
        }
    }

    private static void wsSetName(String user, String pwd, String oldName, String newName) {
        try {
            MyBMIAdmin.MBA.setName(user, pwd, oldName, newName);
        } catch (RemoteException ex) {
            System.out.println("There was a problem with your connection, make sure the remote server is running and try again.");
        }
    }

    private static void wsCallCount(String user, String pwd) {
        try {
            System.out.println(MyBMIAdmin.MBA.callCount(user, pwd));
        } catch (RemoteException ex) {
            System.out.println("There was a problem with your connection, make sure the remote server is running and try again.");
        }
    }
}
