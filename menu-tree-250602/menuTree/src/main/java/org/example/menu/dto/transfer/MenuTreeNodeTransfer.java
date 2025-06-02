package org.example.menu.dto.transfer;

import org.example.common.transfer.TreeNodeTransfer;
import org.example.menu.domain.Menu;
import org.example.menu.dto.MenuTreeNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MenuTreeNodeTransfer implements TreeNodeTransfer<Menu, MenuTreeNode> {
    @Override
    public MenuTreeNode toTreeNode(Menu menu) {
        return MenuTreeNode.builder()
                .code(menu.getMenuCd())
                .name(menu.getMenuNm())
                .url(menu.getMenuUrl())
                .parentCode(menu.getPrntMenuCd())
                .children(new ArrayList<>())
                .build();
    }
}
