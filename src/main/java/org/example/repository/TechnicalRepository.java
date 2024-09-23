package org.example.repository;

import org.example.entities.attributes.TechnicalAttributes;

public class TechnicalRepository extends RepositoryManager<TechnicalAttributes, Integer> {

    public TechnicalRepository(){
        super(TechnicalAttributes.class);
    }
}
