package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readles.readlesshop.entity.BooksEntity;

@Repository
public interface BooksRepository extends CrudRepository<BooksEntity,Long> {

}
