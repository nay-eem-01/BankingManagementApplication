package org.example.firstproject.serviceImpl;

import org.example.firstproject.entity.Privilege;
import org.example.firstproject.repository.PrivilegeRepository;
import org.example.firstproject.service.PrivilegeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public List<Privilege> getAllPrivilege() {
        return privilegeRepository.findAll();
    }
}
