package com.example.sigmback.service;

import com.example.sigmback.model.Geologie;
import com.example.sigmback.model.Point;

import java.util.List;

public interface IPointService {

    Point addPoint(Point point);

    Point updatePoint(Point point);

    void deletePoint(Long id_point);

    List<Point> retrievePoints();

    Point retrieveOnePoint(Long id_point);

    List<Geologie> retrieveGeologieByPoint(long id_point);
}
