package com.example.sigmback.service;

import com.example.sigmback.model.Point;
import com.example.sigmback.repository.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService implements IPointService{

    @Autowired
    IPointRepository iPointRepository;
    @Override
    public Point addPoint(Point point) {

        return iPointRepository.save(point);
    }

    @Override
    public Point updatePoint(Point point) {

        return iPointRepository.save(point);
    }

    @Override
    public void deletePoint(Long id_point) {
        iPointRepository.deleteById(id_point);
    }

    @Override
    public List<Point> retrievePoints() {

        return (List<Point>)iPointRepository.findAll();
    }

    @Override
    public Point retrieveOnePoint(Long id_point) {

        return iPointRepository.findById(id_point).get();
    }
}
