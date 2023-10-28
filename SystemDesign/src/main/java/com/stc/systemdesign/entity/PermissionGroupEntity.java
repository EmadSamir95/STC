package com.stc.systemdesign.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Permission;

@Entity
@Table(name = "permission_group")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PermissionGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
