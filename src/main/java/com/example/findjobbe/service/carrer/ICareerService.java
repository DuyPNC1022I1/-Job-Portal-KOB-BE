package com.example.findjobbe.service.carrer;

import com.example.findjobbe.model.Career;
import com.example.findjobbe.service.ICoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ICareerService implements ICoreService<Career> {
    @Override
    public Page<Career> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Career findOne(Long id) {
        return null;
    }

    @Override
    public void save(Career career) {

    }

    @Override
    public void delete(Long id) {

    }
}
