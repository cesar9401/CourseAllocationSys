/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class AVLTree<T> {

    private AVLNode<T> root;
    private int size;

    public AVLTree() {
        this.size = 0;
    }

    public void insert(T data, String id) {
        AVLNode node = new AVLNode(data, id);

        if (this.root == null) {
            this.root = node;
        } else {
            this.root = insert(node, root);
        }
    }

    private AVLNode<T> insert(AVLNode<T> node, AVLNode<T> father) {
        AVLNode dad = father;

        if (node.getId().compareTo(father.getId()) < 0) {
            if (father.getLeft() == null) {
                father.setLeft(node);
            } else {
                father.setLeft(insert(node, father.getLeft()));
                if (father.getFactor() == -2) {
                    if (father.getLeft().getFactor() > 0) {
                        /* rotacion doble izquierda */
                        dad = doubleLeftRotation(father);
                    } else {
                        dad = leftRotation(father);
                    }
                }
            }
        } else if (node.getId().compareTo(father.getId()) > 0) {
            if (father.getRight() == null) {
                father.setRight(node);
            } else {
                father.setRight(insert(node, father.getRight()));
                if (father.getFactor() == 2) {
                    if (father.getRight().getFactor() < 0) {
                        /* rotacion doble derecha */
                        dad = doubleRightRotation(father);
                    } else {
                        dad = rightRotation(father);
                    }
                }
            }
        } else if (node.getId().compareTo(father.getId()) == 0) {
            System.out.println("Ya existe dato" + node.getId());
        }

        return dad;
    }

    public AVLNode<T> search(String id) {
        return search(this.root, id);
    }

    private AVLNode<T> search(AVLNode<T> father, String id) {
        if (father != null) {
            if (id.compareTo(father.getId()) < 0) {
                return search(father.getLeft(), id);
            } else if (id.compareTo(father.getId()) > 0) {
                return search(father.getRight(), id);
            } else {
                return father;
            }
        }
        return null;
    }

    public void delete(String id) {
        if (this.root != null) {
            this.root = delete(id, this.root);
        } else {
            System.out.println("Arbol vacio");
        }
    }

    private AVLNode<T> delete(String id, AVLNode<T> father) {
        AVLNode<T> dad = father;

        return dad;
    }

    /**
     * Casos de eliminacion
     *
     * @param node
     * @return
     */
    private AVLNode<T> delete(AVLNode<T> node) {
        boolean right = node.getRight() != null;
        boolean left = node.getLeft() != null;

        if (!right && !left) {
            /* nodo hoja */
            node = null;
        } else if (right && left) {
            /* nodo con dos hijos */
            AVLNode<T> dad = lowerRight(node.getRight());
            node.setRight(delete(dad.getId(), node.getRight()));
        
            /* hijo izquierda */
            dad.setLeft(node.getLeft());
            dad.setRight(node.getRight());
            
            node = dad;
        } else if(right || left) {
            node = right ? node.getRight() : node.getLeft();
        }

        return node;
    }

    /**
     * Nodo con id mas pequeño
     *
     * @param node
     * @return
     */
    private AVLNode lowerRight(AVLNode<T> node) {
        if (node.getLeft() != null) {
            node = lowerRight(node.getLeft());
        }
        return node;
    }

    private AVLNode<T> doubleLeftRotation(AVLNode<T> node) {
        node.setLeft(rightRotation(node.getLeft()));
        return leftRotation(node);
    }

    private AVLNode<T> doubleRightRotation(AVLNode<T> node) {
        node.setRight(leftRotation(node.getRight()));
        return rightRotation(node);
    }

    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> aux = node.getLeft();
        node.setLeft(aux.getRight());
        aux.setRight(node);

        return aux;
    }

    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> aux = node.getRight();
        node.setRight(aux.getLeft());
        aux.setLeft(node);

        return aux;
    }

    public int getSize() {
        this.size = 0;
        getSize(this.root);
        return size;
    }

    private void getSize(AVLNode<T> node) {
        if (node != null) {
            this.size -= -1;
            getSize(node.getLeft());
            getSize(node.getRight());
        }
    }
}
