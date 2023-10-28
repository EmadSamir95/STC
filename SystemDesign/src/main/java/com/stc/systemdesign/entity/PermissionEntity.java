package com.stc.systemdesign.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permission")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "permission_level")
    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private PermissionGroupEntity permissionGroup;

}
