package com.springprojectdefence.controller;

import com.springprojectdefence.models.bindingModels.car.AddCarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.CarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.CarDetailsModel;
import com.springprojectdefence.models.bindingModels.car.EditCarAdvertModel;
import com.springprojectdefence.service.CarAdvertService;
import com.springprojectdefence.viewModels.CarAdverView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarAdvertController {
    @Autowired
    private CarAdvertService carAdvertService;

    @GetMapping("all")
    public String getAllCarsPage(Model model) {
        List<CarAdverView> cars = this.carAdvertService.getAll();
        model.addAttribute("title", "Cars");
        model.addAttribute("cars", cars);
        model.addAttribute("view", "/cars/cars-all");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddCarPage(@ModelAttribute AddCarAdvertModel addCarAdvertModel, Model model){
        model.addAttribute("title", "Add Car");
        model.addAttribute("cars", addCarAdvertModel);
        model.addAttribute("view","/cars/cars-add");
        return "base-layout";
    }

    @PostMapping("add")
    public String addCar(@Valid @ModelAttribute AddCarAdvertModel addCarAdvertModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "cars/parts-add";
        }
        this.carAdvertService.persist(addCarAdvertModel);
        return "redirect:/cars/all";
    }

    @GetMapping("edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        EditCarAdvertModel editCarAdvertModel = this.carAdvertService.getById(id);
        model.addAttribute("title", "Edit Car");
        model.addAttribute("editCarAdvertModel", editCarAdvertModel);
        model.addAttribute("view","/cars/cars-edit");
        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editCar(@PathVariable Long id, @Valid @ModelAttribute EditCarAdvertModel editCarAdvertModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "cars/cars-edit";
        }
        editCarAdvertModel.setId(id);
        this.carAdvertService.update(editCarAdvertModel);
        return "redirect:/cars/all";
    }

    @GetMapping("details/{id}")
    public String getDetailsPage(Model model, @PathVariable Long id) {
        CarDetailsModel carDetailsModel = this.carAdvertService.getDetailsById(id);
        model.addAttribute("title", "Car Details");
        model.addAttribute("car", carDetailsModel);
        model.addAttribute("view","/cars/cars-details");
        return "/cars/cars-details";
    }

    @GetMapping("delete/{id}")
    public String getDeleteCarAdvertPage(Model model,@PathVariable Long id){
        CarAdverView carAdverView = this.carAdvertService.getViewById(id);
        model.addAttribute("title", "Delete Car");
        model.addAttribute("view","/cars/cars-delete");
        model.addAttribute("car",carAdverView);
        return "base-layout";
    }

    @PostMapping("delete/{id}")
    public String deleteCarAdvert(@ModelAttribute EditCarAdvertModel editCarAdvertModel,@PathVariable Long id){
        editCarAdvertModel.setId(id);
        this.carAdvertService.delete(editCarAdvertModel);
        return "redirect:/cars/all";
    }

    @GetMapping("table")
    public String getTablePage(Model model) {
        List<CarAdverView> carsTable = this.carAdvertService.getAll();
        model.addAttribute("title", "Car Table");
        model.addAttribute("carsTable", carsTable);
        /*model.addAttribute("view","/cars/cars-table");*/
        return "/cars/cars-table";
    }
}
