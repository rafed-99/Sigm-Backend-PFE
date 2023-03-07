package com.example.sigmback.repository;

import com.example.sigmback.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointRepository extends JpaRepository<Point,Long> {
}
