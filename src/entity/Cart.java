package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

/**
 * Created by zhongli on 2017/2/4.
 * 购物车类
 */
public class Cart {

    private HashMap<Item, Integer> goods;

    private double totalPrice;

    public Cart() {
        goods = new HashMap<Item, Integer>();
        totalPrice = 0.0;
    }

    public HashMap<Item, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<Item, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //添加商品到购物车
    public boolean addGoodsInCart(Item item, int number) {

        if (goods.containsKey(item)) {
            goods.put(item, goods.get(item) + number);
        } else {
            goods.put(item, number);
        }

        calTotalPrice();

        return true;
    }

    //移出商品
    public boolean removeGoodsInCart(Item item) {
        goods.remove(item);

        calTotalPrice();
        return true;
    }

    //统计购物车总金额
    public double calTotalPrice() {
        double sum = 0.0;

        Set<Item> keys = goods.keySet();
        Iterator<Item> iterator = keys.iterator();

        while( iterator.hasNext() ) {
            Item i = iterator.next();
            sum += i.getPrice() * goods.get(i);
        }

        this.setTotalPrice(sum);

        return sum;
    }

    //该main方法为测试用
    public static void main(String[] args) {
        Item i1 = new Item(1, "沃特篮球鞋", "温州", 200, 500, "001.jpg");
        Item i2 = new Item(2, "李宁运动鞋", "广州", 300, 500, "002.jpg");
        Item i3 = new Item(1, "沃特篮球鞋", "温州", 200, 500, "001.jpg");

        Cart c = new Cart();
        c.addGoodsInCart(i1, 1);
        c.addGoodsInCart(i2, 2);
        c.addGoodsInCart(i3, 5);

        Set<Map.Entry<Item, Integer>> items = c.getGoods().entrySet();
        for (Map.Entry<Item, Integer> obj: items) {
            System.out.println(obj);
        }

        System.out.println("购物车总金额：" + c.calTotalPrice());
    }
}
