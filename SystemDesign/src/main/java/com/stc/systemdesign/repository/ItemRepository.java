package com.stc.systemdesign.repository;

import com.stc.systemdesign.entity.ItemEntity;
import com.stc.systemdesign.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByNameIgnoreCaseAndType(String name, Type type);
}
