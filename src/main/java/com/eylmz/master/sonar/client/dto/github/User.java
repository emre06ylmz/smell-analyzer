package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "github_users")
public class User implements Serializable {

    /**
     * TYPE_USER
     */
    public static final String TYPE_USER = "User"; //$NON-NLS-1$
    /**
     * TYPE_ORG
     */
    public static final String TYPE_ORG = "Organization"; //$NON-NLS-1$
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1211802439119529774L;
    private Date createdAt;

    private int followers;

    private int following;

    @Id
    private int id;

    private int ownedPrivateRepos;

    private int privateGists;

    private int publicGists;

    private int publicRepos;

    private int totalPrivateRepos;

    private String company;

    private String htmlUrl;

    private String location;

    private String login;

    private String name;

    private String type;

    private String url;

    private String uuid;

}
