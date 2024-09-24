package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {

    T save(T entity);

    Iterable<T> saveAll(Iterable<T> entities);

    T update(T entity);

    Boolean deleteById(ID id);

    Optional<T> findById(ID id);

    Boolean existById(ID id);

    List<T> findAll();

    List<T> findByFieldNameAndValue(String fieldName,Object value);
}
