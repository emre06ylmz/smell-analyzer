package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "github_issues")
public class Issue implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6358575015023539051L;

    @Id
    private long id;

    private Date closedAt;

    private Date createdAt;

    private Date updatedAt;

    private int comments;

    private int number;

    private String body;

    private String bodyHtml;

    private String bodyText;

    private String htmlUrl;

    private String state;

    private String title;

    private String url;

    private String uuid;

}
