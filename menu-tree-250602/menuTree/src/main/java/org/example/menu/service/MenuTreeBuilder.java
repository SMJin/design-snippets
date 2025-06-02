package org.example.menu.service;

import org.example.common.tree.TreeBuilder;
import org.example.menu.domain.Menu;
import org.example.menu.dto.MenuTreeNode;
import org.example.menu.dto.transfer.MenuTreeNodeTransfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuTreeBuilder extends TreeBuilder<Menu, MenuTreeNode> {

    private final MenuTreeNodeTransfer transfer;

    public MenuTreeBuilder(MenuTreeNodeTransfer transfer) {
        super(transfer);
        this.transfer = transfer;
    }

    @Override
    public List<MenuTreeNode> buildTree(List<Menu> entities) {
        return super.buildTree(entities);
    }
}
