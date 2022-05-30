package individualprojectpartb;

import individualprojectpartb.database.UserDao;
import java.util.Scanner;


public class IndividualProjectPartB {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : SEE THE LISTS");
            System.out.println("2 : CREATE OR CHANGE DATA");
            System.out.println("3 : EXIT PROGRAM");
            

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    System.out.println("\n");
                    UserDao.printingLists();
                    break;
                case ("2"):
                    System.out.println("\n");
                    UserDao.changingLists();
                    break;
                case ("3"):
                    exitProgram = true;
                    break;
            }
        }
         

    }

    
    
    
}
