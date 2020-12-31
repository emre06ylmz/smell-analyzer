package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;

import java.io.Serializable;

@Data
public class SummaryDeveloper implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 271938589340613208L;

    private String name;

    private int followerCount;

    private int activityCount;

    private int gistCount;

    private int repoCount;
}
