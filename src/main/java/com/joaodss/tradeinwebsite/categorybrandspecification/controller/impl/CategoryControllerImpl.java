package com.joaodss.tradeinwebsite.categorybrandspecification.controller.impl;

import com.joaodss.tradeinwebsite.categorybrandspecification.controller.CategoryController;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService service;


    // ------------------------------ Get all ------------------------------
    @GetMapping
    @ResponseStatus(OK)
    public List<CategoryDTO> getAllCategories() {
        log.info("Getting all categories");
        try {
            return service.getAllCategories();
        } catch (Exception e) {
            log.error("Error getting all categories", e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get all names ------------------------------
    @GetMapping("/names")
    @ResponseStatus(OK)
    public List<String> getAllCategoryNames() {
        log.info("Getting all category names");
        try {
            return service.getAllCategoryNames();
        } catch (Exception e) {
            log.error("Error getting all category names", e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by id ------------------------------
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryDTO getCategoryById(
            @PathVariable("id") long id
    ) {
        log.info("Getting category by id {}", id);
        try {
            return service.getCategoryById(id);
        } catch (NoSuchElementException e1) {
            log.error("Category with id {} not found", id);
            throw new ResponseStatusException(NOT_FOUND, "Category with id " + id + " not found");
        } catch (Exception e) {
            log.error("Error getting category by id {}", id, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by name ------------------------------
    @GetMapping(params = {"name"})
    @ResponseStatus(OK)
    public List<CategoryDTO> getCategoryByName(
            @RequestParam("name") String categoryName
    ) {
        log.info("Getting category by name {}", categoryName);
        try {
            return service.getCategoryByName(categoryName);
        } catch (Exception e) {
            log.error("Error getting category by name {}", categoryName, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }


    // ------------------------------ Post ------------------------------
    @PostMapping
    @ResponseStatus(CREATED)
    public List<String> createCategories(
            @RequestBody List<String> categories
    ) {
        log.info("Creating categories {}", categories);
        try {
            return service.createCategories(categories);
        } catch (Exception e) {
            log.error("Error creating categories {}", categories, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }


    // ------------------------------ Delete ------------------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryDTO deleteCategoryById(
            @PathVariable("id") long id
    ) {
        log.info("Deleting category by id {}", id);
        try {
            return service.deleteCategoryById(id);
        } catch (NoSuchElementException e1) {
            log.error("Category with id {} not found", id);
            throw new ResponseStatusException(NOT_FOUND, "Category with id " + id + " not found");
        } catch (Exception e) {
            log.error("Error deleting category by id {}", id, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

}
