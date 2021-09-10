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
    <link rel="stylesheet" type="text/css" href="/css/makebet.css">
    <title>Главная страница</title>
</head>
<body>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <p class="h5 my-0 me-md-auto fw-normal"><a href="/" class="nav-link">ВЫИГРЫШЕЙ.NET</a></p>
    <c:choose>
        <c:when test="${id==1}">
            <nav class="my-2 my-md-0 me-md-3">
                <a class="p-2 text-dark" href="/profile">Профиль</a>
            </nav>
            <nav class="my-2 my-md-0 me-md-3">
                <a class="p-2 text-dark" href="/admin">Администрирование</a>
            </nav>
            <div class="my-2 my-md-0 me-md-3">
                <a class="btn btn-outline-primary" href="/logout">Выйти</a>
            </div>
        </c:when>
        <c:otherwise>
            <nav class="my-2 my-md-0 me-md-3">
                <a class="p-2 text-dark" href="/profile">Профиль</a>
            </nav>
            <div class="my-2 my-md-0 me-md-3">
                <a class="btn btn-outline-primary" href="/logout">Выйти</a>
            </div>
        </c:otherwise>
    </c:choose>
</header>

<main class="container">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">Выигрышей.нет (и не будет)</h1>
        <p class="lead">Самые высокие коэффициенты (если брать параллельную вселенную),
            лучшая контора, только у нас вы можете обогатиться
            (трехэтажными матами, которые добавятся в Ваш словарный, если решитесь ставить у нас).</p>
    </div>
    <p class="lead">${error}</p>
    <div class="row row-cols-1 row-cols-md-1 mb-3 text-center">
        <div class="col">
            <div class="card mb-4 shadow-sm">
                <div class="card-header">
                    <h4 class="my-0 fw-normal">${match.getName()}</h4>
                </div>
                <div class="card-body">
                    <h1 class="card-title pricing-card-title">Дата ${match.getDateOfMatch()}</h1>
                    <form method="post" action="/changematch" class="form">
                        <input type="hidden" value="${match.getId()}" name="id">
                        <label for="team">Выберите победившую команду</label>
                        <input type="number" min="1" max="2" placeholder="1" id="team" required name="team">
                        <button type="submit" class="btn btn-sm btn-outline-secondary">Изменить</button>
                    </form>
                </div>
            </div>
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