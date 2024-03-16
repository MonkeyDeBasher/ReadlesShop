package ru.readles.readlesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.CommentEntity;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.model.Comment;
import ru.readles.readlesshop.repository.BooksRepository;
import ru.readles.readlesshop.repository.CommentRepository;
import ru.readles.readlesshop.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BooksRepository booksRepository;
    public CommentEntity createCom(CommentEntity commentEntity, Long id_user, Long id_book) throws BookNotFoundException {
        UsersEntity user = usersRepository.findById(id_user).orElseThrow(() -> new BookNotFoundException("Пользователь не найден"));
        BooksEntity book = booksRepository.findById(id_book).orElseThrow(() -> new BookNotFoundException("Книга не найдена"));
        commentEntity.setUser(user);
        commentEntity.setBook(book);
        return commentRepository.save(commentEntity);
    }
    public List<Comment> getCom(Long id_book) throws BookNotFoundException {
        BooksEntity book = new BooksEntity();
        book.setId_book(id_book);
        List<CommentEntity> comments = commentRepository.findByBook(book);
        if (comments != null && !comments.isEmpty()) {
            List<Comment> commentModels = Comment.toModel(comments);
            return commentModels;
        } else {
            throw new BookNotFoundException("Комментарии для книги не найдены");
        }
    }


}
