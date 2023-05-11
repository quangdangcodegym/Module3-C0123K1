<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Coron-login</title>
        <meta name="description" content="">
        <jsp:include page="/WEB-INF/frontend/layout/css_head.jsp"></jsp:include>
    </head>
    <body>
            <!-- Add your site or application content here -->
            
            <!--pos page start-->
            <div class="pos_page">
                <div class="container"> 
                    <!--pos page inner-->
                    <div class="pos_page_inner">  
                       <!--header area -->
                        <jsp:include page="/WEB-INF/frontend/layout/head_area.jsp"></jsp:include>
                        <!--header end -->
                         <!--breadcrumbs area start-->
                        <div class="breadcrumbs_area">
                            <div class="row">
                                    <div class="col-12">
                                        <div class="breadcrumb_content">
                                            <ul>
                                                <li><a href="index.html">home</a></li>
                                                <li><i class="fa fa-angle-right"></i></li>
                                                <li>login</li>
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                        </div>
                        <!--breadcrumbs area end-->

                       <!-- customer login start -->
                        <div class="customer_login">
                            <div class="row">
                                       <!--login area start-->
                                        <div class="col-lg-6 col-md-6">
                                            <div class="account_form">
                                                <h2>login</h2>
                                                <c:if test="${requestScope.message !=null}">
                                                    <div class="alert alert-danger">
                                                        <h4>${requestScope.message}</h4>
                                                    </div>
                                                </c:if>
                                                <form action="/login" method="post">
                                                    <p>   
                                                        <label>Username or email <span>*</span></label>
                                                        <input type="text" name="username">
                                                     </p>
                                                     <p>   
                                                        <label>Passwords <span>*</span></label>
                                                        <input type="password" name="password">
                                                     </p>   
                                                    <div class="login_submit">
                                                        <button type="submit">login</button>
                                                        <label for="remember">
                                                            <input id="remember" type="checkbox" name="rememberme">
                                                            Remember me
                                                        </label>
                                                        <a href="#">Lost your password?</a>
                                                    </div>

                                                </form>
                                             </div>    
                                        </div>
                                        <!--login area start-->

                                        <!--register area start-->
                                        <div class="col-lg-6 col-md-6">
                                            <div class="account_form register">
                                                <h2>Register</h2>
                                                <form action="#">
                                                    <p>   
                                                        <label>Email address  <span>*</span></label>
                                                        <input type="text">
                                                     </p>
                                                     <p>   
                                                        <label>Passwords <span>*</span></label>
                                                        <input type="password">
                                                     </p>
                                                    <div class="login_submit">
                                                        <button type="submit">Register</button>
                                                    </div>
                                                </form>
                                            </div>    
                                        </div>
                                        <!--register area end-->
                                    </div>
                        </div>
                        <!-- customer login end -->

                    </div>
                    <!--pos page inner end-->
                </div> 
            </div>
            <!--pos page end-->
            
            <!--footer area start-->
            <jsp:include page="/WEB-INF/frontend/layout/footer_area.jsp"></jsp:include>
            <!--footer area end-->
            
            
            
            
      
		
		<!-- all js here -->
        <jsp:include page="/WEB-INF/frontend/layout/js_footer.jsp"></jsp:include>
    </body>
</html>
