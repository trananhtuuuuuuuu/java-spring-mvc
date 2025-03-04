<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> <!--Dòng này để sử dụng lấy dữ liệu sau khi nhập vào từ ô input-->


<!-- -->


<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create User</title>

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <link href="/css/demo.css" rel="stylesheet">
  </head>
  <body>
    <div class="container mt-5"> <!-- mt -  margin top-->
      <div class="row">
        <div class="col-md-6 col-12 mx-auto"> <!-- col-md-6 co nghia la tren mobile se full 12 column, tren may tinh thi 6 column-->
          <h3>Create a user</h3>
          <hr>
          <form:form action="/admin/user/create" method="post"
          modelAttribute="newUser">
            <div class="mb-3"> <!--margin bottom 3rem-->
              <label class="form-label">Email:</label>
              <form:input type="email" class="form-control"
              path="email"
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Password:</label>
              <form:input type="password" class="form-control"
              path="password"
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Phone number:</label>
              <form:input type="text" class="form-control"
              path="phone"
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Full Name:</label>
              <form:input type="text" class="form-control"
              path="fullName"
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Address:</label>
              <form:input type="text" class="form-control"
              path="address"
              />
            </div>
            <div class="mb-3">
              <button type="submit" class="btn btn-primary">Create</button>
            </div>
          </form:form>
        </div>
      </div>

    </div>
  </body>
</html>
