package com.springprojectdefence.controller;

import com.springprojectdefence.interceptors.TitleInterceptor;
import com.springprojectdefence.models.bindingModels.car.CarAdvertModel;
import com.springprojectdefence.models.bindingModels.car.EditCarAdvertModel;
import com.springprojectdefence.service.CarAdvertService;
import com.springprojectdefence.viewModels.CarAdverView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.acl.LastOwnerException;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CarAdvertController.class)
@ActiveProfiles("test")
public class CarAdvertControllerTest {

    public static final long CAR_ID = 1;
    public static final String MAKE = "Ferrari";

    @Autowired
    private MockMvc mvc;

    @Captor
    private ArgumentCaptor<HttpServletRequest> requestArgumentCaptor;

    @Captor
    private ArgumentCaptor<HttpServletResponse> responseArgumentCaptor;

    @Captor
    private ArgumentCaptor<Object> handler;

    @MockBean
     private TitleInterceptor titleInterceptor;

    @MockBean
    private CarAdvertService carService;

    @Before
    public void setUp() throws Exception {
        EditCarAdvertModel carAdvertModel = new EditCarAdvertModel();
        carAdvertModel.setId(CAR_ID);
        carAdvertModel.setName(MAKE);
        CarAdverView carAdverView = new CarAdverView();
        carAdverView.setId(CAR_ID);
        carAdverView.setName(MAKE);
        when(this.carService.getById(CAR_ID)).thenReturn(carAdvertModel);
        when(this.carService.getViewById(CAR_ID)).thenReturn(carAdverView);
        when(this.titleInterceptor.preHandle(requestArgumentCaptor.capture(), responseArgumentCaptor.capture(), handler.capture()))
                .thenReturn(true);

    }

    @Test
    public void getDetailsPage() throws Exception {
        //Act
        this.mvc.perform(get("/cars/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("base-layout"))
                .andExpect(model().attribute("editCarAdvertModel", hasProperty("id", is(CAR_ID))))
                .andExpect(model().attribute("editCarAdvertModel", hasProperty("name", is(MAKE))));

        verify(this.carService, times(1)).getById(CAR_ID);
        verifyNoMoreInteractions(this.carService);

    }

    @Test
    public void getDeleteCarAdvertPage() throws Exception {
        //Act
        this.mvc.perform(get("/cars/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("base-layout"))
                .andExpect(model().attribute("car", hasProperty("id", is(CAR_ID))))
                .andExpect(model().attribute("car", hasProperty("name", is(MAKE))));

        verify(this.carService, times(1)).getViewById(CAR_ID);
        verifyNoMoreInteractions(this.carService);
    }
    @Test
    public void getAllCarsPage() throws Exception {

    }

}