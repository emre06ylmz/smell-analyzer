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
public class IssueResult {

    private int total;
    private int p;
    private int ps;

    private Paging paging;

    private int effortTotal;
    private int debtTotal;
    private ArrayList<IssueSonar> issues;
    private ArrayList<Component> components;
    private ArrayList<Facet> facets;
}
