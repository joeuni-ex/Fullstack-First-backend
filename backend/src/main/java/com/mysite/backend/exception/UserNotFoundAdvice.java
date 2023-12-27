package com.mysite.backend.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
//모든 컨트롤러에 적용한다.
@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody//문자열 데이터를 리턴할 수 있도록
    //특정 예외가 생겼을 때 만 발생
    @ExceptionHandler(UserNotFoundException.class)//UserNotFoundException 발생 시
    @ResponseStatus(HttpStatus.NOT_FOUND)//상태 코드 NOT FOUND로 설정
    public Map<String,String> exceptionHandler(UserNotFoundException exception){
        
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());

        return errorMap;
    }
}
