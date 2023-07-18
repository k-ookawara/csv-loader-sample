package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CsvDao {

    @Insert({
        """
        <script>
            insert into employee (name, age, birthday) values 
            <foreach collection=\"records\" item=\"record\" separator=\",\"> 
            (
            #{record.name},
            #{record.age},
            #{record.birthday}
            )
            </foreach>
        </script>
        """
        })
    public int insert(@Param("records") List<CsvRecord> csvRecords);
}
