package ru.readles.readlesshop.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.config.JwtRequestFilter;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.UserAlreadyException;
import ru.readles.readlesshop.exception.UserNotFoundException;
import ru.readles.readlesshop.model.User;
import ru.readles.readlesshop.repository.UsersRepository;
import ru.readles.readlesshop.utils.JwtUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, JwtUtils jwtUtils, JwtRequestFilter jwtRequestFilter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsersEntity registration(UsersEntity usersEntity) throws UserAlreadyException {
        if (usersRepository.findByLogin(usersEntity.getLogin()) != null) {
            throw new UserAlreadyException("Пользователь с таким логином уже существует!");
        }
        String role = usersEntity.getRole();
        if (Objects.equals(role, "USER")) {
            throw new UserAlreadyException("Тварь ли я дрожащая или право имею?");
        }
        usersEntity.setPassword(bCryptPasswordEncoder.encode(usersEntity.getPassword()));
        return usersRepository.save(usersEntity);
    }

    public User getOneUser(Long id_user) throws UserNotFoundException {
        UsersEntity user = usersRepository.findById(id_user).get();
        try {
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String jwt = authorizationHeader.substring(7);
                Long userId = jwtUtils.getUserId(jwt);
                String role = String.valueOf(jwtUtils.getRoles(jwt));
                System.out.println(role);
                if (user == null) {
                    throw new UserNotFoundException("Пользователь не найден!");
                }
                if (id_user!=userId&&!role.equals("[ADMIN]")) {
                    throw new UserNotFoundException("О чём думаешь?(Профиль не соответсвует аутифицированному пользователю)");
                }
            } else {
                System.out.println("JWT Token отсутствует в заголовке Authorization");
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        return User.toModel(user);
}
    public Long delete(Long id) {
        usersRepository.deleteById(id);
        return id;
    }
}
