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
  <link rel="stylesheet" href="/assets/style.css">
</head>
<body>
    <div class="container">

      <div class="row head-search">
            <div class="col-4">
          <button class="col-4 btn btn-primary mybutton">Add</button>
        </div>
          <form class="col-8 d-flex" action="/products" method="get">
                <input name="kw" type="text" class="form-control mysearch" value="${requestScope.kw}">
                <select name="idcategory" class="form-control mybutton">
                    <option value="-1">ALL</option>
                    <c:forEach items="${requestScope.categories}" var="c">
                        <option value="${c.getId()}"
                                <c:if test="${requestScope.idcategory == c.getId()}">selected</c:if>
                        >${c.getName()}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-primary mybutton">Search</button>
          </form>
      </div>
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
        <ul class="pagination justify-content-end">
            <c:if test="${requestScope.currentPage != 1}">
                <li class="page-item"><a class="page-link" href="/products?kw=${requestScope.kw}&page=${requestScope.currentPage-1}&idcategory=${requestScope.idcategory}&limit=${requestScope.limit}">Previous</a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${requestScope.noOfPages}">
                <c:choose>
                    <c:when test="${i == requestScope.currentPage}">
                        <li class="page-item active"><a class="page-link" href="/products?kw=${requestScope.kw}&page=${i}&idcategory=${requestScope.idcategory}&limit=${requestScope.limit}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/products?kw=${requestScope.kw}&page=${i}&idcategory=${requestScope.idcategory}&limit=${requestScope.limit}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${requestScope.currentPage < requestScope.noOfPages}">
                <li class="page-item"><a class="page-link" href="/products?kw=${requestScope.kw}&page=${requestScope.currentPage+1}&idcategory=${requestScope.idcategory}&limit=${requestScope.limit}">Next</a></li>
            </c:if>
        </ul>
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
