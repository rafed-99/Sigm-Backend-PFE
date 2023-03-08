package com.example.sigmback.service;

import com.example.sigmback.model.Archive;
import com.example.sigmback.repository.IArchiveRepository;
import com.example.sigmback.repository.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveService implements IArchiveService{

    @Autowired
    IArchiveRepository iArchiveRepository;

    @Autowired
    IPointRepository iPointRepository;

    @Override
    public Archive addArchive(Archive archive) {
        return iArchiveRepository.save(archive);
    }

    @Override
    public Archive updateArchive(Archive archive) {
        return iArchiveRepository.save(archive);
    }

    @Override
    public void deleteArchive(Long id_archive) {
        iArchiveRepository.deleteById(id_archive);
    }

    @Override
    public List<Archive> retrieveArchive() {
        return iArchiveRepository.findAll();
    }

    @Override
    public Archive retrieveOneArchive(Long id_archive) {
        return iArchiveRepository.findById(id_archive).get();
    }

}
