package ru.readles.readlesshop.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.UserRegisterDTO;

import ru.readles.readlesshop.entity.UsersEntity;

/**
 * Реализация мапинга DTO to Entity
 */
@Service
@AllArgsConstructor
public class MappingToEntityUtils {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param dto
     * @return userEntity
     */
    public UsersEntity mapToUsersEntity(UserRegisterDTO dto){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(dto.getLogin());
        usersEntity.setEmail(dto.getEmail());
        usersEntity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return usersEntity;
    }
}
