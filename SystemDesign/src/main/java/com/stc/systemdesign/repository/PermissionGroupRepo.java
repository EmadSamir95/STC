package com.stc.systemdesign.repository;

import com.stc.systemdesign.entity.PermissionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionGroupRepo extends JpaRepository<PermissionGroupEntity, Long> {

    Optional<PermissionGroupEntity> findByNameIgnoreCase(String name);
}
