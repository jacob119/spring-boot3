package com.example.spring3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeavyJobController {

    @GetMapping("/heavy-job")
    public String startHeavyJob() throws InterruptedException {
        System.out.println("작업이 시작 되었습니다.");
        Thread.sleep(20_000);
        return "작업이 완료되었습니다! :)";
    }
}
