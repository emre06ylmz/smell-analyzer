package com.eylmz.master.sonar.client.controller;

import com.eylmz.master.sonar.client.dto.sonar.ComponentResult;
import com.eylmz.master.sonar.client.dto.sonar.IssueResult;
import com.eylmz.master.sonar.client.service.ISonarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sonar")
public class SonarController {

    private final ISonarService sonarService;

    @GetMapping("/getIssues")
    public IssueResult getIssues(@RequestParam(name = "id") String id, @RequestParam(name = "types") String types) {
        return sonarService.getIssues(id, types);
    }

    @GetMapping("/getComponents")
    public ComponentResult getComponents(@RequestParam(name = "component") String component, @RequestParam(name = "metricKeys") String metricKeys) {
        return sonarService.getComponents(component, metricKeys);
    }
}
