package org.example.repository;

import org.example.entities.attributes.GKAttributes;

public class GkaAttributesRepository extends RepositoryManager<GKAttributes, Integer> {

    public GkaAttributesRepository() {
        super(GKAttributes.class);
    }
}
