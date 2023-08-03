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
            insert into mydb.employee (
            col1,
            col2,
            col3,
            col4,
            col5,
            col6,
            col7,
            col8,
            col9,
            col10
            ) values 
            <foreach collection=\"records\" item=\"record\" separator=\",\"> 
            (
            #{record.col1},
            #{record.col2},
            #{record.col3},
            #{record.col4},
            #{record.col5},
            #{record.col6},
            #{record.col7},
            #{record.col8},
            #{record.col9},
            #{record.col10}
            )
            </foreach>
        </script>
        """
        })
    public int insert(@Param("records") List<CsvRecord> csvRecords);
}
