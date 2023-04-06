<!DOCTYPE html>
<html lang="fr">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>When Is My Code Review?</title>

    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet" type="text/css">

    <!-- Custom Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top container-fluid" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">When Is My Code Review?</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle navlink" data-toggle="dropdown" href="#">
                        <i class="fa fa-gear fa-fw"></i> Gérer les code reviews <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="addProm"><i class="fa fa-users fa-fw"></i> Ajouter une promotion</a>
                        </li>
                        <li><a href="addmem"><i class="fa fa-user fa-fw"></i> Ajouter un membre</a>
                        </li>
                        <li><a href="addReview"><i class="fa fa-calendar fa-fw"></i> Créer un rendez-vous</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>

        <div id="page-wrapper" class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
<c:choose>
                                <c:when test="${email!=null}">
                    <h1 class="page-header">Modifier un membre</h1>
                                </c:when>
                                    <c:otherwise>
                    <h1 class="page-header">Ajouter un membre</h1>
                                    </c:otherwise>
                                </c:choose>

                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form action="${pageContext.request.contextPath}/addmem" method="post" class="">
                                        <c:choose>
                                        <c:when test="${email!=null}">
                                            <input type="hidden" value="${id}" name="id">
                                            <div class="form-group">
                                                <label for="name">Nom</label>
                                                <input type="text" class="input-lg form-control"  value="${name}" name="name"  placeholder="Nom">
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Adresse Email</label>
                                                <input type="email" class="input-lg form-control" value="${email}" name="email" id="email" placeholder="Adresse Email">
                                                </c:when>
                                                <c:otherwise>
                                                <div class="form-group">
                                                    <label for="name">Nom</label>
                                                    <input type="text" class="input-lg form-control" id="name"  name="name" placeholder="Nom">
                                                </div>
                                                <div class="form-group">
                                                    <label for="email">Adresse Email</label>
                                                    <input type="email" class="input-lg form-control"  name="email" id="email" placeholder="Adresse Email">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="form-group">
                                            <label for="promotion">Promotion</label>
                                            <select class="input-lg form-control" name="classes_selected" id="promotion">
                                                <c:forEach items="${classes}" var="classes">
                                                    <c:set var="class_id" value="${classes.name.concat(' , ').concat(classes.id)}" scope = "page"/>
                                                    <option value= <c:out value="${classes.id}"/> > <c:out value="${classes.name}"/></option>

                                                </c:forEach>
                                            </select>
                                        </div>

                                        <c:choose>
                                                <c:when test="${email!=null}">
                                                    <div class="text-right">
                                                        <button type="submit" name="updateMem" class="btn btn-lg btn-primary">Enregistrer</button>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="text-right">
                                                        <button type="submit" name="addMem" class="btn btn-lg btn-primary">Enregistrer</button>
                                                    </div>
                                                </c:otherwise>
                                                </c:choose>
                                    </form>
                                </div>
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    <footer class="footer">
        <div class="container">
            <div class="row text-center">
                <img src="img/ebusiness.png" class="logo" alt=""> &bullet; 2017
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="js/jquery-3.1.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
