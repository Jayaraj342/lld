package com.example.lld.splitwise.dto;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String id;
    private String name;
    private String imageUrl;
    private List<String> userIds;
}
