package by.epam.bookstore.model.entity;

import java.io.Serializable;

public class EntityFlag implements Serializable, Cloneable {
    private int id;

    public EntityFlag() {
    }

    public EntityFlag(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
