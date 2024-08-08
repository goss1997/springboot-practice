package com.githrd.demo_mybatis.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data = @Getter + @Setter
@Data
@NoArgsConstructor  // Default Constructor
@AllArgsConstructor // All Property넣어준 Overload된 생성자
@Alias("dept")
public class DeptVo {

    int    deptno;
    String dname;
    String loc;

}
