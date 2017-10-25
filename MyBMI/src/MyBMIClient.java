import localhost.axis.MyBMIServer_jws.MyBMIServer;
import localhost.axis.MyBMIServer_jws.MyBMIServerServiceLocator;

import java.rmi.RemoteException;
import java.util.Scanner;

import static java.lang.System.exit;

public class MyBMIClient {

    private static MyBMIServer MBS;
    private static Scanner CONSOLE;

    /**
     * Main Calling Function
     * @param args
     */
    public static void main(String[] args)
    {
        // Init MyBMIServer
        MyBMIServerServiceLocator mbsl = new MyBMIServerServiceLocator();
        MyBMIClient.MBS = mbsl.getMyBMIServer();

        // Init Scanner
        MyBMIClient.CONSOLE = new Scanner(System.in);

        // First check to see if args are present
        if (!args[0].isEmpty()) {
            String func = args[0];
            String height, weight;
            switch (func) {
                case "calcBMI":
                    height = args[1];
                    weight = args[2];
                    MyBMIClient.wsCalcBMI(weight, height);
                    break;
                case "listRanges":
                    MyBMIClient.wsListRanges();
                    break;
                case "listWeights":
                    height = args[1];
                    MyBMIClient.wsListWeights(height);
                    break;
                default:
                    break;
            }
            exit(0);
        }

        // We are here, so there's no args, run the menu
        MyBMIClient.runMenu();
    }

    /**
     * Menu based UI
     */
    private static void runMenu() {
        // Print menu
        System.out.println("Please enter one of the following options:");
        System.out.println("1 - Calculate BMI");
        System.out.println("2 - List BMI Ranges");
        System.out.println("3 - List Normal Weights for a height");
        System.out.println("4 - Exit");

        // Get input and iterate for validation
        int choice = MyBMIClient.CONSOLE.nextInt();
        while (choice < 1 || choice > 4) {
            System.out.println("Please select 1, 2 or 3.");
            choice = MyBMIClient.CONSOLE.nextInt();
        }

        // Parse input selection
        String height, weight;
        switch (choice) {
            case 1:
                System.out.println("Please enter your height:");
                height = MyBMIClient.CONSOLE.next();
                System.out.println("Please enter your weight:");
                weight = MyBMIClient.CONSOLE.next();
                MyBMIClient.wsCalcBMI(weight, height);
                break;
            case 2:
                MyBMIClient.wsListRanges();
                break;
            case 3:
                System.out.println("Please enter your height:");
                height = MyBMIClient.CONSOLE.next();
                MyBMIClient.wsListWeights(height);
                break;
            case 4:
                System.out.println("Bye :)");
                exit(0);
                break;
            default:
                break;
        }

        // Re-run menu
        MyBMIClient.runMenu();
    }

    /**
     * Call calcBMI endpoint
     * @param weight
     * @param height
     */
    private static void wsCalcBMI(String weight, String height) {
        try {
            System.out.println(MBS.calcBMI(weight, height));
        } catch (Exception e) {
            System.out.println("Ooops, there was an issue with your input, please try again.");
        }
    }

    /**
     * Call listRanges endpoint
     */
    private static void wsListRanges() {
        try {
            System.out.println(MBS.listRanges());
        } catch (RemoteException ex) {
            System.out.println("There was a problem with your connection, make sure the remote server is running and try again.");
        }
    }

    /**
     * Call listWeights endpoint
     * @param height
     */
    private static void wsListWeights(String height) {
        try {
            System.out.println(MBS.listWeights(height));
        } catch (RemoteException ex) {
            System.out.println("There was a problem with your connection, make sure the remote server is running and try again.");
        }
    }
}
