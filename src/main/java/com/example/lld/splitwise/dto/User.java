package com.example.lld.splitwise.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String id;
    private String name;
    private String phone;
    private String imageURL;
}
