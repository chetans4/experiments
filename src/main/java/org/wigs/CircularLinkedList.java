package org.wigs;

import java.util.Objects;

public class CircularLinkedList {
    /**
     * [data| reference to next node] > [data| reference to next node] > [data| reference to first node]
     *
     * - Head
     * -- pointers slow (1 step), fast (2 steps)
     * -- 2, 3, 4, 6, 1, 4, 6, 1, 4, 6, 1
     *
     *
     */

    Node head;

    public boolean isCircular(){
        Node slowMove = this.head.next;
        Node fastMove = this.head.next.next;
        while (!Objects.isNull(fastMove) && !slowMove.equals(fastMove)){
            slowMove = this.head.next;
            fastMove = this.head.next.next;
        }

        if(!Objects.isNull(fastMove)){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        CircularLinkedList linkedList = new CircularLinkedList();
//        linkedList.isCis


    }




}


class Node{

    Integer data;
    Node next;

    Node(Integer data){
        this.data = data;
    }
}