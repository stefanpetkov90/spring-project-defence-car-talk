package com.springprojectdefence.serviceImpl;


import com.springprojectdefence.entities.Log;
import com.springprojectdefence.models.bindingModels.dto.LogDto;
import com.springprojectdefence.repository.LogRepository;
import com.springprojectdefence.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(LogDto log) {
        Log newLog = new Log();
        this.modelMapper.map(log, newLog);
        this.logRepository.save(newLog);
    }
}
