package com.example.sigmback.service;

import com.example.sigmback.model.Element;

import java.util.List;

public interface IElementService {

    Element addElement(Element element);

    Element updateElement(Element element);

    void deleteElement(Long id_element);

    List<Element> retrieveElements();

    Element retrieveOneElement(Long id_element);
}
