package org.lxb.algorithmic.list.skiplist.redisimpl;

import java.util.Random;

/**
 * @author lixiaobao
 * @create 2019-07-23 8:55 AM
 **/

public class SkipList<T> {

  /**
   * 跳表的最大层数
   */
  protected static final int ZSKIPLIST_MAXLEVEL = 32;

  /**
   * Skiplist P = 1/4
   */
  private static final float ZSKIPLIST_P = 0.25f;

  /**
   * 表头节点和表尾节点
   */
  SkipListNode header, tail = null;

  /**
   * 表中节点的数据量
   */
  int length;

  /**
   * 表中层数最大的节点层数
   */
  int level;

  public SkipList() {
    init();
  }

  private void init() {
    level = 1;
    length = 0;
    this.header = new SkipListNode(ZSKIPLIST_MAXLEVEL, 0, null);
  }

  /**
   * 返回一个随机值，最为跳跃表节点的层数
   * 返回值介乎 1 和 ZSKIPLIST_MAXLEVEL 之间（包含 ZSKIPLIST_MAXLEVEL），
   * 根据随机算法所使用的幂次定律，越大的值生成的几率越小。
   *
   * @return
   */
  protected int zslRandomLevel() {
    int level = 1;
    while (Math.random() < ZSKIPLIST_P ) {
      level += 1;
    }

    return (level < ZSKIPLIST_MAXLEVEL) ? level : ZSKIPLIST_MAXLEVEL;
  }

  /**
   * 创建一个成员为 obj ，分值为 score 的新节点，
   * 并将这个新节点插入到跳跃表 zsl 中。
   * 函数的返回值为新节点。
   * T_wrost = O(N^2), T_avg = O(N log N)
   **/
  public SkipListNode<T> zslInsert(double score, T val) {

    //记录每层要插入节点的位置
    SkipListNode<T>[] update = new SkipListNode[ZSKIPLIST_MAXLEVEL];
    SkipListNode<T> x;

    //记录每层跨越了多少个节点
    int[] rank = new int[ZSKIPLIST_MAXLEVEL];
    int i, level;

    // 在各个层查找节点的插入位置
    // T_wrost = O(N^2), T_avg = O(N log N)
    x = this.header;
    for (i = this.level-1; i >= 0; i--) {

      // 如果 i 不是 level-1 层
      // 那么 i 层的起始 rank 值为 i+1 层的 rank 值
      // 各个层的 rank 值一层层累积
      // 最终 rank[0] 的值加一就是新节点的前置节点的排位
      // rank[0] 会在后面成为计算 span 值和 rank 值的基础
      rank[i] = (i == (this.level-1) ? 0 : rank[i+1]);

      // 沿着前进指针遍历跳跃表
      // T_wrost = O(N^2), T_avg = O(N log N)
      // x.getLevel()[i].forward != null 判断是下一个元素是否存在
      // x.getLevel()[i].forward.getScore() < score 比对分值
      // （x.getLevel()[i].forward.getScore() == score && compareObjects(x.getLevel()[i].forward.getVal(),val) < 0)比对成员， T = O(N)
      while (x.getLevel()[i].forward != null &&
              (x.getLevel()[i].forward.getScore() < score ||
                      (x.getLevel()[i].forward.getScore() == score &&
                              compareObjects(x.getLevel()[i].forward.getVal(),val) < 0))) {

        // 记录沿途跨越了多少个节点
        rank[i] += x.getLevel()[i].span;

        // 移动至下一指针
        x = x.getLevel()[i].forward;
      }
      // 记录将要和新节点相连接的节点
      update[i] = x;
    }

    /* we assume the key is not already inside, since we allow duplicated
     * scores, and the re-insertion of score and redis object should never
     * happen since the caller of zslInsert() should test in the hash table
     * if the element is already inside or not.
     *
     * zslInsert() 的调用者会确保同分值且同成员的元素不会出现，
     * 所以这里不需要进一步进行检查，可以直接创建新元素。
     */

    // 获取一个随机值作为新节点的层数
    // T = O(N)
    level = zslRandomLevel();

    // 如果新节点的层数比表中其他节点的层数都要大
    // 那么初始化表头节点中未使用的层，并将它们记录到 update 数组中
    // 将来也指向新节点
    if (level > this.level) {

      // 初始化未使用层
      // T = O(1)
      for (i = this.level; i < level; i++) {
        rank[i] = 0;
        update[i] = this.header;
        update[i].getLevel()[i].span = this.length;
      }

      // 更新表中节点最大层数
      this.level = level;
    }

    // 创建新节点
    x = new SkipListNode<T>(level,score,val);

    // 将前面记录的指针指向新节点，并做相应的设置
    // T = O(1)
    for (i = 0; i < level; i++) {

      // 设置新节点的 forward 指针
      x.getLevel()[i].forward = update[i].getLevel()[i].forward;

      // 将沿途记录的各个节点的 forward 指针指向新节点
      update[i].getLevel()[i].forward = x;

        /* update span covered by update[i] as x is inserted here */
      // 计算新节点跨越的节点数量
      x.getLevel()[i].span = update[i].getLevel()[i].span - (rank[0] - rank[i]);

      // 更新新节点插入之后，沿途节点的 span 值
      // 其中的 +1 计算的是新节点
      update[i].getLevel()[i].span = (rank[0] - rank[i]) + 1;
    }

    /* increment span for untouched levels */
    // 未接触的节点的 span 值也需要增一，这些节点直接从表头指向新节点
    // T = O(1)
    for (i = level; i < this.level; i++) {
      update[i].getLevel()[i].span++;
    }

    // 设置新节点的后退指针
    x.setBackward((update[0] == this.header) ? null : update[0]);
    if (x.getLevel()[0].forward != null) {
      x.getLevel()[0].forward.setBackward(x);
    } else {
      this.tail = x;
    }

    // 跳跃表的节点计数增一
    this.length++;

    return x;
  }

