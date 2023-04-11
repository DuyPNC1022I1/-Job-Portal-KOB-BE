package com.example.findjobbe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICoreService<E> {
    Page<E> findAll(Pageable pageable);
    E findOne(Long id);
    void save(E e);
    void delete(Long id);
}
