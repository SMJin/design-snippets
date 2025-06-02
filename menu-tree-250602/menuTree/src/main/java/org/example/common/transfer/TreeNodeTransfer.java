package org.example.common.transfer;

public interface TreeNodeTransfer<E, N> {
    N toTreeNode(E entity);
}
