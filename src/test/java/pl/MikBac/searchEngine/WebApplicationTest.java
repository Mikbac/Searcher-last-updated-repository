package pl.MikBac.searchEngine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.MikBac.searchEngine.spring.controller.RepositoryRestController;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MikBac on 2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class WebApplicationTest {

    @Resource
    RepositoryRestController repositoryRestController;

    @Test
    public void contextLoads() {
        assertThat(repositoryRestController).isNotNull();
    }

}
