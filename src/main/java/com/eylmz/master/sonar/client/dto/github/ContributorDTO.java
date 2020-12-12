package com.eylmz.master.sonar.client.dto.github;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ContributorDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -8434028880839230626L;

    /**
     * Anonymous contributor type value
     */
    public static final String TYPE_ANONYMOUS = "Anonymous"; //$NON-NLS-1$

    private int contributions;

    @Id
    private int id;

    private String avatarUrl;

    private String login;

    private String name;

    private String type;

    private String url;

    /**
     * @return contributions
     */
    public int getContributions() {
        return contributions;
    }

    /**
     * @param contributions
     * @return this contributor
     */
    public ContributorDTO setContributions(int contributions) {
        this.contributions = contributions;
        return this;
    }

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
    public ContributorDTO setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl
     * @return this contributor
     */
    public ContributorDTO setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    /**
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     * @return this contributor
     */
    public ContributorDTO setLogin(String login) {
        this.login = login;
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
     * @return this contributor
     */
    public ContributorDTO setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     * @return this contributor
     */
    public ContributorDTO setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     * @return this contributor
     */
    public ContributorDTO setUrl(String url) {
        this.url = url;
        return this;
    }

}
