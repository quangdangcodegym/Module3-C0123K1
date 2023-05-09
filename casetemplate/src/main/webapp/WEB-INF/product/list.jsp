<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/5/2023
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css" integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/js/bootstrap.min.js" integrity="sha512-7rusk8kGPFynZWu26OKbTeI+QPoYchtxsmPeBqkHIEXJxeun4yJ4ISYe7C6sz9wdxeE1Gk3VxsIWgCZTc+vX3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.5/sweetalert2.all.min.js" integrity="sha512-2JsZvEefv9GpLmJNnSW3w/hYlXEdvCCfDc+Rv7ExMFHV9JNlJ2jaM+uVVlCI1MAQMkUG8K83LhsHYx1Fr2+MuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head>
<body>
    <div class="container">
      <form action="/products?action=delete" method="post" id="frmDelete">
        <input id="txtDeleteId" type="hidden" name="id">
      </form>
      <table class="table table-hover">
        <thead>
        <tr>
          <th>Name</th>
          <th>Description</th>
          <th>Price</th>
          <th>Create at</th>
          <th>Category</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.products}" var="p">
            <tr>
              <td>${p.getName()}</td>
                <td>${p.getDescription()}</td>
                <td>${p.getPrice()}</td>
                <td>${p.getCreateAt()}</td>
                <td>
                  <c:forEach items="${requestScope.categories}" var="c">
                      <c:if test="${p.getIdCategory() == c.getId()}">
                        ${c.getName()}
                      </c:if>
                  </c:forEach>
                </td>
                <td>
                  <a href="/products?action=add"><i class="fa-solid fa-square-plus"></i></a>
                  <a href="/products?action=edit&id=${p.getId()}"><i class="fa-solid fa-pen-to-square"></i></a>
                  <a href="javascript:;" onclick="handleDelete(${p.getId()}, '${p.getName()}')"><i class="fa-solid fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <script>
      function handleDelete(id, title) {
        document.getElementById("txtDeleteId").value = id;
        Swal.fire({
          title: 'Bạn có muốn xóa?',
          text: title,
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes!'
        }).then((result) => {
          if (result.isConfirmed) {
              document.getElementById("frmDelete").submit();
          }
        })

      }
    </script>
</body>
</html>
