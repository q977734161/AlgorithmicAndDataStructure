package org.lxb.algorithmic.list.skiplist;

/**
 * @author lixiaobao
 * @create 2019-07-23 8:47 AM
 * 跳跃表的节点的构成
 * 包含 分值score，val，以及next和down指针
 **/

public class SkipListNode<E> {

  E val;
  double score;
  SkipListNode<E> next,down;

  SkipListNode() {
  }

  SkipListNode(E val, double score) {
    this.val = val;
    this.score = score;
  }

  public static class SkipListLevel {

  }

}
