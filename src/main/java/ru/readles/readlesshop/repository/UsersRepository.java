package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readles.readlesshop.entity.UsersEntity;
@Repository
public interface UsersRepository extends CrudRepository<UsersEntity,Long> {
  UsersEntity findByLogin(String login);
    }
