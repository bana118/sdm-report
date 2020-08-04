package net.banatech.sdm;

import java.util.Date;

public class Seat {
    private int row;
    private int col;
    private Boolean isReserved;
    private Date date;
    private int flight;
    private Subscriber subscriber;

    /**
     * Seat Constructor
     * @param row Row of this seat
     * @param col Column of this seat
     * @param date The date this seat was reserved
     * @param flight flight of this seat. 1 or 2
     */
    Seat(int row, int col, Date date, int flight){
        this.row = row;
        this.col = col;
        this.date = date;
        this.flight = flight;
        this.isReserved = false;
    }

    /**
     * Make a reservation for this seat
     * @param name subscriber's name
     * @param telNumber subscriber's telephone number
     * @return true if the reservation is successful, false otherwise
     */
    public Boolean reserve(String name, String telNumber){
        if(this.isReserved){
            return false;
        }else{
            this.subscriber = new Subscriber(name, telNumber, this);
            this.isReserved = true;
            return true;
        }
    }

    /**
     * Cancel this seat reservation.
     */
    public void cancel() {
        if(this.isReserved && this.subscriber != null){
            this.isReserved = false;
            this.subscriber = null;
        }
    }

    /**
     * Get the reservation status for this seat.
     * @return true if this seat is reserved or false otherwise
     */
    public Boolean getIsReserved() {
        return this.isReserved;
    }

    /**
     * Get the subscriber of this seat
     * @return The subscriber of this seat
     */
    public Subscriber getSubscriber() {
        if(this.isReserved || this.subscriber != null){
            return  this.subscriber;
        }else{
            return null;
        }
    }
}
