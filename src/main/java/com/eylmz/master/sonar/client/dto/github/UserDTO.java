package com.eylmz.master.sonar.client.dto.github;

import org.eclipse.egit.github.core.util.DateUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class UserDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1211802439119529774L;

    /**
     * TYPE_USER
     */
    public static final String TYPE_USER = "User"; //$NON-NLS-1$

    /**
     * TYPE_ORG
     */
    public static final String TYPE_ORG = "Organization"; //$NON-NLS-1$

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

    /**
     * @return createdAt
     */
    public Date getCreatedAt() {
        return DateUtils.clone(createdAt);
    }

    /**
     * @param createdAt
     * @return this user
     */
    public UserDTO setCreatedAt(Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }

    /**
     * @return followers
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * @param followers
     * @return this user
     */
    public UserDTO setFollowers(int followers) {
        this.followers = followers;
        return this;
    }

    /**
     * @return following
     */
    public int getFollowing() {
        return following;
    }

    /**
     * @param following
     * @return this user
     */
    public UserDTO setFollowing(int following) {
        this.following = following;
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
     * @return this user
     */
    public UserDTO setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return ownedPrivateRepos
     */
    public int getOwnedPrivateRepos() {
        return ownedPrivateRepos;
    }

    /**
     * @param ownedPrivateRepos
     * @return this user
     */
    public UserDTO setOwnedPrivateRepos(int ownedPrivateRepos) {
        this.ownedPrivateRepos = ownedPrivateRepos;
        return this;
    }

    /**
     * @return privateGists
     */
    public int getPrivateGists() {
        return privateGists;
    }

    /**
     * @param privateGists
     * @return this user
     */
    public UserDTO setPrivateGists(int privateGists) {
        this.privateGists = privateGists;
        return this;
    }

    /**
     * @return publicGists
     */
    public int getPublicGists() {
        return publicGists;
    }

    /**
     * @param publicGists
     * @return this user
     */
    public UserDTO setPublicGists(int publicGists) {
        this.publicGists = publicGists;
        return this;
    }

    /**
     * @return publicRepos
     */
    public int getPublicRepos() {
        return publicRepos;
    }

    /**
     * @param publicRepos
     * @return this user
     */
    public UserDTO setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
        return this;
    }

    /**
     * @return totalPrivateRepos
     */
    public int getTotalPrivateRepos() {
        return totalPrivateRepos;
    }

    /**
     * @param totalPrivateRepos
     * @return this user
     */
    public UserDTO setTotalPrivateRepos(int totalPrivateRepos) {
        this.totalPrivateRepos = totalPrivateRepos;
        return this;
    }

    /**
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     * @return this user
     */
    public UserDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    /**
     * @return htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * @param htmlUrl
     * @return this user
     */
    public UserDTO setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     * @return this user
     */
    public UserDTO setLocation(String location) {
        this.location = location;
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
     * @return this user
     */
    public UserDTO setLogin(String login) {
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
     * @return this user
     */
    public UserDTO setName(String name) {
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
     * @return this user
     */
    public UserDTO setType(String type) {
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
     * @return this user
     */
    public UserDTO setUrl(String url) {
        this.url = url;
        return this;
    }

}
