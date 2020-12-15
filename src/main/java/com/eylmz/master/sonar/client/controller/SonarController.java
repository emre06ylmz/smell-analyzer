package com.eylmz.master.sonar.client.controller;

import com.eylmz.master.sonar.client.dto.Project;
import com.eylmz.master.sonar.client.dto.sonar.ComponentResult;
import com.eylmz.master.sonar.client.dto.sonar.IssueResult;
import com.eylmz.master.sonar.client.service.IProjectService;
import com.eylmz.master.sonar.client.service.ISonarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sonar")
public class SonarController {

    private final ISonarService sonarService;
    private final IProjectService projectService;


    @GetMapping("/getProjects")
    public List<Project> getProjects(@RequestParam(name = "uuid", required = false) String uuid) {
        return projectService.listProject();
    }

    @GetMapping("/getFiles")
    public List<Project> getFiles(@RequestParam(name = "uuid") String uuid) {
        return projectService.listFiles(uuid);
    }

    @GetMapping("/getIssues")
    public IssueResult getIssues(@RequestParam(name = "id") String id, @RequestParam(name = "types") String types) {
        return sonarService.getIssues(id, types);
    }

    @GetMapping("/getComponents")
    public ComponentResult getComponents(@RequestParam(name = "component") String component, @RequestParam(name = "metricKeys") String metricKeys) {
        return sonarService.getComponents(component, metricKeys);
    }
}
