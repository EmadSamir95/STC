package com.stc.systemdesign.repository;

import com.stc.systemdesign.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileEntity, Long> {
}
