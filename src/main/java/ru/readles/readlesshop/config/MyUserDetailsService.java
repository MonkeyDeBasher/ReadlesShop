package ru.readles.readlesshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.repository.UserRepository;
import ru.readles.readlesshop.repository.UsersRepository;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UsersEntity> user = userRepository.findByLogin(login);
        return user.map(MyUserDetails::new).orElseThrow(()->new UsernameNotFoundException(login+" - не найден в БД"));
    }
}
