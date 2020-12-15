package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Contributor;
import com.eylmz.master.sonar.client.repository.IContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ContributorService implements IContributorService{

    @Autowired
    private IContributorRepository contributorRepository;

    @Override
    public void addContributor(Contributor contributor) {
        this.contributorRepository.save(contributor);
    }


    public List<Contributor> getAllContributors() {
        List<Contributor> contributors = new ArrayList<>();
        this.contributorRepository.findAll().forEach(contributors::add);
        return contributors;
    }

}
