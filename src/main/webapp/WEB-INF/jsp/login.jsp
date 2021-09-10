<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <title>Авторизация</title>
</head>
<body>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <p class="h5 my-0 me-md-auto fw-normal"><a href="/" class="nav-link">ВЫИГРЫШЕЙ.NET</a></p>
    <div class="my-2 my-md-0 me-md-3">
        <a class="btn btn-primary" href="/registration">Зарегистрироваться</a>
    </div>
</header>

<main class="container">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">Авторизация</h1>
    </div>

    <form class="auth" method="post" action="login">
        <div class="form-group">
            <label for="inputLogin">Логин</label>
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Логин" required >
        </div>
        <div class="form-group">
            <label for="inputPassword">Пароль</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Пароль" required>
        </div>
        <button type="submit" class="btn btn-primary in-btn">Войти</button>
        <div>
            <p>${loginError}</p>
        </div>
    </form>

    <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
            <div class="col-12 col-md">
                <small class="d-block mb-3 text-muted">Все права защищены, не сомневайтесь!</small>
                <small class="d-block mb-3 text-muted">© 2020</small>
            </div>
        </div>
    </footer>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>