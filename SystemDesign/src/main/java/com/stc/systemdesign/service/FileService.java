package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.FileDTO;
import com.stc.systemdesign.entity.FileEntity;
import com.stc.systemdesign.entity.ItemEntity;
import com.stc.systemdesign.entity.PermissionGroupEntity;
import com.stc.systemdesign.entity.Type;
import com.stc.systemdesign.exception.PermissionGroupNotFoundException;
import com.stc.systemdesign.repository.FileRepo;
import com.stc.systemdesign.repository.ItemRepository;
import com.stc.systemdesign.repository.PermissionGroupRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService{


    private final PermissionGroupRepo permissionGroupRepo;
    private final ItemRepository itemRepository;
    private final FileRepo fileRepo;

    @Override
    @Transactional
    public void create(FileDTO dto, MultipartFile binary) throws IOException {
        PermissionGroupEntity permissionGroup = getPermissionGroup(dto.getPermissionGroup());
        ItemEntity item = createItem(dto, permissionGroup);
        FileEntity file = createFile(binary, item);
        saveItem(item);
        saveFile(file);
    }

    private ItemEntity createItem(FileDTO dto, PermissionGroupEntity permissionGroup){
        return ItemEntity.builder()
                .type(Type.valueOf(dto.getType()))
                .name(dto.getName())
                .permissionGroupEntity(permissionGroup)
                .build();
    }

    private FileEntity createFile(MultipartFile file, ItemEntity item) throws IOException {
        return FileEntity.builder()
                .binary(file.getBytes())
                .item(item)
                .build();
    }

    private PermissionGroupEntity getPermissionGroup(String name){
        return permissionGroupRepo.findByNameIgnoreCase(name)
                .orElseThrow(() -> new PermissionGroupNotFoundException("no permission group is found with name " + name));
    }

    private void saveItem(ItemEntity item){
        itemRepository.save(item);
    }

    private void saveFile(FileEntity file){
        fileRepo.save(file);
    }

}
