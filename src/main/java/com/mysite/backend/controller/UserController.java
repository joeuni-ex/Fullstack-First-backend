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

    //유저 ID 조회 및 예외처리
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id){
        //유저가 없을 경우 예외처리
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    //유저 정보 수정
    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id).map(user->{//id로 유저를 찾음
            user.setUsername(newUser.getUsername());//유저 이름 저장
            user.setName((newUser.getName()));//이름 저장
            user.setEmail((newUser.getEmail()));//이메일 저장
            return userRepository.save(user);//유저 정보 DB에 저장
        }).orElseThrow(()-> new UserNotFoundException((id)));//예외 처리
    }

    //유저 삭제
    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){ //DB에 ID가 있으면 true,없으면 false
           throw new UserNotFoundException(id);//예외 처리
        }
        userRepository.deleteById(id);//DB에서 유저 삭제하기
        return "유저 아이디: "+id+"는 삭제 되었습니다.";
    }

    //유저 검색하기
    @GetMapping("/searchUser")
    List<User> searchUser(@RequestParam String search){
        return userRepository.findByNameContainsOrUsernameContains(search, search);
    }
}
