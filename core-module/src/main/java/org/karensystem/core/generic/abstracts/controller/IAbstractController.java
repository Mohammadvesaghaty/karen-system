package org.karensystem.core.generic.abstracts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IAbstractController<T,Long> {

    @GetMapping({"/"} )
    ResponseEntity<?> getAll() ;

    @GetMapping(value = "/{id}")
    ResponseEntity<?> getById(@PathVariable Long id) ;

    @PostMapping("/")
    ResponseEntity<?> insert(@RequestBody T dto);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Long id,@RequestBody T dto) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id);




}
