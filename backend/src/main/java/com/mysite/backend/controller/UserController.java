package com.mysite.backend.controller;

import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor//생성자 주입
public class UserController {

    private final UserRepository userRepository;

    //user로 요청할 때 requestbody에 담아서 보낸다
    //그리고 그 데이터를 userRepository에 저장한다.(db)
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser); //저장하고 리턴되는 유저를 리턴함

    }

}
