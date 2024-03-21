package ru.readles.readlesshop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.RatingBookEntity;
import ru.readles.readlesshop.entity.UsersEntity;

public interface RatingBookRepository extends CrudRepository<RatingBookEntity, Long> {
    RatingBookEntity findByUserAndBook(UsersEntity user, BooksEntity book);
    @Query(value = "SELECT COUNT(*) FROM rating_book WHERE id_book=:bookId", nativeQuery = true)
    Long countByBook(@Param("bookId") Long bookId);
}
