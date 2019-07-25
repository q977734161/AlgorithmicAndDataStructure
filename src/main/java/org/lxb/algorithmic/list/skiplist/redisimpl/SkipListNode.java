package org.lxb.algorithmic.list.skiplist.redisimpl;

/**
 * @author lixiaobao
 * @create 2019-07-23 8:47 AM
 * 跳跃表的节点的构成
 * 包含分值score，val
 **/

public class SkipListNode<E> {

  private E val;
  private double score;
  private SkipListNode<E> backward = null;
  private SkipListLevel<E> skipListLevels[];

  SkipListNode(int level, double score, E val) {
    skipListLevels =new SkipListLevel[level];
    this.val = val;
    this.score = score;
    initSkipListLevel(skipListLevels);
  }

  private void initSkipListLevel(SkipListLevel<E>[] skipListLevels) {
    for (int i = 0; i < skipListLevels.length; i++) {
      skipListLevels[i] = new SkipListLevel<>();
    }
  }

  public E getVal() {
    return val;
  }

  public void setVal(E val) {
    this.val = val;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public SkipListNode<E> getBackward() {
    return backward;
  }

  public void setBackward(SkipListNode<E> backward) {
    this.backward = backward;
  }

  public SkipListLevel<E>[] getLevel() {
    return skipListLevels;
  }

  public static class SkipListLevel<T> {
    /**
     * 前进指针
     */
    SkipListNode<T> forward = null;

    /**
     * 跨度
     */
    int span = 0;

    public SkipListLevel() {

    }

    public SkipListNode<T> getForward() {
      return forward;
    }

    public void setForward(SkipListNode<T> forward) {
      this.forward = forward;
    }

    public int getSpan() {
      return span;
    }

    public void setSpan(int span) {
      this.span = span;
    }
  }

}
