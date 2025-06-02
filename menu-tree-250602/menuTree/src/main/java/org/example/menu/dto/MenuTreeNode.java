package org.example.menu.dto;

import org.example.common.tree.TreeNode;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class MenuTreeNode implements TreeNode<MenuTreeNode> {

    private String code;
    private String name;
    private String url;

    private String parentCode;
    private List<MenuTreeNode> children;

    @Override
    public String getId() {
        return this.code;
    }

    @Override
    public String getParentId() {
        return this.parentCode;
    }

    @Override
    public List<MenuTreeNode> getChildren() {
        return this.children;
    }
}
