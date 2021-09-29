package org.yx.mongotest.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author yangxin
 * @date 2021-09-29 09:35
 * @since v1.6.5
 */
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Test {


    @JsonProperty("ID")
    private String id;

    private String userName;

    private String passWord;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public static void main(String[] args) throws JsonProcessingException {
        String t = "{\"UserName\":\"zhangsan\",\"PassWord\":\"123456\",\"ID\":\"123456\"}";
        ObjectMapper objectMapper = new ObjectMapper();

        //objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

        Test test = objectMapper.readValue(t, Test.class);

        System.out.println(test);
    }
}
