package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.LibraryEntity;
import ru.readles.readlesshop.entity.UsersEntity;

import java.util.Optional;

public interface LibraryRepository extends CrudRepository<LibraryEntity, Long> {
    Optional<LibraryEntity> findByUserAndBook(UsersEntity user, BooksEntity book);

}
