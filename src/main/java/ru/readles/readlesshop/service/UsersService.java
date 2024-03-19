package ru.readles.readlesshop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.UserRegisterDTO;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.UserAlreadyException;
import ru.readles.readlesshop.repository.UsersRepository;
import ru.readles.readlesshop.utils.JwtUtils;
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
    MappingToEntityUtils mappingUtils;

    /**
     * Регистрация
     * @param userRegisterDTO
     * @return userRepository.save(userRegisterDTO)
     * @throws UserAlreadyException
     */
    public UsersEntity registration(UserRegisterDTO userRegisterDTO) throws UserAlreadyException {
        if (usersRepository.findByLogin(userRegisterDTO.getLogin()) != null) {
            throw new UserAlreadyException("Пользователь с таким логином уже существует!");
        }
        return usersRepository.save(mappingUtils.mapToUsersEntity(userRegisterDTO));
    }

//    public User getOneUser(Long id_user) throws UserNotFoundException {
//        UsersEntity user = usersRepository.findById(id_user).get();
//        try {
//            String authorizationHeader = request.getHeader("Authorization");
//            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//                String jwt = authorizationHeader.substring(7);
//                Long userId = jwtUtils.getUserId(jwt);
//                String role = String.valueOf(jwtUtils.getRoles(jwt));
//                if (user == null) {
//                    throw new UserNotFoundException("Пользователь не найден!");
//                }
//                if (id_user!=userId&&!role.equals("[ADMIN]")) {
//                    System.out.println("Роль не соответсвует запрашиваемову ресурсу");
//                    throw new UserNotFoundException("О чём думаешь, пупсик?(Профиль не соответсвует аутифицированному пользователю)");
//                }
//            } else {
//                System.out.println("JWT Token отсутствует в заголовке Authorization");
//            }
//        } catch (UserNotFoundException e) {
//            throw new UserNotFoundException("Пользователь не найден!");
//        }
//        return User.toModel(user);
//}
    public Long delete(Long id) {
        usersRepository.deleteById(id);
        return id;
    }
}
