package com.eylmz.master.sonar.client.dto.sonar;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paging {
    private int pageIndex;
    private int pageSize;
    private int total;
}
