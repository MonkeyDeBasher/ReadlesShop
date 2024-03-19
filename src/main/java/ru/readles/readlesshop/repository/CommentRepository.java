package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.CommentEntity;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс, который реализует запросы к таблице Comment
 */
@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findByBook(BooksEntity book);
}
