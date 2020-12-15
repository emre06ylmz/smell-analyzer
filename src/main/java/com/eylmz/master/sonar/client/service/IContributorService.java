package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Contributor;

import java.util.List;

public interface IContributorService {
    void addContributor(Contributor contributor);
    List<Contributor> getAllContributors();
}
