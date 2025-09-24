package org.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.firstproject.constatnt.AppTables;
import org.example.firstproject.model.AuditModel;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = AppTables.USER_NAME)
public class User extends AuditModel<String> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private String name;
    private String password;
    private String email;

}
