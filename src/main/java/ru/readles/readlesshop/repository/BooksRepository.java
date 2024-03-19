package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readles.readlesshop.entity.BooksEntity;

/**
 * Интерфейс, который реализует запросы к таблице Book
 */
@Repository
public interface BooksRepository extends CrudRepository<BooksEntity,Long> {

}
