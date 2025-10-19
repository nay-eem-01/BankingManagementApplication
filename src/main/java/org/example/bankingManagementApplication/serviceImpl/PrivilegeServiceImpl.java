package org.example.bankingManagementApplication.serviceImpl;

import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.repository.PrivilegeRepository;
import org.example.bankingManagementApplication.service.PrivilegeService;
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
