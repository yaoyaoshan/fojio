> 参考链接：[leetcode](https://leetcode.com/problems/) [GrandYang](https://www.cnblogs.com/grandyang/p/6854825.html) [AcWing](https://www.acwing.com/about/) [剑指offer](https://www.nowcoder.com/ta/coding-interviews)

## DFS/BFS

### 题目

- 需要重置visited的（通常 **判断某路径是否存在** 这种需要重置visited）
  - lc79 单词搜索
  - lc207 课程表
  - lc433 最小基因变化（如果在主函数和dfs函数中，遍历的是相同的vector/set等，那在主函数中直接调1次dfs就行了；只有当dfs函数中传入了visited的index i时，才会在dfs函数第一行就判断是否visited；可参考lc737体会两者差别）
- 不需要重置visited的dfs（通常 **求连成一片的子块数量/面积等性质** 这种不需要重置visited）（也都可以用bfs做）
  - 各种岛屿
    - lc200 岛屿数量
    - lc695 岛屿最大面积
    - lc733 洪水填色
    - lc827 构造大岛
    - lc130 包围区域
    - jz66 机器人的运动范围
  - 各种同类合并（bfs/dfs/并查集）
    - lc841 房间与钥匙
    - lc721 合并账号
    - lc547 朋友圈
    - lc399 求除法表达式的值
    - lc737 句子相似度2⃣️
    - lc839 相似字符串组
    - lc952 按公因数计算最大连通子集
    - lc990 等式方程的可满足性
- 回溯相关（可以把决策树画出来帮助理解）
  - lc257 求二叉树所有路径
  - lc113 返回所有和为target的路径
  - lc78、lc90 求所有子集
- 用bfs更好写的
  - lc815 公交路线
  - 迷宫
    - lc1162 地图分析、lc286 墙和门（两道题思路几乎一样）
    - lc1263 推箱子（箱子的位置可能出现重复，因此以`(箱子的位置, 人的位置)`保存`visited`数组）
  - lc1129 交替颜色最短路径

### 算法思想

#### dfs

主函数：遍历每一个元素，调用dfs函数

dfs函数：

1. 判断是否越界/visited/符合条件，视情况return
2. 设置visited（在判断是否visited之后的下一句一般就是设置visited[cur]=true了）
3. 在各个方向递归调用dfs函数
4. 重置visited（这一步可能不需要）

#### bfs

用queue