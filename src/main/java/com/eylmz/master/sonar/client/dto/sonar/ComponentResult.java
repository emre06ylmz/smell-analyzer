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
public class ComponentResult {

    private Paging paring;
    private BaseComponent baseComponent;
    private ArrayList<Component> components;

}
