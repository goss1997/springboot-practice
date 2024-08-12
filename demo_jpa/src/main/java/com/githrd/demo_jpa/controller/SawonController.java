package com.githrd.demo_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.githrd.demo_jpa.entity.Sawon;
import com.githrd.demo_jpa.repository.SawonRepository;

// @RestController = @Controller + @ResponseBody
@RestController 
public class SawonController {

    @Autowired
    SawonRepository sawonRepository;

    //전체조회
    @GetMapping("/sawons")
    public List<Sawon> selectList(){
        return sawonRepository.findAll();
    }

    //부서별조회
    @GetMapping("/sawons/{deptno}")
    public List<Sawon> selectListFromDeptno(@PathVariable int deptno){
        return sawonRepository.findAllByDeptno(deptno);
    }

    // Sort처리
    @GetMapping("/sawons-sortbysapaydesc")
    public List<Sawon> selectListSortBySapayDesc(){
        return sawonRepository.findAll(Sort.by("sapay").descending());
    }

    // page처리 : oracle 11 버전 이후에 지원.
    @GetMapping("/sawons/{page}/{count}")
    public List<Sawon> select(@PathVariable int page, @PathVariable int count) {
        // page는 0부터 시작
        Pageable pageable = PageRequest.of(page, count,Sort.by(Sort.Direction.DESC,"sabun"));

        Page<Sawon> pageSawon = sawonRepository.findAll(pageable);
        // Page<Sawon> --> List<Sawon> 으로 변환 : getContent()

        return pageSawon.getContent();
    }

}
