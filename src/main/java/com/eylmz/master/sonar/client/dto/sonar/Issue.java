package com.eylmz.master.sonar.client.dto.sonar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    private String key;
    private String rule;
    private String severity;
    private String component;
    private String project;
    private int line;
    private String hash;
    private TextRange textRange;
    private ArrayList<Flow> flows;
    private String status;
    private String message;
    private String effort;
    private String debt;

    private ArrayList<String> tags;

    private String creationDate;
    private String updateDate;
    private String type;
    private String organization;
    private boolean fromHotspot;

}
