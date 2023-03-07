package com.example.sigmback.repository;

import com.example.sigmback.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchiveRepository extends JpaRepository<Archive,Long> {
}
