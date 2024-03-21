package ru.readles.readlesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.LibraryEntity;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.model.Comment;
import ru.readles.readlesshop.model.Library;
import ru.readles.readlesshop.repository.BooksRepository;
import ru.readles.readlesshop.repository.LibraryRepository;
import ru.readles.readlesshop.repository.UsersRepository;

import java.util.List;


@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BooksRepository booksRepository;
    public LibraryEntity createLib(Long id_user, Long id_book) throws BookNotFoundException {
        LibraryEntity libraryEntity = new LibraryEntity();
        UsersEntity user = usersRepository.findById(id_user).orElseThrow(() -> new BookNotFoundException("Пользователь не найден"));
        BooksEntity book = booksRepository.findById(id_book).orElseThrow(() -> new BookNotFoundException("Книга не найдена"));
      LibraryEntity existingLib = libraryRepository.findByUserAndBook(user, book);
        if (existingLib != null) {
            throw new BookNotFoundException("Книга уже присутствует в библиотеке этого пользователя");
        }
        LibraryEntity library = new LibraryEntity();
        library.setUser(user);
        library.setBook(book);
        libraryRepository.save(library);
        return libraryEntity;
    }
    public List<Library> selectBooksByUserId(Long userId) throws BookNotFoundException {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId_user(userId);
        List<LibraryEntity> libraryEntity = libraryRepository.findByUser(usersEntity);
        if (libraryEntity != null && !libraryEntity.isEmpty()) {
            List<Library> libraryModels = Library.toModel(libraryEntity);
            return libraryModels;
        } else {
            throw new BookNotFoundException("Комментарии для книги не найдены");
        }
    }
}
