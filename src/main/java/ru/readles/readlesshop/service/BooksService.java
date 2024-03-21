package ru.readles.readlesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.BookDTO;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.CommentEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.model.Books;
import ru.readles.readlesshop.model.Comment;
import ru.readles.readlesshop.repository.BooksRepository;
import ru.readles.readlesshop.utils.MappingToDtoUtils;
import ru.readles.readlesshop.utils.MappingToEntityUtils;

import java.util.List;
import java.util.Optional;


@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    MappingToEntityUtils mappingToEntityUtils;
    @Autowired
    MappingToDtoUtils mappingToDtoUtils;

    public BooksEntity addBooks(BookDTO bookDTO) {
        return booksRepository.save(mappingToEntityUtils.mapToBookEntityAddBook(bookDTO));
    }

    /**
     * Зацикленный вызов данных
     */
    public BooksEntity getBook(Long id) {
        Optional<BooksEntity> optionalBooks = booksRepository.findById(id);
        if (optionalBooks.isPresent()) {
            return optionalBooks.get();
        } else {
            return null;
        }
    }

    public BooksEntity putBooks(BookDTO bookDTO, Long id) throws BookNotFoundException {
                 Optional<BooksEntity> booksEntity = booksRepository.findById(id);
        if (booksEntity.isPresent()) {
            return booksRepository.save(mappingToEntityUtils.mapToBookEntityUpdate(booksEntity.get(), bookDTO, id));
        } else {
            throw new BookNotFoundException("Такой книги не существует!");
        }
    }
}