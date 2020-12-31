package com.eylmz.master.sonar.client.dto.sonar;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rules")
public class RuleSonar  implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6358575015023539051L;

    @Id
    private int id;
    private String language;
}
