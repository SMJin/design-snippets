package org.example.menu.service;

import org.example.common.tree.TreeBuilder;
import org.example.menu.domain.Menu;
import org.example.menu.dto.MenuTreeNode;
import org.example.menu.dto.MenuTreeResponse;
import org.example.menu.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuTreeService {

    private final MenuRepository menuRepository;
    private final MenuTreeBuilder treeBuilder;

    public List<MenuTreeResponse> getAllMenuTree() {
        List<Menu> allMenus = menuRepository.findAll();
        List<MenuTreeNode> menuTreeNodes = treeBuilder.buildTree(allMenus);
        return menuTreeNodes.stream().map(node ->
            MenuTreeResponse.builder()
                    .code(node.getId())
                    .name(node.getName())
                    .url(node.getUrl())
                    .children(node.getChildren())
                    .build()
        ).toList();
    }
}
