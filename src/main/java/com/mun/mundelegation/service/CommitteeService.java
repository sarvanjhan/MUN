package com.mun.mundelegation.service;

import com.mun.mundelegation.Committee;
import com.mun.mundelegation.repository.CommitteeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommitteeService {

    private final CommitteeRepository committeeRepository;

    public CommitteeService(CommitteeRepository committeeRepository) {
        this.committeeRepository = committeeRepository;
    }

    public List<Committee> getAllCommittees() {
        return committeeRepository.findAll();
    }

    public Committee getCommitteeById(Long id) {
        return committeeRepository.findById(id);
    }

    public void addCommittee(Committee committee) {
        committeeRepository.save(committee);
    }

    public void updateCommittee(Committee committee) {
        committeeRepository.update(committee);
    }

    public void deleteCommittee(Long id) {
        committeeRepository.deleteById(id);
    }
}

