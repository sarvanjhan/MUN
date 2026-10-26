package com.mun.mundelegation.service;

import com.mun.mundelegation.model.Delegate;
import com.mun.mundelegation.repository.DelegateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegateService {

    private final DelegateRepository repository;

    public DelegateService(DelegateRepository repository) {
        this.repository = repository;
    }

    public List<Delegate> getAllDelegates() {
        return repository.findAll();
    }

    public Delegate getDelegate(Long id) {
        return repository.findById(id);
    }

    public void registerDelegate(Delegate delegate) {
        repository.save(delegate);
    }

    public void updateDelegate(Delegate delegate) {
        repository.update(delegate);
    }

    public void deleteDelegate(Long id) {
        repository.deleteById(id);
    }
}
