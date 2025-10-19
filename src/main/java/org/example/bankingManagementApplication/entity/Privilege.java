package org.example.bankingManagementApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.bankingManagementApplication.constatnt.AppTables;
import org.example.bankingManagementApplication.model.AuditModel;

@Entity
@Table(name = AppTables.PRIVILEGE_NAME)
@Getter
@Setter
public class Privilege extends AuditModel<String> {

    @Column(name = AppTables.PrivilegeTable.NAME)
    private String privilegeName;

    @Column(name = AppTables.PrivilegeTable.DESC_NAME)
    private String description;
}
