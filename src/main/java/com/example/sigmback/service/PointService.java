package com.example.sigmback.service;

import com.example.sigmback.model.Archive;
import com.example.sigmback.model.Echantillon;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.model.Point;
import com.example.sigmback.repository.IArchiveRepository;
import com.example.sigmback.repository.IGisementRepository;
import com.example.sigmback.repository.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService implements IPointService{

    @Autowired
    IPointRepository iPointRepository;

    @Autowired
    IGisementRepository iGisementRepository;

    @Autowired
    IArchiveRepository iArchiveRepository;

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

    @Override
    public List<Point> retrievePointsByGisement(Long id_gisement) {

        return iGisementRepository.findById(id_gisement).get().getPoints();
    }

    @Override
    public List<Point> retrieveArchiveByPoint(Long id_archive){
        return iArchiveRepository.findById(id_archive).get().getPoints();
    }
}
