package com.springprojectdefence.service;

import com.springprojectdefence.models.bindingModels.car.AddCarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.CarDetailsModel;
import com.springprojectdefence.models.bindingModels.car.EditCarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.CarAdvertModel;
import com.springprojectdefence.viewModels.CarAdverView;

import java.util.List;

public interface CarAdvertService {
    void persist(AddCarAdvertModel addCarAdvertModel);
    List<CarAdverView> getAll();
    EditCarAdvertModel getById(Long id);
    CarDetailsModel getDetailsById(Long id);
    void update(EditCarAdvertModel partModel);
    CarAdverView getViewById(Long id);
    void delete(EditCarAdvertModel id);
    CarAdvertModel getByName(String name);
}
