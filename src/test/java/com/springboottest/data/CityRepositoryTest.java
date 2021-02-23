package com.springboottest.data;


import com.springboottest.model.City;
import com.springboottest.repository.CityRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

//below annotation is needed only in junit 4.
@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback
@TestPropertySource("/application.properties")
public class CityRepositoryTest {
    //Inject the JPA Repository you want to
    // test, for example
    @Autowired
    CityRepository cityRepository;

    @Autowired
    //TestEntityManager is designed for tests, provides
    // an alternative to the JPA standard EntityManager
    private TestEntityManager testEntityManager;

    @Before
    public void setUp(){
        // given
        City city = new City();
        city.setName("Chicago");
        city.setPopulation(10000000);
        testEntityManager.persist(city);
    }

    @Test
    //by default will begin a new transaction before and roll back it after running a test method.
    public void findByCityName() {

        List<City> cityList = cityRepository.findByCityName("Chicago");
        assertThat(cityList, hasSize(1));
        // then
        //assertThat(city.getDescription()).isEqualTo("P1 desc");
    }


}
