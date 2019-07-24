package org.lxb.algorithmic.list.skiplist.redisimpl;

import org.junit.Test;

/**
 * @author lixiaobao
 * @create 2019-07-24 8:50 AM
 **/

public class SkipListTest {

  @Test
  public void testRandomLevel(){

    SkipList<String> skipList = new SkipList<>();
    int runTimes = 10;
    int[] levels = new int[SkipList.ZSKIPLIST_MAXLEVEL];
    for (int i = 0; i < runTimes; i++) {
      levels[skipList.zslRandomLevel()] ++;
    }
    for (int i = 0; i < SkipList.ZSKIPLIST_MAXLEVEL; i++) {
      System.out.println(levels[i]);
    }

  }

}
