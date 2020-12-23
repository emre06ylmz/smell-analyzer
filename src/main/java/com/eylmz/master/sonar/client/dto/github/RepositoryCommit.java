package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;
import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.CommitStats;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "github_repository_commits")
public class RepositoryCommit  implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8911733018395257250L;

    @Id
    @GeneratedValue
    private int id;

    private String sha;

    private String url;

    @ManyToOne(fetch= FetchType.LAZY, optional = true)
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne(fetch= FetchType.LAZY, optional = true)
    @JoinColumn(name="committer_id")
    private User committer;

    private String uuid;
}
