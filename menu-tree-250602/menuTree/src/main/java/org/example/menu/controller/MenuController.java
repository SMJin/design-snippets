package org.example.menu.controller;

import org.example.config.exception.ApiResponse;
import org.example.config.util.ResponseUtil;
import org.example.menu.dto.MenuTreeResponse;
import org.example.menu.service.MenuTreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuTreeService menuTreeService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMenuTree() {
        List<MenuTreeResponse> menuList = menuTreeService.getAllMenuTree();
        return ResponseUtil.readSuccess(menuList);
    }
}
