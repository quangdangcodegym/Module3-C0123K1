<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/5/2023
  Time: 11:46 AM
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
</head>
<body>
    <div class="container mt-5">
      <div class="row justify-content-center">
          <form method="post">
            <div class="col-6">
                <div class="row mb-2">
                    <label class="col-4">Name</label>
                    <input name="name" class="col-8 form-control"  />
                </div>
              <div class="row mb-2">
                  <label class="col-4">Description</label>
                  <input name="description" class="col-8 form-control"  />
              </div>
              <div class="row mb-2">
                  <label class="col-4">Price</label>
                  <input name="price" class="col-8 form-control"  />
              </div>
              <div class="row mb-2">
                  <label class="col-4">Create at</label>
                  <input name="create_at" type="date" class="col-8 form-control"  />
              </div>
              <div class="row mb-2">
                  <label class="col-4">Category</label>
                  <select name="category">
                      <c:forEach items="${requestScope.categories}" var="c">
                          <option value="${c.getId()}">${c.getName()}</option>
                      </c:forEach>
                  </select>
              </div>
              <div class="row">
                  <input type="submit" class="col-8 offset-4 btn btn-primary"  />
              </div>
          </div>
          </form>
      </div>
    </div>
</body>
</html>
