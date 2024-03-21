package ru.readles.readlesshop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.UserGetDTO;
import ru.readles.readlesshop.DTO.UserRegisterDTO;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.RightsMismatchUsersException;
import ru.readles.readlesshop.exception.UserAlreadyException;
import ru.readles.readlesshop.repository.UsersRepository;
import ru.readles.readlesshop.utils.JwtUtils;
import ru.readles.readlesshop.utils.MappingToDtoUtils;
import ru.readles.readlesshop.utils.MappingToEntityUtils;

/**
 * Здесь реализуются все методы UsersController
 */
@Service
@AllArgsConstructor
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MappingToEntityUtils mappingToEntityUtils;
    @Autowired
    MappingToDtoUtils mappingToDtoUtils;

    /**
     * Регистрация
     *
     * @param userRegisterDTO
     * @return userRepository.save(userRegisterDTO)
     * @throws UserAlreadyException
     */
    public UsersEntity registration(UserRegisterDTO userRegisterDTO) throws UserAlreadyException {
        if (usersRepository.findByLogin(userRegisterDTO.getLogin()) != null)
            throw new UserAlreadyException("Пользователь с таким логином уже существует!");
        return usersRepository.save(mappingToEntityUtils.mapToUsersEntityRegister(userRegisterDTO));
    }

    /**
     * Получение информаци по пользователю.
     * Пользователь может получить полную информацию о себе и не может получить информацию о других пользователях.
     * Админ может посмотреть информацию о любых пользователях.
     *
     * @param login, jwtToken
     * @return info of user
     */
    public UserGetDTO getUser(String login, String token) throws RightsMismatchUsersException {
        String jwtToken = token.substring(7);
        String jwtLogin = jwtUtils.getLogin(jwtToken);
        String jwtRole = String.valueOf(jwtUtils.getRoles(jwtToken));
        if (jwtLogin.equals(login) || jwtRole.equals("[ADMIN]")) {
            UsersEntity usersEntity = usersRepository.findByLogin(login);
            if (usersEntity == null) {
                throw new RightsMismatchUsersException("Пользователь с таким логином не найден");
            }
            return mappingToDtoUtils.mapToUserGetDto(usersEntity);
        } else {
            throw new RightsMismatchUsersException("недостаточно прав");
        }
    }

//    public UserProfileDTO putProfile(String login, UserProfileDTO userProfileDTO, String token) {
//    }
}
