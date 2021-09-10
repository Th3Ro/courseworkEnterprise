<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/profile.css">
    <title>Администрирование</title>
</head>
<body>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <p class="h5 my-0 me-md-auto fw-normal"><a href="/" class="nav-link">ВЫИГРЫШЕЙ.NET</a></p>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="/profile">Профиль</a>
    </nav>
    <div class="my-2 my-md-0 me-md-3">
        <a class="btn btn-outline-primary" href="/logout">Выйти</a>
    </div>
</header>

<main class="container">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">Добавление события</h1>
    </div>
    <div>
        <h3 class="display-4">Добавление матчей.</h3>
        <form method="post" action="match">
            <div class="form-group">
                <label for="inputName">Название события</label>
                <input type="text" class="form-control" id="inputName" name="name"
                       placeholder="Название события" required>
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Дата матча</label>
                <input type="date" class="form-control" id="dateOfBirth" name="date" placeholder="Дата" required>
            </div>
            <div class="form-group">
                <label for="koef1">Коэффициент на первую команду</label>
                <input type="number" class="form-control" id="koef1" name="koef1"
                       required step="0.01" min="0" placeholder="0,00">
            </div>
            <div class="form-group">
                <label for="koef2">Коэффициент на вторую команду</label>
                <input type="number" class="form-control" id="koef2" name="koef2"
                       required step="0.01" min="0" placeholder="0,00">
            </div>
            <button type="submit" class="btn btn-primary in-btn">Добавить событие</button>
            <p>${error}</p>
        </form>
    </div>

    <div>
        <h3 class="display-4">Изменение результатов матчей</h3>
        <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">
            <c:forEach var="match" items="${matches}">
                <div class="col">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            <h4 class="my-0 fw-normal">${match.getName()}</h4>
                        </div>
                        <div class="card-body">
                            <h1 class="card-title pricing-card-title">Дата ${match.getDateOfMatch()}</h1>
                        </div>
                        <form method="post" action="/match/${match.getId()}">
                            <button type="submit" class="btn btn-sm btn-outline-secondary">Изменить результат</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

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