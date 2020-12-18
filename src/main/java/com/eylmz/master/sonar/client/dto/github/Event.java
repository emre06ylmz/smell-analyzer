package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "github_events")
public class Event implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8634781540034147721L;

    private String type;

    private boolean isPublic;

    @Id
    private String id;

    private Date createdAt;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
