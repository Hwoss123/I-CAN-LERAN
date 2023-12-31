package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat_records {
//    private Integer id;
//    private Integer senderId;
//    private Integer recipientId;
    private String messageContent;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timestamp;
}
