package com.nexdin.store.service;

import com.nexdin.store.mapper.GenericMapper;
import com.nexdin.store.payload.response.PageResponse;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Scope("prototype")
public class GenericService<E, I, O> implements CrudService<Integer, I, O> {
    private JpaRepository<E, Integer> repository;
    private GenericMapper<E, I, O> mapper;
    private String resourceName;

    public <R extends JpaRepository<E, Integer>> GenericService<E, I, O> init(R repository, GenericMapper<E, I, O> mapper, String resourceName) {
        this.setRepository(repository);
        this.setMapper(mapper);
        this.setResourceName(resourceName);
        return this;
    }

    @Override
    public PageResponse<O> findAll(int page, int size, String sort, boolean all) {
        return defaultFindAll(page, size, sort, all, repository, mapper);
    }

    @Override
    public O findById(Integer id) {
        return defaultFindById(id, repository, mapper, resourceName);
    }

    @Override
    public O save(I request) {
        return defaultSave(request, repository, mapper);
    }

    @Override
    public O save(Integer id, I request) {
        return defaultSave(id, request, repository, mapper, resourceName);
    }

    @Override
    public void delete(Integer id) {
        defaultDelete(id, repository, resourceName);
    }

    @Override
    public void delete(List<Integer> ids) {
        defaultDelete(ids, repository, resourceName);
    }
}
