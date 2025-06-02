package org.example.menu.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class MenuTreeResponse {
    private String code;
    private String name;
    private String url;

    private List<MenuTreeNode> children;
}
