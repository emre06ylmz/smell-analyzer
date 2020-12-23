package com.eylmz.master.sonar.client.dto.sonar;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "issues")
public class IssueSonar implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6358575015023539051L;

    @Id
    private String kee;

    private String rule_id;
    private String severity;
    private String message;
    private String status;
    private String checksum;


    private String component_uuid;
    private String project_uuid;

    private String issue_type;
}
