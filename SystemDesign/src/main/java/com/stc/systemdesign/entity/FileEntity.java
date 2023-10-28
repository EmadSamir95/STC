package com.stc.systemdesign.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "bytes")
    private byte[] binary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

}
