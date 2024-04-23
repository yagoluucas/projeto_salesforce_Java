package org.repository;

import org.entities._BaseEntity;

import java.util.List;

// esta interface foi feita para ser implementada por todas as classes de repositório
public interface _BaseRepository<T extends _BaseEntity> {
    void Create(T entidade);
    List<T> ReadAll();
    T Read(int id);
    void Update(T entidade);
    void Delete(int id);
}
