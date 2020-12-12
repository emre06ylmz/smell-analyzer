package com.eylmz.master.sonar.client.webclient;


import com.eylmz.master.sonar.client.dto.sonar.ComponentResult;
import com.eylmz.master.sonar.client.dto.sonar.IssueResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sonar-qube-service", url = "http://localhost:9000")
public interface SonarFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/issues/search?id={id}&types={types}&pageSize=500")
    IssueResult getIssues(@PathVariable("id") String id, @PathVariable("types") String types);

    @RequestMapping(method = RequestMethod.GET, value = "/api/measures/component_tree?component={component}&metricKeys={metricKeys}")
    ComponentResult getComponents(@PathVariable("component") String component, @PathVariable("metricKeys") String metricKeys);

}
