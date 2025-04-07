package com.nexdin.store.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nexdin.store.constant.AppConstants;
import com.nexdin.store.payload.response.PageResponse;
import com.nexdin.store.payload.Success;
import com.nexdin.store.service.CrudService;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Setter
@Scope("prototype")
public class GenericController<I, O> {

    private CrudService<Integer, I, O> crudService;
    private Class<I> requestType;

    public ResponseEntity<Success<PageResponse<O>>> getAllResource(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "all", required = false) boolean all) {
        return ResponseEntity.status(HttpStatus.OK).body(new Success<>(
                200,
                "ok",
                LocalDateTime.now(),
                crudService.findAll(page, size, sort, all)
        ));
    }

    public ResponseEntity<Success<O>> getResource(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Success<>(
                200,
                "ok",
                LocalDateTime.now(),
                crudService.findById(id)
        ));
    }

    public ResponseEntity<Success<O>> createResource(@RequestBody JsonNode request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Success<>(
                201,
                "created",
                LocalDateTime.now(),
                crudService.save(request, requestType)
        ));
    }

    public ResponseEntity<Success<O>> updateResource(@PathVariable("id") Integer id, @RequestBody JsonNode request) {
        return ResponseEntity.status(HttpStatus.OK).body(new Success<>(
                200,
                "updated",
                LocalDateTime.now(),
                crudService.save(id, request, requestType)
        ));
    }

    public ResponseEntity<Void> deleteResource(@PathVariable("id") Integer id) {
        crudService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Void> deleteResource(@RequestBody List<Integer> ids) {
        crudService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
