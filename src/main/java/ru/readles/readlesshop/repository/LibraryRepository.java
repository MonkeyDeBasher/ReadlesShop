package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.LibraryEntity;
import ru.readles.readlesshop.entity.UsersEntity;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, который реализует запросы к таблице Library
 */
@Repository
public interface LibraryRepository extends CrudRepository<LibraryEntity, Long> {
    LibraryEntity findByUserAndBook(UsersEntity users, BooksEntity books);
    List<LibraryEntity> findByUser(UsersEntity user);
}
