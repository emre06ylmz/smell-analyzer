package com.eylmz.master.sonar.client.dto.github;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Summary implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 4759542049129654659L;

    private String projectName;

    private int smellCount;

    private int issueCount;

    private int pullRequestCount;

    private int commitCount;

    private int fileCount;

    private List<SummaryDeveloper> developers;

}
