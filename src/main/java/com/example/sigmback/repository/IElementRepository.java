package com.example.sigmback.repository;

import com.example.sigmback.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementRepository extends JpaRepository<Element,Long> {
}
