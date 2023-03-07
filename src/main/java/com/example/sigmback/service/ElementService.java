package com.example.sigmback.service;

import com.example.sigmback.model.Element;
import com.example.sigmback.repository.IElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService implements IElementService{

    @Autowired
    IElementRepository iElementRepository;

    @Override
    public Element addElement(Element element) {

        return iElementRepository.save(element);
    }

    @Override
    public Element updateElement(Element element) {

        return iElementRepository.save(element);
    }

    @Override
    public void deleteElement(Long id_element) {

        iElementRepository.deleteById(id_element);
    }

    @Override
    public List<Element> retrieveElements() {

        return (List<Element>)iElementRepository.findAll();
    }

    @Override
    public Element retrieveOneElement(Long id_element) {

        return iElementRepository.findById(id_element).get();
    }
}
