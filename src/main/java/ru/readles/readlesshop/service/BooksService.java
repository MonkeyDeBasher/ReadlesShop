package ru.readles.readlesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.repository.BooksRepository;

import java.util.Optional;


@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    public BooksEntity addBooks(BooksEntity booksEntity) {
        return booksRepository.save(booksEntity);
    }

    public BooksEntity getBook(Long id) {
        BooksEntity books = booksRepository.findById(id).get();
        return books;
    }

    public Long deleteBook(Long id) {
        booksRepository.deleteById(id);
        return id;
    }

    public BooksEntity putBook(BooksEntity booksEntity) {
        return booksRepository.save(booksEntity);
    }

    public BooksEntity updateBooks(BooksEntity booksEntityUpdate) throws BookNotFoundException {
        Optional<BooksEntity> optionalUpdateEntity = booksRepository.findById(booksEntityUpdate.getId_book());

        if (optionalUpdateEntity.isPresent()) {
            BooksEntity existingEntity = optionalUpdateEntity.get();
            existingEntity.setAuthor(booksEntityUpdate.getAuthor());
            existingEntity.setCategory(booksEntityUpdate.getCategory());
            existingEntity.setTitle(booksEntityUpdate.getTitle());
            existingEntity.setDescription(booksEntityUpdate.getDescription());
            existingEntity.setUrlImg(booksEntityUpdate.getUrlImg());
            existingEntity.setPrice(booksEntityUpdate.getPrice());

            return booksRepository.save(existingEntity);
        } else {
            throw new BookNotFoundException("Такой книги не существует!");
        }
    }
}