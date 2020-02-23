package com.itliv.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TagDTO {
    private String tagCategory;
    private List<String> tagName;
}
