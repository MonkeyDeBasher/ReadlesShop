package ru.readles.readlesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.UserAlreadyException;
import ru.readles.readlesshop.exception.UserNotFoundException;
import ru.readles.readlesshop.model.User;
import ru.readles.readlesshop.repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity registration(UsersEntity usersEntity) throws UserAlreadyException {
            if (usersRepository.findByLogin(usersEntity.getLogin()) != null) {
                throw new UserAlreadyException("Пользователь с таким логином уже существует!");
            }
        String role = usersEntity.getRole();
        if(role!=null && !role.equals("user")){
            throw new UserAlreadyException("Тварь ли я дрожащая или право имею?");
        }
        return usersRepository.save(usersEntity);
    }
    public User getOneUser(Long id_user) throws UserNotFoundException {
        UsersEntity user = usersRepository.findById(id_user).get();
        if(user==null){
            throw new UserNotFoundException("Пользователь не найден!");
        }
        return User.toModel(user);
    }
    public Long delete(Long id) {
        usersRepository.deleteById(id);
        return id;
    }
}
