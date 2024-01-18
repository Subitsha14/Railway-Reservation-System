package RailwayTicketBooking;

import java.util.Scanner;

public class TicketBooking {
    Scanner s = new Scanner(System.in);
    Services service = new Services();
    static int id =1;
    public void menu(){
        char go = 'y';
        while(go == 'y'){
            System.out.print("\n1.Ticket Booking \n2.Ticket Cancellation \n3.Booked Tickets \n4.Available Tickets \nChoose an option : ");
            int option =s.nextInt();
            switch (option) {
                case 1:
                    booking();
                    break;
                case 2:
                    cancel();
                    break;
                case 3:
                    printBooked();
                    break;
                case 4:
                    printAvailable();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }System.out.println("-----------------------------");
            System.out.print("Do you want to continue ? : ");
            go = s.next().charAt(0);
        }
    }

    private void booking() {
        System.out.println("\nWelcome to booking :)");
        System.out.print("Name : ");
        String name = s.next();
        System.out.print("Age : ");
        int age = s.nextInt();
        System.out.print("Prefered Berth (L,M,U): ");
        String preBerth = s.next();
        Passenger p = new Passenger(id++,name, age, preBerth, "", -1);
        service.bookingTicket(p);
    }

    private void cancel() {
        System.out.println("\nWelcome to ticket cancelling :)");
        System.out.print("Enter the passenger id : ");
        int id = s.nextInt();
        service.cancelTicket(id);
    }

    private void printBooked() {
        System.out.println("\nBooked Tickets ");
        service.printBookedTickets();
    }

    private void printAvailable() {
        System.out.println("\nAvailable Tickets");
        service.printAvailableTickets();
    }   
}