package com.example.forum.dto;

import lombok.Data;

@Data
public class CommentDTO {
     private Long parentId;
     private String comment;
     private Integer type;
}
