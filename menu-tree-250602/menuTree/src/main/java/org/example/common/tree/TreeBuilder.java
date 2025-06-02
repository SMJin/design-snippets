package org.example.common.tree;

import org.example.common.transfer.TreeNodeTransfer;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class TreeBuilder<E, N extends TreeNode<N>> {

    private final TreeNodeTransfer<E, N> transfer;

    /**
     * 부모가 존재하는 데이터들에 대해서 트리 구조로 반환
     * @param entities 트리 구조의 형태를 지니는 특정 객체
     * @return 트리구조로 재생성된 특정 객체의 List Response
     */
    public List<N> buildTree(List<E> entities) {
        Map<String, N> nodeMap = new HashMap<>();

        // 모든 Entity(객체)를 TreeNode 로 변환한다.
        for (E entity : entities) {
            N node = transfer.toTreeNode(entity);
            nodeMap.put(node.getId(), node);
        }

        // 최상위 root 를 시작으로 tree 구조를 이루는 List 를 생성한다.
        List<N> roots = new ArrayList<>();
        for (String id : nodeMap.keySet()) {
            N current = nodeMap.get(id);
            String parentId = current.getParentId();
            if (parentId == null || !nodeMap.containsKey(id)) {
                roots.add(current);
            } else {
                N parent = nodeMap.get(parentId);
                parent.getChildren().add(current);
            }
        }

        return roots;
    }
}
