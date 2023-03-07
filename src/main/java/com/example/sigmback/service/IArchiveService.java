package com.example.sigmback.service;

import com.example.sigmback.model.Archive;
import com.example.sigmback.model.Bordereau;

import java.util.List;

public interface IArchiveService {

    Archive addArchive(Archive archive);

    Archive updateArchive(Archive archive);

    void deleteArchive(Long id_archive);

    List<Archive> retrieveArchive();

    Archive retrieveOneArchive(Long id_archive);
}
