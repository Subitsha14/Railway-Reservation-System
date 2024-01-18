package RailwayTicketBooking;

public class Passenger {
    int id;
    String name;
    int age;
    String preferedBerth;
    String allotedBerth;
    int seatNumber;

    public Passenger(int id, String name, int age, String preferedBerth, String allotedBerth, int seatNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.preferedBerth = preferedBerth;
        this.allotedBerth = allotedBerth;
        this.seatNumber = seatNumber;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPreferedBerth() {
        return preferedBerth;
    }
    public void setPreferedBerth(String preferedBerth) {
        this.preferedBerth = preferedBerth;
    }
    public String getAllotedBerth() {
        return allotedBerth;
    }
    public void setAllotedBerth(String allotedBerth) {
        this.allotedBerth = allotedBerth;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    
}
