package ru.readles.readlesshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.readles.readlesshop.entity.BooksEntity;


public interface BooksRepository extends CrudRepository<BooksEntity,Long> {

}
