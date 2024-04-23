package org.repository;

import org.entities._BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class _BaseRepositoryIMPL<T extends _BaseEntity> implements _BaseRepository<T> {
    List<T> entidades = new ArrayList<>();

    public _BaseRepositoryIMPL(){}

    public _BaseRepositoryIMPL(List<T> entidades) {
        this.entidades = entidades;
    }

    public List<T> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<T> entidades) {
        this.entidades = entidades;
    }

    @Override
    public void Create(T entidade) {
        this.entidades.add(entidade);
    }

    @Override
    public List<T> ReadAll() {
        return this.entidades;
    }

    @Override
    public T Read(int id) {
        return this.entidades.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void Update(T entidade) {
        entidades.removeIf(e -> e.getId() == entidade.getId());
        entidades.add(entidade);
    }

    @Override
    public void Delete(int id) {
        entidades.removeIf(e -> e.getId() == id);
    }
}
