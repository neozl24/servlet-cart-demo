<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="entity.Item"%>
<%@ page import="dao.ItemsDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>商品展示</title>

  <style type="text/css">
    table {
      margin: 0 auto;
      width: 80%;
    }
    div{
      margin-left: 60px;
      margin-right:60px;
      margin-top: 5px;
      margin-bottom: 5px;
    }
    div dt a img {
      border: 1px solid #CCC;
      padding: -1px;
    }
    div dd{
      margin:0 auto;
      font-size:10pt;
    }
    div dd.dd_name

    {
      color:#2060C0;
      text-align: center;
      height: 25px;
      line-height: 25px;
    }
    div dd.dd_info
    {
      color:#000;
    }
  </style>

</head>
<body>
<h1>商品展示</h1>
<hr>
<table cellpadding="0" cellspacing="0" border="0">
  <%
    ItemsDAO itemsDAO = new ItemsDAO();
    ArrayList<Item> list = itemsDAO.getAllItems();
    int rowCapacity = 4;
    if(list != null) {
      int rows = (int) Math.ceil(list.size() / (double)rowCapacity );
      for (int i = 0; i < rows; i ++) {
  %>
  <tr>
    <%
      for (int j = i * rowCapacity; j < Math.min(list.size(), (i+1) * rowCapacity ); j ++) {
    %>
    <td>
      <!--商品循环开始-->
      <%
        Item item = list.get(j);
      %>
      <div>
        <dl>
          <dt>
            <a href="details.jsp?id=<%=item.getId()%>"><img src="images/<%=item.getPicture() %>" width="120" height="90" border="1"/></a>
          </dt>
          <dd class="dd_name"><%=item.getName() %></dd>
          <dd class="dd_info">
            产地: <%=item.getCity() %>
            <br>
            价格: <%=item.getPrice() %>￥
          </dd>
        </dl>
      </div>
      <br>
      <!--商品循环结束-->
    </td>
    <%
      }
    %>
  </tr>
  <%
      }
    }
  %>
</table>
</body>
</html>
