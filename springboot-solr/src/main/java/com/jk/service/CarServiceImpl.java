package com.jk.service;

import com.jk.mapper.CarMapper;
import com.jk.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements  CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public void addcar(Car car) {
        carMapper.addcar(car);
    }

    @Override
    public void del(String id) {
        carMapper.del(id);
    }
}
