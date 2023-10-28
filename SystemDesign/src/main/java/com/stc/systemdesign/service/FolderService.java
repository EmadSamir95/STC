package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.FolderDTO;
import com.stc.systemdesign.entity.ItemEntity;
import com.stc.systemdesign.entity.PermissionGroupEntity;
import com.stc.systemdesign.entity.Type;
import com.stc.systemdesign.exception.PermissionGroupNotFoundException;
import com.stc.systemdesign.repository.ItemRepository;
import com.stc.systemdesign.repository.PermissionGroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderService implements IFolderService{

    private final PermissionGroupRepo permissionGroupRepo;
    private final ItemRepository itemRepository;

    @Override
    public void create(FolderDTO dto) {
        PermissionGroupEntity permissionGroup = getPermissionGroup(dto.getPermissionGroup());
        ItemEntity item = createItem(dto, permissionGroup);
        saveItem(item);
    }

    private ItemEntity createItem(FolderDTO dto, PermissionGroupEntity permissionGroup){
        return ItemEntity.builder()
                .type(Type.valueOf(dto.getType()))
                .name(dto.getName())
                .permissionGroupEntity(permissionGroup)
                .build();
    }

    private PermissionGroupEntity getPermissionGroup(String name){
        return permissionGroupRepo.findByNameIgnoreCase(name)
                .orElseThrow(() -> new PermissionGroupNotFoundException("no permission group is found with name " + name));
    }

    private void saveItem(ItemEntity item){
        itemRepository.save(item);
    }
}
