package com.hmy.staybooking.model;

import javax.persistence.*;
import java.io.Serializable;

// public setters are not needed because all the stay reserved date information is read from the database,
// so we don't need to update them in the Java code
@Entity
@Table(name = "stay_reserved_date")
public class StayReservedDate implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StayReservedDateKey id;

    @MapsId("stay_id")
    @ManyToOne
    private Stay stay;

    public StayReservedDate() {}

    public StayReservedDate(StayReservedDateKey id, Stay stay) {
        this.id = id;
        this.stay = stay;
    }

    public StayReservedDateKey getId() {
        return id;
    }

    public Stay getStay() {
        return stay;
    }

}

