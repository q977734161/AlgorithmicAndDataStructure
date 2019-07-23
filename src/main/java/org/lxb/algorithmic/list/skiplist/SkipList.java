package org.lxb.algorithmic.list.skiplist;

/**
 * @author lixiaobao
 * @create 2019-07-23 8:55 AM
 **/

public class SkipList<T> {

  /**
   * 表头节点和表尾节点
   */
  SkipListNode header,tail;

  /**
   * 表中节点的数据量
   */
  long length;

  /**
   * 表中层数最大的节点层数
   */
  int level;

}
