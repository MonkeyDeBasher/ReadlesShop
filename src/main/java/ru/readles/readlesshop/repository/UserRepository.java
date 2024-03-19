package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.readles.readlesshop.entity.UsersEntity;

import java.util.Optional;
/**
 * Интерфейс, который реализует запросы к таблице Users
 * Создан только для MyUserDetailsService
 */
public interface UserRepository extends CrudRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByLogin(String login);
}
