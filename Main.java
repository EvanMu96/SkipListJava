/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author komoriii
 */
public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SkipList<String> list=new SkipList<String>();
        System.out.println(list);
        list.put(2, "sen");
        list.put(1, "mu");
        list.put(3, "kmr");
        list.put(1, "komoriiii");//测试同一个key值
        list.put(4, "森");
        list.put(6, "木");
        list.put(5, "林");
        SkipListNode<String> searchResult = list.search(1);
        SkipListNode<String> findResult = list.findNode(6);
        System.out.println("Operation results");
        System.out.println(searchResult);
        System.out.println(findResult);
        System.out.println("Print full list");
        System.out.println(list);
        System.out.println(list.size());
    }
}