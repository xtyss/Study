package com.ys.linkedlist;

import java.util.Stack;

/**
 * 用于学习单项链表的原理
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试代码

        //插入

        HeroNode heroNode1 = new HeroNode(1,"A","111");
        HeroNode heroNode2 = new HeroNode(2,"B","222");
        HeroNode heroNode3 = new HeroNode(3,"C","333");
        HeroNode heroNode4 = new HeroNode(4,"D","444");
        HeroNode heroNode5 = new HeroNode(5,"E","555");

        LinkedList linkedList = new LinkedList();


        /*linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.add(heroNode3);
        linkedList.add(heroNode4);
        linkedList.add(heroNode5);*/

        linkedList.addByOrder(heroNode1);

        linkedList.addByOrder(heroNode3);
        linkedList.addByOrder(heroNode4);
        linkedList.addByOrder(heroNode2);
        linkedList.addByOrder(heroNode5);
        linkedList.list();


        //修改
        HeroNode heroNode6 = new HeroNode(3,"CCCC","3333333333");

        linkedList.update(heroNode6);

        System.out.println("----------------------");

        linkedList.list();

        System.out.println("----------------------");
        //测试删除
        linkedList.delete(1);
        linkedList.delete(5);
        linkedList.list();

        System.out.println(linkedList.getLength());
        System.out.println("----------------------");
        System.out.println(linkedList.getLastIndexHeroNode(1));

        System.out.println("----------------------");
        linkedList.reSet().list();
    }



}

/**
 * 链表实体类
 */
class LinkedList{
    private HeroNode head= new HeroNode(0,"","");
    /**
     * 添加到末尾
     */

    public void add(HeroNode heroNode){

        //定义临时节点temp 开始时指向头
        HeroNode temp = head;

        while (true){
            if (temp.nextNode == null){
                temp.nextNode = heroNode;
                break;
            }

            temp=temp.nextNode;
        }
    }

    /**
     * 添加到固定位置
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.nextNode==null){//temp在链表最后
                break;
            }

            if (temp.nextNode.no >heroNode.no){
                break;
            }

            if (temp.nextNode.no == heroNode.no){
                flag = true;
                break;
            }

            temp = temp.nextNode;
        }

        if (flag){
            System.out.println("当前编号以存在");
        }else {
            heroNode.nextNode = temp.nextNode;
            temp.nextNode = heroNode;
        }

    }


    /**
     * 根据编号修改
     * @param heroNode 新的节点
     */
    public void update(HeroNode heroNode){
        HeroNode temp = head.nextNode;
        if (temp==null){
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        //循环判断,找到位置
        while (true){
            if (temp==null){
                System.out.println("没有找到啊!!!");
                break;
            }
            if (temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.nextNode;
        }
        if (flag){
            temp.nickName = heroNode.nickName;
            temp.name = heroNode.name;
        }
    }

    /**
     * 删除一个节点
     * @param no 编号
     */
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;

        while (true){
            //如果下一个为空,代码temp为最后一个
            if (temp.nextNode==null){
                break;
            }

            if (temp.nextNode.no == no){
                flag = true;
                break;
            }

            temp = temp.nextNode;
        }

        if (flag){
            temp.nextNode = temp.nextNode.nextNode;
        }else {
            System.out.println("无当前编号节点");
        }
    }

    /**
     * 得到长度
     * @return 长度
     */
    public int getLength(){
        int count = 0;

        if (head.nextNode==null){
            return count;
        }
        HeroNode temp = head.nextNode;
        while(true){
            count++;
            if (temp.nextNode ==null){
                break;
            }


            temp = temp.nextNode;
        }
        return count;
    }
    //获取倒数第k个节点
    //1.有指针 temp
    //2.先参数判断
    public  HeroNode getLastIndexHeroNode(int lastIndex){
        HeroNode temp = head.nextNode;

        if (lastIndex<0||lastIndex> this.getLength()){
            return null;
        }
        for (int i = 0; i < this.getLength() - lastIndex; i++) {
            temp=temp.nextNode;
        }

        return temp;
    }

    /**
     * 链表反转
     * @return
     */
    public LinkedList reSet(){
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode temp = head.nextNode;
        HeroNode  nextTemp = null;
        while (temp!=null) {
            //先保存下一节点
            nextTemp = temp.nextNode;
            temp.nextNode =  newHead.nextNode;
            newHead.nextNode = temp;
            //将下一节点给temp 完成指针后移
            temp=nextTemp;
        }
        //替换头
        this.head = newHead;
        return this;
    }

    /**
     * 逆序打印
     */
    public void reversePrint(){
        if (head.nextNode ==  null) {
            return;
        }
        HeroNode temp = head.nextNode;

        Stack<HeroNode> heroNodes = new Stack<>();

        while (temp!=null){
            heroNodes.push(temp);
            temp = temp.nextNode;
        }

        while (heroNodes.size()>0){
            System.out.println(heroNodes.pop());
        }
    }
    /**
     * 遍历方法
     */
    public void list(){
        //如果为空
        if (head.nextNode==null){
            System.out.println("链表为空");
            return;
        }

        //头节点不能动,所以需要复制节点,类似于指针
        HeroNode temp = head.nextNode;
        while (true){

            if (temp==null){
                break;
            }

            System.out.println(temp);

            //单项列表后移
            temp = temp.nextNode;
        }
    }
}


/**
 * 定义节点类
 */
class HeroNode{
    //序号
    public int no;
    //名称
    public String name;
    //内容
    public String nickName;
    //下一个节点
    public HeroNode nextNode;

    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    /**
     * 为了输出方便,重写to_string方法
     * @return
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
