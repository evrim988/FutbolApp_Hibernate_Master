package org.example.repository;

import org.example.entities.attributes.MentalAttributes;

public class MentalAttributesRepository extends RepositoryManager<MentalAttributes, Integer> {

    public MentalAttributesRepository() {
        super(MentalAttributes.class);
    }
}
