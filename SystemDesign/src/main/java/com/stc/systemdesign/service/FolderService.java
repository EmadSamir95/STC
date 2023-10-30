package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.FolderDTO;
import com.stc.systemdesign.entity.*;
import com.stc.systemdesign.exception.SpaceNotFoundException;
import com.stc.systemdesign.exception.UnAuthorizedException;
import com.stc.systemdesign.exception.UserNameNotFoundException;
import com.stc.systemdesign.repository.ItemRepository;
import com.stc.systemdesign.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderService implements IFolderService{

    private final ItemRepository itemRepository;
    private final UserRepo userRepo;

    @Override
    public void create(FolderDTO dto, Authentication authentication) {
        UserEntity user = getUser(authentication.getName());
        ItemEntity space = getItem(dto.getSpaceName());
        if(!space.getPermissionGroupEntity().equals(user.getPermission().getPermissionGroup()) ||
            !user.getPermission().getPermissionLevel().equals(PermissionLevel.EDIT))
            throw new UnAuthorizedException("user is un-authorized to create a folder under the space " + space.getName());
        ItemEntity item = createItem(dto, space.getPermissionGroupEntity());
        saveItem(item);
    }

    private ItemEntity createItem(FolderDTO dto, PermissionGroupEntity permissionGroup){
        return ItemEntity.builder()
                .type(Type.valueOf(dto.getType()))
                .name(dto.getName())
                .permissionGroupEntity(permissionGroup)
                .build();
    }

    private void saveItem(ItemEntity item){
        itemRepository.save(item);
    }

    private UserEntity getUser(String username){
        return userRepo.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserNameNotFoundException("no match found for username " + username));
    }

    private ItemEntity getItem(String name){
        return itemRepository.findByNameIgnoreCaseAndType(name, Type.Space).orElseThrow(() -> new SpaceNotFoundException("no space found with name " + name));
    }
}
