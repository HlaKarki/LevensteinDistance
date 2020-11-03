import java.util.*;
import java.io.*;

public class Test {
    public static void main(String argsp[]){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What file/directory would you like to start at?");
        System.out.println("----->");
        String fName;
        fName = keyboard.nextLine();

        File startFile = new File(fName);

        if(startFile.exists()){
            System.out.println("Good to go.");
            displayInfo(startFile);
        }
        else{
            System.out.println("File not found, please try again.");
            System.exit(-1);
        }

    }

    public static void displayInfo(File x){
        if(x.isFile()){
            System.out.println(x.getName());
        }
        else{
            System.out.println(x.getName());
            //File[] fList = x.listFiles();
            for(File innerX: x.listFiles()){

            }

        }
    }

}
