package servlet;

import dao.ItemsDAO;
import entity.Cart;
import entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhongli on 2017/2/4.
 */
@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {

    private String action;  //购物车的动作，可以是add, show, delete
    private ItemsDAO iDao = new ItemsDAO();     //商品业务逻辑类的对象

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");

            if(action.equals("add")) {
                if(addToCart(request, response) ) {
                    request.getRequestDispatcher("/success.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/failure.jsp").forward(request, response);
                }
            } else if (action.equals("show")) {
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else if (action.equals("delete")) {
                if(deleteFromCart(request, response)) {
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        Item item = iDao.getItemById(Integer.parseInt(id));
        Cart cart;

        //判断是否是第一次给购物车添加商品，如果是，需要给session创建一个新的购物车对象
        if (request.getSession().getAttribute("cart") == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        } else {
            cart = (Cart) request.getSession().getAttribute("cart");
        }

        Set<Map.Entry<Item, Integer>> items = cart.getGoods().entrySet();

        if (cart.addGoodsInCart(item, Integer.parseInt(num) ) ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Item item = iDao.getItemById(Integer.parseInt(id));

        if (cart.removeGoodsInCart(item) ) {
            return true;
        } else {
            return false;
        }
    }
}
