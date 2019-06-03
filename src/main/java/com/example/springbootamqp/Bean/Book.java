package com.example.springbootamqp.Bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private String bookName;
    private String author;

    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }
//这里需要无参构造方法，否则反序列化会失败。

    public Book() {
    }
}
