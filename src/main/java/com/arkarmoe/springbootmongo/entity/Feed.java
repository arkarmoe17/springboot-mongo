package com.arkarmoe.springbootmongo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "feed")
public class Feed {
    @Id
    private String id;

    private Long userId;

    private String status;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updatedAt;
}
