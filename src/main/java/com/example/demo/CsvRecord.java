package com.example.demo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { 
        "col1"
        ,"col2"
        ,"col3"
        ,"col4"
        ,"col5"
        ,"col6"
        ,"col7"
        ,"col8"
        ,"col9"
        ,"col10"
})
public class CsvRecord implements Serializable {

    private static final long serialVersionUID = -5384701682469331626L;

    @JsonProperty("col1")
    private String col1;
    @JsonProperty("col2")
    private String col2;
    @JsonProperty("col3")
    private String col3;
    @JsonProperty("col4")
    private String col4;
    @JsonProperty("col5")
    private String col5;
    @JsonProperty("col6")
    private String col6;
    @JsonProperty("col7")
    private String col7;
    @JsonProperty("col8")
    private String col8;
    @JsonProperty("col9")
    private String col9;
    @JsonProperty("col10")
    private String col10;
    
    public String getCol1() {
        return col1;
    }
    public void setCol1(String col1) {
        this.col1 = col1;
    }
    public String getCol2() {
        return col2;
    }
    public void setCol2(String col2) {
        this.col2 = col2;
    }
    public String getCol3() {
        return col3;
    }
    public void setCol3(String col3) {
        this.col3 = col3;
    }
    public String getCol4() {
        return col4;
    }
    public void setCol4(String col4) {
        this.col4 = col4;
    }
    public String getCol5() {
        return col5;
    }
    public void setCol5(String col5) {
        this.col5 = col5;
    }
    public String getCol6() {
        return col6;
    }
    public void setCol6(String col6) {
        this.col6 = col6;
    }
    public String getCol7() {
        return col7;
    }
    public void setCol7(String col7) {
        this.col7 = col7;
    }
    public String getCol8() {
        return col8;
    }
    public void setCol8(String col8) {
        this.col8 = col8;
    }
    public String getCol9() {
        return col9;
    }
    public void setCol9(String col9) {
        this.col9 = col9;
    }
    public String getCol10() {
        return col10;
    }
    public void setCol10(String col10) {
        this.col10 = col10;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
