package models;

import java.io.Serializable;

public class CityStation implements Serializable {
    private static final long serialVersionUID = 1L;

    public String id;
    public String name;

    public CityStation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
