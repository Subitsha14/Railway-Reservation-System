package RailwayTicketBooking;

import java.util.ArrayList;
import java.util.Arrays;   
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Services {
    static int availableLB = 21;
    static int availableMB = 21;
    static int availableUB = 21;
    static int availableRAC = 18;
    static int availableWL = 10;

    private Integer a[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
    private Integer b[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    private Integer c[] = {1,2,3,4,5,6,7,8,9,10};

    private Queue<Integer> RacQ = new LinkedList<>(); 
    private Queue<Integer> WLQ = new LinkedList<>(); 

    private Map<Integer,Passenger> passengers = new HashMap<>();

    private List<Integer> LBposition = new ArrayList<>(Arrays.asList(a));
    private List<Integer> MBposition = new ArrayList<>(Arrays.asList(a));
    private List<Integer> UBposition = new ArrayList<>(Arrays.asList(a));
    private List<Integer> RACposition = new ArrayList<>(Arrays.asList(b));
    private List<Integer> WLposition = new ArrayList<>(Arrays.asList(c));

    public void bookingTicket(Passenger p){
        if(availableWL == 0){
            System.out.println("No Tickets Available :(");
        }
        //check prefered berth is available 
        else if((availableLB >0 && p.preferedBerth.equals("L")) || 
                (availableMB >0 && p.preferedBerth.equals("M")) || 
                (availableUB >0 && p.preferedBerth.equals("U"))){
            if(p.preferedBerth.equals("L")){
                System.out.println("Prefered Berth Available");
                p.allotedBerth = "Lower Berth";
                p.seatNumber = LBposition.get(0);
                LBposition.remove(0);
                passengers.put(p.id, p);
                availableLB--;
                System.out.println("Lower berth alloted");
            }
            else if(p.preferedBerth.equals("M")){
                System.out.println("Prefered Berth Available");
                p.allotedBerth = "Middle Berth";
                p.seatNumber = MBposition.get(0);
                MBposition.remove(0);
                passengers.put(p.id, p);
                availableMB--;
                System.out.println("Middle berth alloted");
            } 
            else if(p.preferedBerth.equals("U")){
                System.out.println("Prefered Berth Available");
                p.allotedBerth = "Upper Berth";
                p.seatNumber = UBposition.get(0);
                UBposition.remove(0);
                passengers.put(p.id, p);
                availableUB--;
                System.out.println("Upper berth alloted");
            } 
        }
        //if prefered berth is not available then check for available berth
        else if(availableLB>0){
            System.out.println("Prefered Berth Available");
            p.allotedBerth = "Lower Berth";
            p.seatNumber = LBposition.get(0);
            LBposition.remove(0);
            passengers.put(p.id, p);
            availableLB--;
            System.out.println("Lower berth alloted");
        }
        else if(availableMB>0){
            p.allotedBerth = "Middle Berth";
            p.seatNumber = MBposition.get(0);
            MBposition.remove(0);
            passengers.put(p.id, p);
            availableMB--;
            System.out.println("Middle berth alloted");
        }
        else if(availableUB>0){
            p.allotedBerth = "Upper Berth";
            p.seatNumber = UBposition.get(0);
            UBposition.remove(0);
            passengers.put(p.id, p);
            availableUB--;
            System.out.println("Upper berth alloted");
        }
        // if no berth available add to RAC
        else if(availableRAC >0){
            p.allotedBerth = "RAC";
            p.seatNumber = RACposition.get(0);
            RACposition.remove(0);
            passengers.put(p.id, p);
            RacQ.add(p.id);
            availableRAC--;
            System.out.println("RAC alloted");
        }
        //if RAC also not available then add to waiting list 
        else if(availableWL>0){
            p.allotedBerth = "WL";
            p.seatNumber = WLposition.get(0);
            WLposition.remove(0);
            passengers.put(p.id, p);
            WLQ.add(p.id);
            availableWL--;
            System.out.println("Waiting list alloted");
        }
    }

    public void cancelTicket(int id){
        if(passengers.containsKey(id)){
            Passenger pc = passengers.get(id);
            passengers.remove(id);
            //check and remove the passenger if they alloted berth is L or M or U
            if(pc.allotedBerth.equals("Lower Berth")){
                availableLB++;
                LBposition.add(pc.seatNumber);
                System.out.println("Ticket cancelled successfully");
            }
            else if(pc.allotedBerth.equals("Middle Berth")){
                availableMB++;
                MBposition.add(pc.seatNumber);
                System.out.println("Ticket cancelled successfully");
            }
            else if(pc.allotedBerth.equals("Upper Berth")){
                availableUB++;
                UBposition.add(pc.seatNumber);
                System.out.println("Ticket cancelled successfully");
            }

            //when berth ticket get canclled , move RAC passenger to berth
            if(((pc.allotedBerth.equals("Lower Berth"))||
                (pc.allotedBerth.equals("Middle Berth"))||
                (pc.allotedBerth.equals("Upper Berth")))&&
                (RacQ.size()>0)){
                //remove 1st RAC passenger
                Passenger pRac = passengers.get(RacQ.poll());
                RacQ.remove(pRac.id);
                RACposition.add(pRac.seatNumber);
                availableRAC++;
                //move waitlist passenger to RAC
                if(WLQ.size()>0){
                    //remove 1st WL passenger 
                    Passenger pWl = passengers.get(WLQ.poll());
                    WLQ.remove(pWl.id);
                    WLposition.add(pWl.seatNumber);
                    availableWL++;
                    //add to RAC
                    RacQ.add(pWl.id);
                    pWl.seatNumber = RACposition.get(0);
                    RACposition.remove(0);
                    pWl.allotedBerth = "RAC";
                    availableRAC--;
                }
                //add the removed RAC passenger to berth
                bookingTicket(pRac);
            }
            //if the passenger cancel their RAC , remove from RAC and add WL passenger to RAC
            else if(pc.allotedBerth.equals("RAC")){
                availableRAC++;
                RACposition.add(pc.seatNumber);
                RacQ.remove(pc.id);
                if(WLQ.size()>0){
                    //remove 1st WL passenger 
                    Passenger pWl = passengers.get(WLQ.poll());
                    WLQ.remove(pWl.id);
                    WLposition.add(pWl.seatNumber);
                    availableWL++;
                    //add to RAC
                    RacQ.add(pWl.id);
                    pWl.seatNumber = RACposition.get(0);
                    RACposition.remove(0);
                    pWl.allotedBerth = "RAC";
                    availableRAC--;
                }System.out.println("Ticket cancelled successfully");
            }
            //if WL cancelled remove from WL 
            else if(pc.allotedBerth.equals("WL")){
                availableWL++;
                WLposition.add(pc.seatNumber);
                WLQ.remove(pc.id);
                System.out.println("Ticket cancelled successfully");
            }
        }
        else 
            System.out.println("Passenger not found :|");
    }

    public void printBookedTickets(){
        for(Passenger p : passengers.values()){
            System.out.println("Passenger ID : "+p.id);
            System.out.println("Passenger Name: "+p.name);
            System.out.println("Berth alloted : "+p.allotedBerth);
            System.out.println("Position : "+p.seatNumber);
            System.out.println("--------------------------------");
        }
    }
    public void printAvailableTickets(){
        System.out.println("Available Lower Berth : "+availableLB);
        System.out.println("Available Middle Berth: "+availableMB);
        System.out.println("Available Upper Berth : "+availableUB);
        System.out.println("Available RAC : "+ availableRAC);
        System.out.println("Available Waiting list : "+availableWL);
    }
}