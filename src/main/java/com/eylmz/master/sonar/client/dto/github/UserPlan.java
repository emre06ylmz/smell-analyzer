package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "github_user_plans")
public class UserPlan implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 4759542049129654659L;

    private long collaborators;

    private long privateRepos;

    private long space;

    private String name;

    @Id
    private int id;
}
