package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.readles.readlesshop.entity.UsersEntity;

public interface UsersRepository extends CrudRepository<UsersEntity,Long> {
  UsersEntity findByLogin(String login);
    }
