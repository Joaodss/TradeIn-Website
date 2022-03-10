package com.joaodss.tradeinwebsite.categorybrandspecification.controller.impl;

import com.joaodss.tradeinwebsite.categorybrandspecification.controller.BrandController;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.BrandDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
@Slf4j
public class BrandControllerImpl implements BrandController {
    private final BrandService service;


    // ------------------------------ Get all ------------------------------
    @GetMapping
    @ResponseStatus(OK)
    public List<BrandDTO> getAllBrands() {
        log.info("Getting all brands");
        try {
            return service.getAllBrands();
        } catch (Exception e) {
            log.error("Error getting all brands", e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get all names ------------------------------
    @GetMapping("/names")
    @ResponseStatus(OK)
    public List<String> getAllBrandNames() {
        log.info("Getting all brand names");
        try {
            return service.getAllBrandNames();
        } catch (Exception e) {
            log.error("Error getting all brand names", e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by id ------------------------------
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public BrandDTO getBrandById(
            @PathVariable("id") long id
    ) {
        log.info("Getting brand by id {}", id);
        try {
            return service.getBrandById(id);
        } catch (NoSuchElementException e1) {
            log.error("Brand with id {} not found", id);
            throw new ResponseStatusException(NOT_FOUND, "Brand with id " + id + " not found");
        } catch (Exception e) {
            log.error("Error getting brand by id {}", id, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by name ------------------------------
    @GetMapping(params = {"name"})
    @ResponseStatus(OK)
    public List<BrandDTO> getBrandByName(
            @RequestParam("name") String brandName
    ) {
        log.info("Getting brand by name {}", brandName);
        try {
            return service.getBrandByName(brandName);
        } catch (Exception e) {
            log.error("Error getting brand by name {}", brandName, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }


    // ------------------------------ Post ------------------------------
    @PostMapping
    @ResponseStatus(CREATED)
    public List<String> createBrands(
            @RequestBody List<String> brands
    ) {
        log.info("Creating brands {}", brands);
        try {
            return service.createBrands(brands);
        } catch (Exception e) {
            log.error("Error creating brands {}", brands, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }


    // ------------------------------ Delete ------------------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public BrandDTO deleteBrandById(
            @PathVariable("id") long id
    ) {
        log.info("Deleting brand by id {}", id);
        try {
            return service.deleteBrandById(id);
        } catch (NoSuchElementException e1) {
            log.error("Brand with id {} not found", id);
            throw new ResponseStatusException(NOT_FOUND, "Brand with id " + id + " not found");
        } catch (Exception e) {
            log.error("Error deleting brand by id {}", id, e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }
}
