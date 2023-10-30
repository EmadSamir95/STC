package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.FileDTO;
import com.stc.systemdesign.dto.FileMetaRespDTO;
import com.stc.systemdesign.entity.*;
import com.stc.systemdesign.exception.FileNotFoundException;
import com.stc.systemdesign.exception.SpaceNotFoundException;
import com.stc.systemdesign.exception.UnAuthorizedException;
import com.stc.systemdesign.exception.UserNameNotFoundException;
import com.stc.systemdesign.mapper.FileMapper;
import com.stc.systemdesign.repository.FileRepo;
import com.stc.systemdesign.repository.ItemRepository;
import com.stc.systemdesign.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService{


    private final ItemRepository itemRepository;
    private final FileMapper fileMapper;
    private final FileRepo fileRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public void create(FileDTO dto, MultipartFile binary, Authentication authentication) throws IOException {
        UserEntity user = getUser(authentication.getName());
        ItemEntity folder = getItem(dto.getFolderName());
        if(!user.getPermission().getPermissionGroup().equals(folder.getPermissionGroupEntity()) ||
            !user.getPermission().getPermissionLevel().equals(PermissionLevel.EDIT))
            throw new UnAuthorizedException("user is un-authorized to create file under the folder " + dto.getFolderName());
        ItemEntity item = createItem(dto, folder.getPermissionGroupEntity());
        FileEntity file = createFile(binary, item);
        saveItem(item);
        saveFile(file);
    }

    @Override
    public byte[] download(String filename, Authentication authentication) {
        UserEntity user = getUser(authentication.getName());
        FileEntity file = getFile(filename);
        if(!user.getPermission().getPermissionGroup().equals(file.getItem().getPermissionGroupEntity()))
            throw new UnAuthorizedException("user has no authority to download this file " + filename);
        return file.getBinary();
    }

    @Override
    public FileMetaRespDTO view(String fileName, Authentication authentication) {
        UserEntity user = getUser(authentication.getName());
        FileEntity file = fileRepo.getFileMetaData(Type.File, fileName);
        if(!file.getItem().getPermissionGroupEntity().equals(user.getPermission().getPermissionGroup()))
            throw new UnAuthorizedException("user is not authorized to view file meta-data for file " + fileName);
        return fileMapper.fileToMetaDataResponse(file);
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

    private void saveItem(ItemEntity item){
        itemRepository.save(item);
    }

    private void saveFile(FileEntity file){
        fileRepo.save(file);
    }

    private UserEntity getUser(String username){
        return userRepo.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserNameNotFoundException("no match found for username " + username));
    }

    private ItemEntity getItem(String name){
        return itemRepository.findByNameIgnoreCaseAndType(name, Type.Folder).orElseThrow(() -> new SpaceNotFoundException("no space found with name " + name));
    }

    private FileEntity getFile(String name){
        return fileRepo.findByItemName(name).orElseThrow(() -> new FileNotFoundException("no file found with name " + name));
    }

}
