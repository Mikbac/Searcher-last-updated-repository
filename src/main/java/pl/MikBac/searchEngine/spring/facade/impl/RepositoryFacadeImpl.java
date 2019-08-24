package pl.MikBac.searchEngine.spring.facade.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import pl.MikBac.searchEngine.exception.ApiRateLimitException;
import pl.MikBac.searchEngine.exception.RepositoryNotFoundException;
import pl.MikBac.searchEngine.model.RepositoryModel;
import pl.MikBac.searchEngine.spring.facade.RepositoryFacade;
import pl.MikBac.searchEngine.spring.service.RepositoryService;

import javax.annotation.Resource;

/**
 * Created by MikBac on 03.08.2019
 */

@Component
public class RepositoryFacadeImpl implements RepositoryFacade {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public RepositoryModel getLastModifiedRepository(final String organizationName) {
        try {
            return repositoryService.getLastUpdatedRepository(organizationName);
        } catch (HttpStatusCodeException e) {
            throw new ApiRateLimitException(organizationName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryNotFoundException(organizationName);
        }
    }
}