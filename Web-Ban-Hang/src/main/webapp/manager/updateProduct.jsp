<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 12/28/2021
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Sing Up</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="css/style.css">

</head>
<body class="img js-fullheight" style="background-image: url(/views/images/iu.jpg);">
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="login-wrap p-0">
                    <form action="/managerProduct?action=update&&id=${requestScope["productUpdate"].getId()}" class="singin-form" method="post">
                        <h3>Name</h3>
                        <div class="form-group">
                            <input  type="text" class="form-control" name="name" value="${requestScope["productUpdate"].getName()}" >
                        </div>
                        <h3>Price</h3>
                        <div class="form-group">
                            <input  type="number" class="form-control" name="price" value="${requestScope["productUpdate"].getPrice()}" >
                        </div>
                        <h3>Description</h3>
                        <div class="form-group">
                            <input type="text" class="form-control" name="description" value="${requestScope["productUpdate"].getDescription()}" >
                        </div>
                        <h3>Quantity</h3>
                        <div class="form-group">
                            <input type="number" class="form-control" name="quantity" value="${requestScope["productUpdate"].getQuantity()}" >
                        </div>
                        <h3>Img</h3>
                        <div class="form-group">
                            <input type="text" class="form-control" name="img" value="${requestScope["productUpdate"].getImg()}" >
                        </div>
                        <div class="form-group">
                            <button type="submit" class="form-control btn btn-primary submit px-3">Update</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>

