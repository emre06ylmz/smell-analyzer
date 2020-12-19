package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "github_commit_files")
public class CommitFile  implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4607637532042868579L;

    @Id
    private int id;

    private int additions;

    private int changes;

    private int deletions;

    private String blobUrl;

    private String filename;

    private String patch;

    private String rawUrl;

    private String sha;

    private String status;

    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="commit_id")
    private RepositoryCommit commit;
}
