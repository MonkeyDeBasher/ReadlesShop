package ru.readles.readlesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.LibraryEntity;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.repository.BooksRepository;
import ru.readles.readlesshop.repository.LibraryRepository;
import ru.readles.readlesshop.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BooksRepository booksRepository;
    public Optional<LibraryEntity> createLib(LibraryEntity libraryEntity, Long id_user, Long id_book) throws BookNotFoundException {
        UsersEntity user = usersRepository.findById(id_user).orElseThrow(() -> new BookNotFoundException("Пользователь не найден"));
        BooksEntity book = booksRepository.findById(id_book).orElseThrow(() -> new BookNotFoundException("Книга не найдена"));
        Optional<LibraryEntity> existingLib = libraryRepository.findByUserAndBook(user, book);
        if (existingLib.isPresent()) {
            throw new BookNotFoundException("Книга уже присутствует в библиотеке этого пользователя");
        }
        libraryEntity.setUser(user);
        libraryEntity.setBook(book);
        return Optional.of(libraryRepository.save(libraryEntity));
    }
    public List<BooksEntity> selectBooksByUserId(Long userId) throws BookNotFoundException {
        UsersEntity user = usersRepository.findById(userId).orElseThrow(() -> new BookNotFoundException("Пользователь не найден"));
        List<LibraryEntity> libraryEntries = new ArrayList<>();
        libraryRepository.findAll().forEach(libraryEntries::add);

        List<BooksEntity> books = new ArrayList<>();
        for (LibraryEntity entry : libraryEntries) {
            if (entry.getUser().equals(user)) {
                books.add(entry.getBook());
            }
        }
        return books;
    }
}
