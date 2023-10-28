package com.stc.systemdesign.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id")
    private PermissionGroupEntity permissionGroupEntity;

}
