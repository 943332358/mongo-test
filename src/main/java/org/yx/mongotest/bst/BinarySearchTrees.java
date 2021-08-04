package org.yx.mongotest.bst;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.var;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author yangxin
 */
public class BinarySearchTrees<K, V> {
    private Node<K, V> root;

    @AllArgsConstructor
    @Data
    static class Node<K, V> {
        private V v;
        private K k;
        private int hash;
        private Node<K, V> left, right;

        public Node(K k, V v, int hash) {
            this.k = k;
            this.v = v;
            this.hash = hash;
        }

        @SneakyThrows
        @Override
        public String toString() {
            return new ObjectMapper().writeValueAsString(this);
        }
    }

    /**
     * 找到要删除节点后，寻找其左子树的最右子节点，替换要删除的节点
     */
    public V remove(K k) {
        // 将要移除的node
        var removeNode = get(root, k.hashCode());

        Consumer<Node<K, V>> consumer = node -> {
            removeNode.k = node.k;
            removeNode.v = node.v;
            removeNode.hash = node.hash;
        };

        // 同时包含左子树和右子树
        if (Objects.nonNull(removeNode.left) && Objects.nonNull(removeNode.right)) {
            // 该node替换需要移除的removeNode
            var substituteNode = remove(removeNode.left, removeNode, true);
            consumer.accept(substituteNode);
        }

        // 只包含右子树
        if (Objects.isNull(removeNode.left) && Objects.nonNull(removeNode.right)) {
            consumer.accept(removeNode.right);
            removeNode.right = removeNode.right.right;
        }


        return removeNode.v;
    }

    private Node<K, V> remove(Node<K, V> node, Node<K, V> parentNode, boolean first) {
        if (Objects.nonNull(node.right)) {
            return remove(node.right, node, false);
        }

        if (first) {
            parentNode.left = null;
        } else {
            // 移除当前node的引用（删除左子树的最右子节点）
            parentNode.right = null;
        }
        return node;
    }

    public Node<K, V> get(Node<K, V> node, int hash) {
        if (node == null) {
            return null;
        }

        if (node.hash > hash) {
            return get(node.left, hash);
        }
        if (node.hash < hash) {
            return get(node.right, hash);
        }
        return node;
    }

    public V get(K k) {
        return get(root, k.hashCode()).v;
    }


    private Node<K, V> put(Node<K, V> node, K k, V v) {
        var hash = k.hashCode();
        if (node == null) {
            node = new Node<>(k, v, hash);
        }

        // 插入左子树
        if (node.hash > hash) {
            node.left = put(node.left, k, v);
        }

        // 插入右子树
        if (node.hash < hash) {
            node.right = put(node.right, k, v);
        }
        return node;
    }

    public void put(K k, V v) {
        root = put(root, k, v);
    }

    @Override
    public String toString() {
        return "BinarySearchTrees{" +
                "root=" + root +
                '}';
    }
}
