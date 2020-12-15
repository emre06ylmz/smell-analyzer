package com.eylmz.master.sonar.client.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "projects")
public class Project implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4113703570218554703L;

    @Id
    private int id;

    private String name;

    private String description;

    private String language;

    private String long_name;

    private String path;

    private String uuid;

    private String project_uuid;

    private String module_uuid;

    private String root_uuid;

    private String scope;

}
