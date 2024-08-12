package com.githrd.demo_jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "gogek_view")
public class Sawon {

    @Id
    int sabun;

    String saname;
    String sasex;

    String sajob;
    String sahire;

    @Column(nullable = true)
    Integer samgr;

    int sapay;

    // 참조만 하게끔 설정.
    @Column(insertable = false, updatable = false)
    int deptno;

    @OneToOne
    @JoinColumn(name = "deptno", referencedColumnName = "deptno")
    Dept dept;
}
