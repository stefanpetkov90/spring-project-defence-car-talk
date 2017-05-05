package com.springprojectdefence.serviceImpl;

import com.springprojectdefence.entities.CarAdvert;
import com.springprojectdefence.models.bindingModels.car.AddCarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.CarDetailsModel;
import com.springprojectdefence.models.bindingModels.car.EditCarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.CarAdvertModel;
import com.springprojectdefence.repository.CarAdvertRepository;
import com.springprojectdefence.service.CarAdvertService;
import com.springprojectdefence.viewModels.CarAdverView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarAdvertServiceImpl implements CarAdvertService {
    @Autowired
    private CarAdvertRepository carAdvertRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void persist(AddCarAdvertModel addCarAdvertModel) {
        ModelMapper modelMapper = new ModelMapper();
        CarAdvert carAdvert = modelMapper.map(addCarAdvertModel, CarAdvert.class);
        this.carAdvertRepository.save(carAdvert);
    }

    @Override
    public List<CarAdverView> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<CarAdvert> carAdverts = this.carAdvertRepository.findAll();
        List<CarAdverView> carAdverViews = new ArrayList<>();
        for (CarAdvert carAdvert : carAdverts) {
            CarAdverView carAdverView = modelMapper.map(carAdvert, CarAdverView.class);
            carAdverViews.add(carAdverView);
        }

        return carAdverViews;
    }

    @Override
    public EditCarAdvertModel getById(Long id) {
        CarAdvert carAdvert = this.carAdvertRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditCarAdvertModel editCarAdvertModel = null;
        if (carAdvert != null) {
            editCarAdvertModel = modelMapper.map(carAdvert, EditCarAdvertModel.class);
        }
        return editCarAdvertModel;
    }

    @Override
    public CarDetailsModel getDetailsById(Long id) {
        CarAdvert carAdvert = this.carAdvertRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        CarDetailsModel carDetailsModel = null;
        if (carAdvert != null) {
            carDetailsModel = modelMapper.map(carAdvert, CarDetailsModel.class);
        }
        return carDetailsModel;
    }

    @Override
    public void update(EditCarAdvertModel editCarAdvertModel) {
        this.carAdvertRepository.update
                (editCarAdvertModel.getName(),
                        editCarAdvertModel.getModel(),
                        editCarAdvertModel.getPrice(),
                        editCarAdvertModel.getId(),
                        editCarAdvertModel.getEngineType(),
                        editCarAdvertModel.getImgUrl(),
                        editCarAdvertModel.getTravelledDistance(),
                        editCarAdvertModel.getColor(),
                        editCarAdvertModel.getSellerPhone());
    }

    @Override
    public CarAdverView getViewById(Long id) {
        CarAdvert carAdvert = this.carAdvertRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        CarAdverView carAdverView = null;
        if (carAdvert != null) {
            carAdverView = modelMapper.map(carAdvert, CarAdverView.class);
        }
        return carAdverView;
    }

    @Override
    public void delete(EditCarAdvertModel editCarAdvertModel) {
        this.carAdvertRepository.delete(editCarAdvertModel.getId());
    }

    @Override
    public CarAdvertModel getByName(String name) {
        CarAdvert carAdvert = this.carAdvertRepository.findByName(name);
        ModelMapper modelMapper = new ModelMapper();
        CarAdvertModel carAdvertModel = null;
        if (carAdvert != null) {
            carAdvertModel = modelMapper.map(carAdvert, CarAdvertModel.class);
        }
        return carAdvertModel;
    }
}
