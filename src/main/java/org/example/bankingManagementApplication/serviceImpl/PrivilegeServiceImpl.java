package org.example.bankingManagementApplication.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.repository.PrivilegeRepository;
import org.example.bankingManagementApplication.service.PrivilegeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    @Override
    public List<Privilege> getAllPrivilege() {
        return privilegeRepository.findAll();
    }

    @Override
    public Privilege createPrivilege(String key, String value) {
        Privilege privilege = new Privilege();
        privilege.setPrivilegeName(key);
        privilege.setDescription(value);
        return privilegeRepository.save(privilege);
    }

    @Override
    public Privilege findByPrivilegeName(String privilegeName) {
        return privilegeRepository.findByPrivilegeName(privilegeName).orElse(null);
    }

    @Override
    public Privilege findByPrivilegeId(Long id) {
        return privilegeRepository.findById(id).orElse(null);
    }
}
