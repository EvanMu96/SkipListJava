import java.util.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author komoriii
 */
public class SkipList<T> {
    private SkipListNode<T> head, tail;
    private int nodes;
    private int listLevel;
    private Random random;
    private static final double prob = 0.5;
    
    public SkipList()
    {
        random = new Random();
        clear();
    }
    
    public void clear()
    {
        head = new SkipListNode(SkipListNode.HEAD_KEY, null); //跳表头结点
        tail = new SkipListNode(SkipListNode.TAIL_KEY, null); //跳表尾节点
        horizontalLink(head, tail);
        listLevel = 0;
        nodes = 0;
    }
    
    public boolean isEmpty()
    {
        if (nodes == 0)
            return true;
        else
            return false;
    }
    
    public int size()
    {
        return nodes;
    }
    
    public SkipListNode<T> findNode(int key)
    {
        SkipListNode<T> p = head;
        while(true)
        {
            while(p.right.key!=SkipListNode.TAIL_KEY && p.right.key<=key)
            {
                p = p.right;
                
            }
            if(p.down!=null)
            {
                p = p.down;
            }else{
                break;
            }
        }
        return p;
    }
    
    public SkipListNode<T> search(int key)
    {   
        SkipListNode<T> p = findNode(key);
        if (key==p.getKey())
        {
            return p;
        }else{
            return null;
        }
    }
    
    public void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2)
    {
        node1.right = node2;
        node2.left = node1;
    }
    
    public void verticalLink(SkipListNode<T> p, SkipListNode<T> q)
    {
        p.down = q;
        q.up = p;
    }
    //想跳表中添加一组k, v
    public void put(int key, T v)
    {
        SkipListNode<T> p = findNode(key);
        if (key==p.getKey()) {
            p.value=v;
            return;
        }
        SkipListNode<T> q = new SkipListNode<T>(key, v);
        backLink(p, q);
        int currentLevel = 0;
        while (random.nextDouble()<prob) {
            //如果超出了高度，需要重新建一个顶层
            if (currentLevel>=listLevel) {
                listLevel++;
                SkipListNode<T> p1=new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> p2=new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
                horizontalLink(p1, p2);
                verticalLink(p1, head);
                verticalLink(p2, tail);
                head=p1;
                tail=p2;
            }
            //将p移动到上一层
            while (p.up==null) {
                p=p.left;
            }
            p=p.up;

            SkipListNode<T> e=new SkipListNode<T>(key, null);//只保存key就ok
            backLink(p, e);//将e插入到p的后面
            verticalLink(e, q);//将e和q上下连接
            q=e;
            currentLevel++;
        }
        nodes++;//层数递增
        
        
    }
    
    private void backLink(SkipListNode<T> p, SkipListNode<T> q)
    {
        q.left = p;
        q.right = p.right;
        p.right.left = q;
        p.right = q;
    }
    
    /**
     * 打印出原始数据
     * */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            return "跳跃表为空！";
        }
        StringBuilder builder=new StringBuilder();
        SkipListNode<T> p=head;
        while (p.down!=null) {
            p=p.down;
        }

        while (p.left!=null) {
            p=p.left;
        }
        if (p.right!=null) {
            p=p.right;
        }
        while (p.right!=null) {
            builder.append(p);
            builder.append("\n");
            p=p.right;
        }

        return builder.toString();
    }
    
}
