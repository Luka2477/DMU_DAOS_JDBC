package opgave14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App2 {

    private static Scanner scanner;
    private static Statement statement;

    public static void opretVM () {
        while (true) {
            try {
                System.out.println("Du kan altid indtaste ; for at gå tilbage.");
                System.out.print("Venligst indtaste årstal: ");
                String aarstal = scanner.nextLine();
                if (aarstal.equalsIgnoreCase(";")) {
                    break;
                }

                System.out.print("Venligst indtaste bynavn: ");
                String bynavn = scanner.nextLine();
                if (bynavn.equalsIgnoreCase(";")) {
                    break;
                }

                System.out.print("Venligst indtaste land: ");
                String land = scanner.nextLine();
                if (land.equalsIgnoreCase(";")) {
                    break;
                }

                if (aarstal.isEmpty() || bynavn.isEmpty() || land.isEmpty()) {
                    System.out.println("Du skal indtaste noget i alle felter...");
                } else {
                    String query = String.format(
                            "insert into vm values (%s, '%s', '%s');",
                            aarstal, bynavn, land);
                    System.out.printf("SQL-Streng er: '%s'%n", query);
                    statement.execute(query);

                    System.out.println("VM er registreret");
                }

                System.out.println();
            } catch (SQLException throwables) {
                System.out.println();

                if (throwables.getErrorCode() == 2627) {
                    System.out.println("Der findes allerede et VM på det pågældende år.");
                } else {
                    System.out.println("SQL-fejl: " + throwables.getMessage());
                }

                System.out.println();
            }
        }
    }

    public static void opretResultat () {
        while (true) {
            try {
                System.out.println("Du kan altid indtaste ; for at gå tilbage.");
                System.out.print("Venligst indtaste årstal: ");
                String aarstal = scanner.nextLine();
                if (aarstal.equalsIgnoreCase(";")) {
                    break;
                }

                System.out.print("Venligst indtaste rytterinitialer: ");
                String rytterinitialer = scanner.nextLine();
                if (rytterinitialer.equalsIgnoreCase(";")) {
                    break;
                }

                System.out.print("Venligst indtaste placering (NULL hvis ikke gennemført): ");
                String placering = scanner.nextLine();
                if (placering.equalsIgnoreCase(";")) {
                    break;
                }

                if (aarstal.isEmpty() || rytterinitialer.isEmpty() || placering.isEmpty()) {
                    System.out.println("Du skal indtaste noget i alle felter...");
                } else {
                    String query = String.format(
                            "insert into placering values (%s, '%s', %s);",
                            aarstal, rytterinitialer, placering);
                    System.out.printf("SQL-Streng er: '%s'%n", query);
                    statement.execute(query);

                    System.out.println("Resultat er registreret");
                }

                System.out.println();
            } catch (SQLException throwables) {
                System.out.println();

                switch (throwables.getErrorCode()) {
                    case 547: {
                        if (throwables.getMessage().contains("vm_constraint")) {
                            System.out.println("VM for det pågældende år er ikke oprettet.");
                        } else if (throwables.getMessage().contains("rytter_constraint")) {
                            System.out.println("Rytter med de pågældende initialer er ikke oprettet.");
                        } else {
                            System.out.println("Ukendt foreign key.");
                        }
                        break;
                    } case 2627: {
                        System.out.println("Rytter med de pågældende initialer har allerede kørt i det pågældende år.");
                        break;
                    } default: {
                        System.out.println("SQL-fejl: "+throwables.getMessage());
                    }
                }

                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String svr = "localhost\\SQLEXPRESS";
            String db = "cykelplaceringer";
            String us = "sa";
            String pw = "sa123";

            Connection connection = DriverManager.getConnection(String.format(
                    "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;",
                    svr, db, us, pw));
            statement = connection.createStatement();
            scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Du kan altid indtaste ; for at afbryde programmet.");
                System.out.print("Vil du oprette et VM (vm) eller oprette resultat (r): ");
                String response = scanner.nextLine();
                System.out.println();

                if (response.equalsIgnoreCase("vm")) {
                    opretVM();
                } else if (response.equalsIgnoreCase("r")) {
                    opretResultat();
                } else if (response.equalsIgnoreCase(";")) {
                    break;
                } else {
                    System.out.println("Ukendt kommando...");
                }

                System.out.println();
            }

            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