  /**
   * 内部删除函数
   * @param x
   * @param update
   */
  public void zslDeleteNode(SkipListNode<T> x,SkipListNode<T>[] update) {

    //更新所有和被删除节点 x 有关的节点指针，删除他们之间的关系
    for (int i = 0;  i< this.level; i++) {
      if(update[i].getLevel()[i].getForward() == x) {
        update[i].getLevel()[i].span = x.getLevel()[i].span - 1;
        update[i].getLevel()[i].setForward(x.getLevel()[i].forward);
      } else {
        update[i].getLevel()[i].span -= 1;
      }
    }

    // 更新被删除节点 x 的前进和后退指针
    if(x.getLevel()[0].forward != null) {
      x.getLevel()[0].forward.setBackward(x.getBackward());
    } else {
      this.tail = x.getBackward();
    }

    // 更新跳跃表最大层数（只在被删除节点是跳跃表中最高的节点时才执行）
    // T = O(1)
    while(this.level > 1 && this.header.getLevel()[this.level-1].forward == null) {
      this.level--;
    }

    // 跳跃表节点计数器减一
    this.length--;

  }

  /**
   * 删除节点
   * @param score
   * @param val
   * @return 0 没有发现，1 删除成功
   */
  public int delete(double score,T val) {
    SkipListNode<T>[] update = new SkipListNode[ZSKIPLIST_MAXLEVEL];
    SkipListNode<T> x;

    // 遍历跳跃表，查找目标节点，并记录所有沿途节点
    // T_wrost = O(N^2), T_avg = O(N log N)
    x = this.header;
    for (int i = this.level-1; i >= 0; i--) {

      // 遍历跳跃表的复杂度为 T_wrost = O(N), T_avg = O(log N)
      while (x.getLevel()[i].forward != null &&
              (x.getLevel()[i].forward.getScore() < score ||
                      // 比对分值
                      (x.getLevel()[i].forward.getScore() == score &&
                              // 比对对象，T = O(N)
                              compareObjects(x.getLevel()[i].forward.getVal(), val) < 0))) {

        // 沿着前进指针移动
        x = x.getLevel()[i].forward;

        // 记录沿途节点
        update[i] = x;
      }
    }

    /* We may have multiple elements with the same score, what we need
     * is to find the element with both the right score and object.
     *
     * 检查找到的元素 x ，只有在它的分值和对象都相同时，才将它删除。
     */
    x = x.getLevel()[0].forward;
    if (x != null && score == x.getScore() && compareObjects(x.getVal(),val) == 0) {
      // T = O(1)
      zslDeleteNode(x, update);
      // T = O(1)
      zslFreeNode(x);
      return 1;
    } else {
      return 0; /* not found */
    }
  }


  /**
   * 比较两个对象的值
   * @param val
   * @param val1
   * @return
   */
  private int compareObjects(T val, T val1) {

    if(val == val1) {
      return 0;
    }

    if(val instanceof Comparable && val1 instanceof Comparable) {
      return ((Comparable) val1).compareTo(val1);
    }

    return val.toString().compareTo(val1.toString());
  }

  /**
   * 释放节点
   * @param x
   */
  private void zslFreeNode(SkipListNode<T> x) {
    x.setBackward(null);
    for (int i = 0; i < x.getLevel().length; i++) {
      x.getLevel()[i] = null;
    }
  }

}