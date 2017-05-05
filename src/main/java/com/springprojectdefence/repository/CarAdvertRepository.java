package com.springprojectdefence.repository;

import com.springprojectdefence.entities.CarAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CarAdvertRepository extends JpaRepository<CarAdvert,Long> {

    @Modifying
    @Query("UPDATE CarAdvert AS p SET " +
            "p.name = :name, " +
            "p.model = :model," +
            "p.price = :price, " +
            "p.engineType = :engineType, " +
            "p.imgUrl = :imgUrl, " +
            "p.travelledDistance = :travelledDistance, " +
            "p.color = :color," +
            "p.sellerPhone = :sellerPhone WHERE p.id = :id")
    void update(@Param("name")String name,
                @Param("model")String model,
                @Param("price") Integer price,
                @Param("id") Long id,
                @Param("engineType") String engineType,
                @Param("imgUrl") String imgUrl,
                @Param("travelledDistance") Integer travelledDistance,
                @Param("color") String color,
                @Param("sellerPhone")String sellerPhone);
    CarAdvert findByName(String name);

    @Query(value = "SELECT p FROM CarAdvert AS p "+"WHERE p.price > 100000")
    List<CarAdvert>findCarsWhichCostMoreThan100K();
}
