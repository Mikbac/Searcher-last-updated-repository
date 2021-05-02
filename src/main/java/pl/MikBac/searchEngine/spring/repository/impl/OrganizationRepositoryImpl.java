package pl.MikBac.searchEngine.spring.repository.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.MikBac.searchEngine.model.exte.OrganizationModel;
import pl.MikBac.searchEngine.spring.property.GithubProperties;
import pl.MikBac.searchEngine.spring.repository.OrganizationRepository;

import javax.annotation.Resource;

/**
 * Created by MikBac on 2019
 */

@Log4j2
@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {

    @Resource
    private GithubProperties githubProperties;

    @Override
    public int getRepositoriesQuantity(final String organizationName) {
        log.info("[getRepositoriesQuantity] -- get repositories quantity for organizationName: {} ", () -> organizationName);

        RestTemplate restTemplate = new RestTemplate();
        final String url = getGithubApiUrl() + organizationName;
        OrganizationModel organizationModel = restTemplate.getForObject(url, OrganizationModel.class);
        return organizationModel != null ? organizationModel.getPublicRepos() : 0;
    }

    private String getGithubApiUrl() {
        return githubProperties.getUrl();
    }

}
