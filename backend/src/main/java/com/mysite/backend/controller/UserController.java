package com.mysite.backend.controller;

import com.mysite.backend.exception.UserNotFoundException;
import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor//생성자 주입
@CrossOrigin("*")
public class UserController {

    private final UserRepository userRepository;

    //user로 요청할 때 requestbody에 담아서 보낸다
    //그리고 그 데이터를 userRepository에 저장한다.(db)
    @PostMapping("/users")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser); //저장하고 리턴되는 유저를 리턴함

    }

    //모든 유저 정보 가져오기
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id){
        //유저가 없을 경우 예외처리
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

}
