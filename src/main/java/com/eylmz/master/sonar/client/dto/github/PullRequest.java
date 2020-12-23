package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "github_pull_requests")
public class PullRequest implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7858604768525096763L;

    private boolean mergeable;

    private boolean merged;

    private Date closedAt;

    private Date mergedAt;

    private Date updatedAt;

    private Date createdAt;

    @Id
    private long id;

    private int additions;

    private int changedFiles;

    private int comments;

    private int commits;

    private int deletions;

    private int number;

    @Lob
    @Column
    private String body;

    @Lob
    @Column
    private String bodyHtml;

    private String bodyText;

    private String diffUrl;

    private String htmlUrl;

    private String issueUrl;

    private String patchUrl;

    private String state;

    private String title;

    private String url;

    private String uuid;

}
