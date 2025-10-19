package org.example.bankingManagementApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bankingManagementApplication.constatnt.AppTables;
import org.example.bankingManagementApplication.enums.RoleType;
import org.example.bankingManagementApplication.model.AuditModel;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = AppTables.ROLE_NAME)
public class Role extends AuditModel<String> {

    @Column(name = AppTables.RoleTable.ROLE_NAME)
    private String roleName;

    @Column(name = AppTables.RoleTable.ROLE_TYPE)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @ManyToMany(fetch = FetchType.EAGER , cascade =  CascadeType.DETACH)
    @JoinTable(
            name = AppTables.ROLE_PRIVILEGE_NAME,
            joinColumns = @JoinColumn(name = AppTables.RoleTable.ROLE_ID),
            inverseJoinColumns = @JoinColumn(name = AppTables.PrivilegeTable. PRIVILEGE_ID)
    )
    private Collection<Privilege> privileges;
}
