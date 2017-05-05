package com.springprojectdefence.repository;

import com.springprojectdefence.entities.CarAdvert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CarAdvertRepositoryTest {

    public static final String NAME = "Ferrari";

    @Autowired
    private TestEntityManager em;

    @Autowired
    CarAdvertRepository carAdvertRepository;

    @Before
    public void setUp() throws Exception {
        //Arrange
        CarAdvert carAdvert = new CarAdvert();
        carAdvert.setName(NAME);
        carAdvert.setPrice(150000);
        this.em.persist(carAdvert);
    }

    @Test
    public void findCarsWhichCostMoreThan100K() throws Exception {
        //Act
        List<CarAdvert> carAdverts = this.carAdvertRepository.findCarsWhichCostMoreThan100K();

        //Assert
        assertEquals(1, carAdverts.size());

        //Assert
        CarAdvert carAdvert = carAdverts.get(0);
        assertEquals(NAME, carAdvert.getName());

    }
}