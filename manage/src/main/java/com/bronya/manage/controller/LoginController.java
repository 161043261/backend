package com.bronya.manage.controller;

import com.bronya.manage.aop.demoJoinPoint;
import com.bronya.manage.pojo.Emp;
import com.bronya.manage.pojo.Result;
import com.bronya.manage.service.EmpService;
import com.bronya.manage.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    private EmpService empService;

    @Autowired // inject by constructor
    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    @demoJoinPoint
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("emp={}", emp);
        Emp e = empService.selectEmpByUp(emp);
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jws = JwtUtils.genJwsString(claims);
            log.warn("jws={}", jws);
            return Result.success(jws);
        }
        return Result.error("username or password incorrect");
    }
}