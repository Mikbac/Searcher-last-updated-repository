package pl.MikBac.searchEngine.spring.repository.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.MikBac.searchEngine.exception.RepositoryNotFoundException;
import pl.MikBac.searchEngine.model.exte.RepositoryModel;
import pl.MikBac.searchEngine.spring.property.GithubProperties;
import pl.MikBac.searchEngine.spring.repository.RepositoryRepository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static pl.MikBac.searchEngine.constants.RequestConstants.Param.PER_PAGE;
import static pl.MikBac.searchEngine.constants.RequestConstants.REPOST;

/**
 * Created by MikBac on 2019
 */

@Log4j2
@Repository
public class RepositoryRepositoryImpl implements RepositoryRepository {

    @Resource
    private GithubProperties githubProperties;

    @Override
    public List<RepositoryModel> getRepositories(final String organizationName, final int pagesNumber) {
        log.info("[getRepositories] -- get repositories for organizationName: {}", () -> organizationName);

        final String url = getGithubApiUrl() + organizationName + "/" + REPOST + "?" + PER_PAGE + "=" + pagesNumber;
        final RepositoryModel[] repositories = readRepositories(url);

        if (repositories == null || repositories.length == 0) {
            throw new RepositoryNotFoundException(organizationName);
        }

        return Arrays.asList(repositories);
    }

    private RepositoryModel[] readRepositories(final String url) {
        return new RestTemplate().getForObject(url, RepositoryModel[].class);
    }

    private String getGithubApiUrl() {
        return githubProperties.getUrl();
    }

}
