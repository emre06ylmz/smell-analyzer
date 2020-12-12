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
public class Component {

    private String id;
    private String organization;
    private String key;
    private String uuid;
    private boolean enabled;
    private String qualifier;
    private String name;
    private String longName;
    private String language;
    private String path;
    private ArrayList<Measure> measures;

}
