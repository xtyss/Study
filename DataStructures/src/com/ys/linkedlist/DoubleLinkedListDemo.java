package com.ys.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        HeroNode2 heroNode1 = new HeroNode2(1,"A","111");
        HeroNode2 heroNode2 = new HeroNode2(2,"B","222");
        HeroNode2 heroNode3 = new HeroNode2(3,"C","333");
        HeroNode2 heroNode4 = new HeroNode2(4,"D","444");
        HeroNode2 heroNode5 = new HeroNode2(5,"E","555");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode5);

        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    private HeroNode2 head= new HeroNode2(0,"","");


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
        HeroNode2 temp = head.nextNode;
        while (true){

            if (temp==null){
                break;
            }

            System.out.println(temp);

            //单项列表后移
            temp = temp.nextNode;
        }
    }


    public void add(HeroNode2 heroNode){

        //定义临时节点temp 开始时指向头
        HeroNode2 temp = head;

        while (true){
            if (temp.nextNode == null){
                temp.nextNode = heroNode;
                heroNode.preNode = temp;
                break;
            }

            temp=temp.nextNode;
        }
    }

    /**
     * 根据编号修改
     * @param heroNode 新的节点
     */
    public void update(HeroNode2 heroNode){
        HeroNode2 temp = head.nextNode;
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
        HeroNode2 temp = head;
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
            temp.preNode.nextNode = temp.nextNode;

            if (temp.nextNode!=null){
                temp.nextNode.preNode = temp.preNode;
            }
        }else {
            System.out.println("无当前编号节点");
        }
    }
}
/**
 * 定义节点类
 */
class HeroNode2{
    //序号
    public int no;
    //名称
    public String name;
    //内容
    public String nickName;
    //上一个节点
    public HeroNode2 preNode;
    //下一个节点
    public HeroNode2 nextNode;

    public HeroNode2(int no,String name,String nickName){
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
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}