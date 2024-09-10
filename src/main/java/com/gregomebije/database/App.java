package com.gregomebije.database;

import java.util.Scanner;
/**
 * A simple Database in Java
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Database db = new Database();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Database CLI. Type 'exit' to quit.");

        while (true) {
            System.out.print("Database> ");
            String command = scanner.nextLine();

            if ("exit".equalsIgnoreCase(command)) {
                break;
            }

            String result = db.runSQL(command);
            System.out.println(result);
        }

        scanner.close();
        System.out.println("Exiting.");
    }
}
