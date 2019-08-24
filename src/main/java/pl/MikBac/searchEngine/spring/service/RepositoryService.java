package pl.MikBac.searchEngine.spring.service;

import pl.MikBac.searchEngine.model.RepositoryModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by MikBac on 2019
 */

public interface RepositoryService {

    List<RepositoryModel> getAllRepositories(final String organizationName) throws Exception;

    int getRepositoriesQuantity(final String organizationName);

    RepositoryModel getLastUpdatedRepository(final String organizationName) throws Exception;

    LocalDateTime getISODate(final String dateString);

    RepositoryModel getLatestDate(final List<RepositoryModel> repositories);

}