package com.githrd.demo_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.githrd.demo_jpa.entity.Sawon;

public interface SawonRepository extends JpaRepository<Sawon,Integer>{

    //부서별조회  : JPQL or 일반SQL문 변수처리 :  :변수명  or  :1(위치값)
    //일반SQL
    //@Query(value="select * from sawon where deptno = :deptno", nativeQuery = true)

    //JPQL : nativeQuery = false 이거나 생략
    //       1.Table or 속성명은 Entity정보와 일치해야 한다
    //       2.Table Alias명을 사용해야된다
    @Query(value="select s from Sawon s where s.deptno = :deptno", nativeQuery = false)
    List<Sawon> findAllByDeptno(int deptno);

    

}
