package com.nexdin.store.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexdin.store.constant.FieldName;
import com.nexdin.store.exception.ResourceNotFoundException;
import com.nexdin.store.mapper.GenericMapper;
import com.nexdin.store.payload.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudService<ID, I, O> {
    PageResponse<O> findAll(int page, int size, String sort, boolean all);

    O findById(ID id);

    O save(I request);

    O save(ID id, I request);

    void delete(ID id);

    void delete(List<ID> ids);

    default O save(JsonNode request, Class<I> requestType) {
        ObjectMapper mapper = new ObjectMapper();
        I typedRequest = mapper.convertValue(request, requestType);
        return save(typedRequest);
    }

    default O save(ID id, JsonNode request, Class<I> requestType) {
        ObjectMapper mapper = new ObjectMapper();
        I typedRequest = mapper.convertValue(request, requestType);
        return save(id, typedRequest);
    }

    default <E> PageResponse<O> defaultFindAll(int page, int size, String sort, boolean all, JpaRepository<E, ID> repository, GenericMapper<E, I, O> mapper) {
        Pageable pageable = all ? Pageable.unpaged(Sort.by(sort)) : PageRequest.of(page - 1, size, Sort.by(sort));
        Page<E> entities = repository.findAll(pageable);
        List<O> entityResponses = mapper.entityToResponse(entities.getContent());
        return new PageResponse<O>(entityResponses, entities);
    }

    default <E> O defaultFindById(ID id, JpaRepository<E, ID> repository, GenericMapper<E, I, O> mapper, String resourceName) {
        return repository.findById(id).map(mapper::entityToResponse).orElseThrow(() -> new ResourceNotFoundException(resourceName, FieldName.ID, id));
    }

    default <E> O defaultSave(I request, JpaRepository<E, ID> repository, GenericMapper<E, I, O> mapper) {
        E entity = repository.save(mapper.requestToEntity(request));
        return mapper.entityToResponse(entity);
    }

    default <E> O defaultSave(ID id, I request, JpaRepository<E, ID> repository, GenericMapper<E, I, O> mapper, String resourceName) {
        return repository.findById(id)
                .map(existingEntity -> mapper.partialUpdate(existingEntity, request))
                .map(repository::save)
                .map(mapper::entityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, FieldName.ID, id));
    }

    default <E> void defaultDelete(ID id, JpaRepository<E, ID> repository, String resourceName) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException(resourceName, FieldName.ID, id);
        repository.deleteById(id);
    }
    default <E> void defaultDelete(List<ID> ids, JpaRepository<E, ID> repository, String resourceName) {
        ids.forEach(id -> {
            if (!repository.existsById(id)) throw new ResourceNotFoundException(resourceName, FieldName.ID, id);
            repository.deleteById(id);
        });
    }
}
