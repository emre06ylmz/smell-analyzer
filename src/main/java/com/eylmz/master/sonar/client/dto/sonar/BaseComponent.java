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
public class BaseComponent {

    private String id;
    private String key;
    private String name;
    private String qualifier;
    private ArrayList<Measure> measures;

}
