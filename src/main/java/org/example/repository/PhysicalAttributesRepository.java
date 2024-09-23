package org.example.repository;

import org.example.entities.attributes.PhysicalAttributes;

public class PhysicalAttributesRepository extends RepositoryManager<PhysicalAttributes, Integer> {

    public PhysicalAttributesRepository() {
        super(PhysicalAttributes.class);
    }
}
