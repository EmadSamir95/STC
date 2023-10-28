package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.SpaceDTO;
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
public class SpaceService implements ISpaceService{

    private final PermissionGroupRepo permissionGroupRepo;
    private final ItemRepository itemRepository;

    @Override
    public void create(SpaceDTO dto) {
        PermissionGroupEntity permissionGroup = getPermissionGroup(dto.getPermissionGroup());
        ItemEntity item = createItem(dto, permissionGroup);
        saveItem(item);
    }

    private PermissionGroupEntity getPermissionGroup(String name){
        return permissionGroupRepo.findByNameIgnoreCase(name)
                .orElseThrow(() -> new PermissionGroupNotFoundException("no permission group is found with name " + name));
    }

    private ItemEntity createItem(SpaceDTO dto, PermissionGroupEntity permissionGroup){
        return ItemEntity.builder()
                .name(dto.getName())
                .type(Type.valueOf(dto.getType()))
                .permissionGroupEntity(permissionGroup)
                .build();
    }

    private void saveItem(ItemEntity item){
        itemRepository.save(item);
    }

}
