package com.joaodss.tradeinwebsite.categorybrandspecification.controller.impl;

import com.joaodss.tradeinwebsite.categorybrandspecification.controller.SpecificationController;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.NewCategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/specification")
@RequiredArgsConstructor
@Slf4j
public class SpecificationControllerImpl implements SpecificationController {
    private final SpecificationService service;


    // ------------------------------ Get all ------------------------------
    @GetMapping
    @ResponseStatus(OK)
    public List<CategoryBrandSpecificationDTO> getAllSpecifications() {
        log.info("Getting all specifications");
        try {
            return service.getAllSpecifications();
        } catch (Exception e) {
            log.error("Error getting all specifications", e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by id ------------------------------
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryBrandSpecificationDTO getSpecificationsById(
            @PathVariable("id") long id
    ) {
        log.info("Getting specification by id {}", id);
        try {
            return service.getSpecificationById(id);
        } catch (NoSuchElementException e1) {
            log.error("Specification with id {} not found", id);
            throw new ResponseStatusException(NOT_FOUND, "Specification with id " + id + " not found");
        } catch (Exception e) {
            log.error("Error getting specification by id {}", id, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by params ------------------------------
    @GetMapping(params = {"category", "brand"})
    @ResponseStatus(OK)
    public CategoryBrandSpecificationDTO getSpecificationsByCategoryAndBrand(
            @RequestParam("category") String category,
            @RequestParam("brand") String brand
    ) {
        log.info("Getting specification by category {} and brand {}", category, brand);
        try {
            return service.getSpecificationByCategoryAndBrand(category, brand);
        } catch (NoSuchElementException e1) {
            log.error("Specification with category {} and brand {} not found", category, brand);
            throw new ResponseStatusException(NOT_FOUND, "Specification with category " + category + " and brand "
                    + brand + " not found");
        } catch (Exception e) {
            log.error("Error getting specification by category and brand", e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }


    // ------------------------------ Post ------------------------------
    @PostMapping
    @ResponseStatus(CREATED)
    public CategoryBrandSpecificationDTO createSpecification(
            @RequestBody @Valid NewCategoryBrandSpecificationDTO specification
    ) {
        log.info("Creating specification {}", specification);
        try {
            return service.createSpecification(specification);
        } catch (IllegalArgumentException e1) {
            log.error("Specification {} not valid", specification, e1);
            throw new ResponseStatusException(BAD_REQUEST, "Specification " + specification + " not valid");
        } catch (
                Exception e) {
            log.error("Error creating specification {}", specification, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }

    }


    // ------------------------------ Patch ------------------------------
    @PatchMapping
    @ResponseStatus(OK)
    public CategoryBrandSpecificationDTO updateSpecification(
            @RequestBody @Valid NewCategoryBrandSpecificationDTO specification
    ) {
        log.info("Updating specification {}", specification);
        try {
            return service.updateSpecification(specification);
        } catch (NoSuchElementException e1) {
            log.error("Specification with category {} and brand {} not found",
                    specification.getCategory(), specification.getBrand());
            throw new ResponseStatusException(NOT_FOUND, "Specification with category " + specification.getCategory()
                    + " and brand " + specification.getBrand() + " not found");
        } catch (IllegalArgumentException e2) {
            log.error("Specification {} not valid", specification, e2);
            throw new ResponseStatusException(BAD_REQUEST, "Specification " + specification + " not valid");
        } catch (Exception e) {
            log.error("Error updating specification {}", specification, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }


    // ------------------------------ Delete ------------------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryBrandSpecificationDTO deleteSpecificationById(
            @PathVariable("id") long id
    ) {
        log.info("Deleting specification by id {}", id);
        try {
            return service.deleteSpecificationById(id);
        } catch (NoSuchElementException e1) {
            log.error("Specification with id {} not found", id);
            throw new ResponseStatusException(NOT_FOUND, "Specification with id " + id + " not found");
        } catch (Exception e) {
            log.error("Error deleting specification by id {}", id, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

}
