package com.example.springmvc.basic.request;

import com.example.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);

        response.getWriter().write("OK");
    }
    //다른 방법
    @ResponseBody // "OK" 를 뷰로 인식하지 못하게 하려고
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String mamberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", mamberName, memberAge);
        return "OK";
    }
    //2번을 개선
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //받는 파라미터와 변수명을 똑같이 해주면 됨
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }
    //3번을 개선
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ //받는 파라미터와 변수명을 똑같이 해주면 됨
        log.info("username={}, age={}", username, age);
        return "OK";
    }
    //필수 파라미터 세팅
    //3번을 개선
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired( //받는 파라미터와 변수명을 똑같이 해주면 됨
        @RequestParam(required = true) String username,
        @RequestParam(required = false, defaultValue = "28") int age){ //int형은 null이 안들어가니깐 객체형인 integer를 넣는다.
        log.info("username={}, age={}", username, age);
        return "OK";
    }
    //모든 파라미터 다 가져오기
    @ResponseBody
    @RequestMapping("request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
       log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
       return "OK";
    }

    //전달받은 파라미터 -> 객체 생성 -> 객체에 파라미터 넣기 (자동화 @ModelAttribute)
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) { //@ModelAttribute  생략가능
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }
}
