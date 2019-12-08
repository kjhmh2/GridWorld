package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.*;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw 
{
    private List<JigsawNode> solutionPath;  // 解路径：用以保存从起始状态到达目标状态的移动路径中的每一个状态节点
    private int searchedNodesNum;           // 已访问节点数：用以记录所有访问过的节点的数量
    private Set<JigsawNode> visitedList;    // 用以保存已发现的节点

    /**
     * 拼图构造函数
     */
    public Solution() {}

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) 
    {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) 
    {
        this.visitedList = new HashSet<JigsawNode>(1000);
        Queue<JigsawNode> exploreList = new LinkedList<JigsawNode>();
        
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        
        // 访问节点数大于29000个则认为搜索失败
        final int MAX_NODE_NUM = 29000;
        final int DIRS = 4;
        
        // 重置求解标记
        this.searchedNodesNum = 0;
        this.solutionPath = null;
        
        // (1)将起始节点放入exploreList中
        this.visitedList.add(this.beginJNode);
        exploreList.add(this.beginJNode);
        
        // (2) 如果exploreList为空，或者访问节点数大于MAX_NODE_NUM个，则搜索失败，问题无解;否则循环直到求解成功
        while (this.searchedNodesNum < MAX_NODE_NUM && !exploreList.isEmpty()) 
        {
            this.searchedNodesNum ++;
            
            // (2-1)取出exploreList的第一个节点N，置为当前节点currentJNode
            //      若currentJNode为目标节点，则搜索成功，计算解路径，退出
            this.currentJNode = exploreList.poll();
            if(this.currentJNode.equals(eNode)) 
            {
                this.getPath();
                break;
            }
            
            // 记录并显示搜索过程
            // System.out.println("Searching.....Number of searched nodes:" + searchedNodesNum +
            //     "    Est:" + this.currentJNode.getEstimatedValue() +
            //     "    Current state:" + this.currentJNode.toString());

            JigsawNode[] nextNodes = new JigsawNode[] 
            {
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };
            
            for (int i = 0; i < 4; ++ i) 
            {
                // 如果没有被访问
                if (nextNodes[i].move(i) && !this.visitedList.contains(nextNodes[i])) 
                {
                    this.visitedList.add(nextNodes[i]);
                    exploreList.add(nextNodes[i]);
                }
            }
        }
        
        System.out.println("Jigsaw BFSearch Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return this.isCompleted();
    }

    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        int dimension = JigsawNode.getDimension();
        for (int i = 1; i < dimension * dimension; i ++)
        {
            // 后续节点不正确的数码个数
            if (jNode.getNodesState()[i] + 1 != jNode.getNodesState()[i + 1]) {
                s1 ++;
            }
            // 所有放错位的数码与其正确位置的距离之和
            if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0)
            {
                int row = (jNode.getNodesState()[i] - 1) / dimension;
                int col = (jNode.getNodesState()[i] - 1) % dimension;
                int standardRow = (i - 1) / dimension;
                int standardCol = (i - 1) % dimension;
                int dis = (int)Math.sqrt((row - standardRow) * (row - standardRow) + (col - standardCol) * (col - standardCol));
                s2 += dis;
            }
            // 所有放错位的数码个数
            if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0)
            {
                s3 ++;
            }
        }
        int value = 3 * s1 + 10 * s2 + 3 * s3;
        jNode.setEstimatedValue(value);
    }
}

