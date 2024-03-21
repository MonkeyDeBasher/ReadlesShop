package ru.readles.readlesshop.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.UserGetDTO;
import ru.readles.readlesshop.entity.UsersEntity;

/**
 * Реализация мапинга Entity To DTO
 */
@Service
@AllArgsConstructor
public class MappingToDtoUtils {
    public UserGetDTO mapToUserGetDto(UsersEntity usersEntity){
        UserGetDTO userGetDTO = new UserGetDTO();
        userGetDTO.setLogin(usersEntity.getLogin());
        userGetDTO.setEmail(usersEntity.getEmail());
        userGetDTO.setRole(usersEntity.getRole());
        return userGetDTO;
    }

}
