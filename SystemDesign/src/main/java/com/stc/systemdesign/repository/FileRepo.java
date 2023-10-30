package com.stc.systemdesign.repository;

import com.stc.systemdesign.entity.FileEntity;
import com.stc.systemdesign.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FileRepo extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByItemName(String name);

    @Query(value = "select \"name\", \"permission_group_id\" from public.item where \"type\" = :type and \"name\" = :name", nativeQuery = true)
    FileEntity getFileMetaData(@Param("type") Type type, @Param("name") String name);
}
