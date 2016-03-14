package com.sixtelmedia.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by branden on 3/10/16 at 13:09.
 */
@Entity
public class Actor {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;


//    @ManyToOne
//    private User user;




    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof Actor)
        {
            isEqual = (this.name.equals(((Actor) object).name));
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}