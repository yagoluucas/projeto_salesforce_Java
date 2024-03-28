package org.repository;

import org.entities._BaseEntities;

import java.util.List;

public interface _BaseRepository<T extends _BaseEntities> {
    void Create(T entidade);
    List<T> ReadAll();
    T Read(int id);
    void Update(T entidade);
    void Delete(int id);
}
