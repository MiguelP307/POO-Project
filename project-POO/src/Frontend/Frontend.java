package frontend;

import java.util.*;

public class Frontend {

    public Frontend() {}

    public void start() {
        Scanner scanner = new Scanner(System.in); 

        this.printHelp();
  
        while(true) {
            System.out.print("> ");
            String line = scanner.nextLine();

            if(line.equals("0")) {
                 break;
            }
            else if(line.equals("3")) {
                this.printHelp();
                
            }
            else if(line.equals("1")) {
                createUser(scanner);
            }
            else if(line.equals("2")) {
                createActivity(scanner);
            }
            else {
                System.out.println("Unknown command");
            }
        }

        scanner.close(); 
    }

    private void createUser(Scanner scanner) {
        System.out.print("Name: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("Age: ");
        String age = scanner.nextLine();

        System.out.print("Height: ");
        String height = scanner.nextLine();

        System.out.print("Weight: ");
        String weight = scanner.nextLine();

        System.out.print("Heart rate: ");
        String heartRate = scanner.nextLine();
    }

    private void createActivity(Scanner scanner) {
        System.out.print("Name: ");
        String activityName = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Difficulty: ");
        String difficulty = scanner.nextLine();
    }

    private void printHelp() {
        System.out.println("1. Create user");
        System.out.println("2. Create activity");
        System.out.println("3. Help");
        System.out.println("0. Exit");
    }
}
