package ru.readles.readlesshop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.RatingBookEntity;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.RatingException;
import ru.readles.readlesshop.repository.BooksRepository;
import ru.readles.readlesshop.repository.RatingBookRepository;
import ru.readles.readlesshop.repository.UsersRepository;
import ru.readles.readlesshop.utils.JwtUtils;

import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private RatingBookRepository ratingBookRepository;
    @Autowired
    JwtUtils jwtUtils;

    /**
     * @param bookId
     * @param token
     * @return сущность
     * @throws RatingException
     */
    @Transactional
    public String addRating(Long bookId, String token) throws RatingException {
        BooksEntity book = booksRepository.findById(bookId).orElseThrow(() -> new RatingException("Книга не найдена"));
        String jwtToken = token.substring(7);
        Long jwtUserId = jwtUtils.getUserId(jwtToken);
        UsersEntity user = usersRepository.findById(jwtUserId).orElseThrow(() -> new RatingException("Пользователь не найден"));
    Optional<RatingBookEntity> ratingBookEntity = Optional.ofNullable(ratingBookRepository.findByUserAndBook(user, book));
      if(!ratingBookEntity.isPresent()) {
          int score = 1;
            RatingBookEntity entity = new RatingBookEntity();
            entity.setUser(user);
            entity.setBook(book);
            entity.setVote(Long.valueOf(score));
          ratingBookRepository.save(entity);
        return "+";
       }
      else {
           ratingBookRepository.deleteById(ratingBookEntity.get().getId_vote());
          return "-";
      }
    }

 public Long getRatingBook(Long bookId) throws RatingException {
     RatingBookEntity ratingBookEntity = ratingBookRepository.findById(bookId).orElseThrow(() -> new RatingException("Книга не найдена"));
      return ratingBookRepository.countByBook(bookId);
    }
}
