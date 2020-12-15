package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "github_contributors")
public class Contributor implements Serializable {

    /**
     * Anonymous contributor type value
     */
    public static final String TYPE_ANONYMOUS = "Anonymous"; //$NON-NLS-1$
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8434028880839230626L;
    private int contributions;

    @Id
    private int id;

    private String avatarUrl;

    private String login;

    private String name;

    private String type;

    private String url;

    private String uuid;

}
