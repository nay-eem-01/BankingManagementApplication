package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.entity.Role;

import java.util.List;

public interface PrivilegeService {

    List<Privilege> getAllPrivilege();

    Privilege createPrivilege(String key, String value);

    Privilege findByPrivilegeName(String privilegeName);

    Privilege findByPrivilegeId(Long id);
}
