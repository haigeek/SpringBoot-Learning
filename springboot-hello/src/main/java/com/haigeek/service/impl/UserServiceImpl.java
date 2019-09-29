package com.haigeek.service.impl;

import com.haigeek.dao.UserRepository;
import com.haigeek.exception.IllegalParameterException;
import com.haigeek.exception.UnRecordedException;
import com.haigeek.model.dto.UserDTO;
import com.haigeek.model.dto.UserLoginDTO;
import com.haigeek.model.entity.User;
import com.haigeek.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author haigeek
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    Mapper mapper;

    @Override
    public void create(UserDTO userDTO) {

        User user=mapper.map(userDTO,User.class);
        userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }



    @Override
    public User validate(UserLoginDTO userLoginDTO) throws Exception{
        User user=userRepository.findByName(userLoginDTO.getName());
        if (user == null){
            throw new UnRecordedException("用户不存在");
        }
        if(user.getPwd().equals(userLoginDTO.getPwd())){
            return user;
        }else {
            throw new IllegalParameterException("密码错误");
        }
    }
}
