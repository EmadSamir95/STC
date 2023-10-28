package com.stc.systemdesign.repository;

import com.stc.systemdesign.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
