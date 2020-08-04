package net.banatech.sdm;

public class Subscriber {
    private String name;
    private String telNumber;
    private Seat seat;

    /**
     * Subscriber constructor
     * @param name Subscriber's name
     * @param telNumber Subscriber's telephone number
     * @param seat The seat that subscriber reserved
     */
    Subscriber(String name, String telNumber, Seat seat){
        this.name = name;
        this.telNumber = telNumber;
        this.seat = seat;
    }

    /**
     * Get subscriber's name
     * @return subscriber's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get subscriber's telephone number
     * @return subscriber's telephone number
     */
    public String getTelNumber() {
        return this.telNumber;
    }

}
