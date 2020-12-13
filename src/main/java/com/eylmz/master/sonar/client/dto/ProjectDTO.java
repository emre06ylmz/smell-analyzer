package com.eylmz.master.sonar.client.dto;

import javax.persistence.Id;
import java.io.Serializable;

public class ProjectDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4113703570218554703L;

    @Id
    private int id;

    private String name;

    private String description;

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     * @return this contributor
     */
    public ProjectDTO setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * @return this name
     */
    public ProjectDTO setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     * @return this description
     */
    public ProjectDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
