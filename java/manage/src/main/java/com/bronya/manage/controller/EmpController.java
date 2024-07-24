package com.bronya.manage.controller;

import com.bronya.manage.pojo.Emp;
import com.bronya.manage.pojo.Result;
import com.bronya.manage.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    private EmpService empService;

    @Autowired
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public Result getEmpList(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int pageSize,
                             String name,
                             Short gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("page={} pageSize={} name={} gender={} begin={} end={}", page, pageSize, name, gender, begin, end);
        List<Emp> empList = empService.getEmpList(page, pageSize, name, gender, begin, end);
        return Result.success(empList);
    }

    @DeleteMapping("/{idList}")
    public Result deleteEmpList(@PathVariable int[] idList) { // List<Integer> idList
        log.info("idList={}", idList);
        int rowCount = empService.deleteEmpList(idList);
        return Result.success(rowCount);
    }

    @PostMapping
    public Result insertEmp(@RequestBody Emp emp) {
        log.info("emp={}", emp);
        int rowCount = empService.insertEmp(emp);
        return Result.success(rowCount);
    }

    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable int id) {
        log.info("id={}", id);
        Emp emp = empService.getEmpById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("emp={}", emp);
        int rowCount = empService.updateEmp(emp);
        return Result.success(rowCount);
    }
}