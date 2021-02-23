package com.springboottest.repository;

import com.springboottest.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.name LIKE CONCAT('%',:ending, '%') AND c.population < :num")
    List<City> findByNameEndingWithAndPopulationLessThan(@Param("ending") String ending, @Param("num") Integer num);


    @Query("SELECT c FROM City c WHERE c.name LIKE CONCAT('%',:city, '%')")
    List<City> findByCityName(@Param("city") String city);


}
