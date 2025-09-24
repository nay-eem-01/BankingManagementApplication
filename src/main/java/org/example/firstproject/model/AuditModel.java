package org.example.firstproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.example.firstproject.constatnt.AppTables;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditModel<U> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AppTables.AuditModelTable.ID)
    private  Long id;

    @CreatedBy
    @Column(name = AppTables.AuditModelTable.CREATED_BY)
    private U createdBy;

    @LastModifiedBy
    @Column(name = AppTables.AuditModelTable.LAST_MODIFIED_BY)
    private U lastModifiedBy;

    @CreatedDate
    @Column(name = AppTables.AuditModelTable.CREATION_DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = AppTables.AuditModelTable.LAST_MODIFIED_DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastModifiedDate;
}
