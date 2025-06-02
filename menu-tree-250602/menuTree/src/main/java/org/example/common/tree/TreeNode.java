package org.example.common.tree;

import java.util.List;

public interface TreeNode<N> {
    String getId();
    String getParentId();
    List<N> getChildren();
}
