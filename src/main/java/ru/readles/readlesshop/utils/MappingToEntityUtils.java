package ru.readles.readlesshop.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.BookDTO;
import ru.readles.readlesshop.DTO.CommentDTO;
import ru.readles.readlesshop.DTO.UserRegisterDTO;

import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.CommentEntity;
import ru.readles.readlesshop.entity.UsersEntity;

/**
 * Реализация мапинга DTO to Entity
 */
@Service
@AllArgsConstructor
public class MappingToEntityUtils {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param dto
     * @return userEntity
     */
    public UsersEntity mapToUsersEntityRegister(UserRegisterDTO dto){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(dto.getLogin());
        usersEntity.setEmail(dto.getEmail());
        usersEntity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return usersEntity;
    }

    public BooksEntity mapToBookEntityAddBook(BookDTO bookDTO) {
        BooksEntity booksEntity = new BooksEntity();
        booksEntity.setTitle(bookDTO.getTitle());
        booksEntity.setAuthor(bookDTO.getAuthor());
        booksEntity.setDescription(bookDTO.getDescription());
        booksEntity.setCategory(bookDTO.getCategory());
        booksEntity.setPrice(bookDTO.getPrice());
        booksEntity.setUrlImg(bookDTO.getUrlImg());
        return booksEntity;
    }
    public  BooksEntity mapToBookEntityUpdate(BooksEntity booksEntity, BookDTO bookDTO, Long id){
        booksEntity.setAuthor(bookDTO.getAuthor());
        booksEntity.setCategory(bookDTO.getCategory());
        booksEntity.setTitle(bookDTO.getTitle());
        booksEntity.setDescription(bookDTO.getDescription());
        booksEntity.setUrlImg(bookDTO.getUrlImg());
        booksEntity.setPrice(bookDTO.getPrice());
 return booksEntity;
    }
    public CommentEntity mapToComEntityCreateCom(CommentDTO commentDTO, Long id_user, Long id_book) {
        CommentEntity commentEntity = new CommentEntity();
        UsersEntity usersEntity = new UsersEntity();
        BooksEntity booksEntity = new BooksEntity();
        usersEntity.setId_user(id_user);
        booksEntity.setId_book(id_book);
        commentEntity.setUser(usersEntity);
        commentEntity.setBook(booksEntity);
        commentEntity.setDescription(commentDTO.getDescription());
        return commentEntity;
    }
}
