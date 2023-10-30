package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.SpaceDTO;
import com.stc.systemdesign.entity.*;
import com.stc.systemdesign.exception.PermissionGroupNotFoundException;
import com.stc.systemdesign.exception.UnAuthorizedException;
import com.stc.systemdesign.exception.UserNameNotFoundException;
import com.stc.systemdesign.repository.ItemRepository;
import com.stc.systemdesign.repository.PermissionGroupRepo;
import com.stc.systemdesign.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceService implements ISpaceService{

    private final PermissionGroupRepo permissionGroupRepo;
    private final ItemRepository itemRepository;
    private final UserRepo userRepo;

    @Override
    public void create(SpaceDTO dto, Authentication authentication) {
        UserEntity user = getUser(authentication.getName());
        PermissionGroupEntity permissionGroup = getPermissionGroup(dto.getPermissionGroup());
        if(!user.getPermission().getPermissionLevel().equals(PermissionLevel.EDIT))
            throw new UnAuthorizedException("user is un-authorized to create a space");
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

    private UserEntity getUser(String username){
        return userRepo.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserNameNotFoundException("no match found for username " + username));
    }

}
