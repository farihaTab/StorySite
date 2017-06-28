<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title><tiles:insertAttribute name="title" /></title>

  <link href="http://getbootstrap.com/dist/css/bootstrap.css"  rel="stylesheet">

  <link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css"  rel="stylesheet">

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Note: Try to remove the following lines to see the effect of CSS positioning */
    .affix {
      top: 0;
      width: 100%;
    }

    .affix + .container-fluid {
      padding-top: 70px;
    }
    .div-with-bg
    {
      background-image: url("image.jpg");
      background-position: top down ;
    }
  </style>

</head>

<body>


  <%--<div class="header">--%>
    <%--<ul class="nav nav-pills pull-right">--%>
      <%--<tiles:insertAttribute name="navigation" />--%>
    <%--</ul>--%>
    <%--<h3 class="text-muted">A Story Sharing Site</h3>--%>
  <%--</div>--%>

<div class="header" style="background-image: url(/WEB-INF/pages/image.jpg);">
    <div class="div-with-bg" >
      <div class="container-fluid" >
        <h1 align="center">Sobdokolpo</h1>
        <h3 align="center">A Story Sharing Site</h3>
      </div>
    </div>
</div>

    <nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
      <ul class="nav nav-pills pull-right">
        <tiles:insertAttribute name="navigation" />
      </ul>
    </nav>


  <%--<div class="jumbotron">--%>
    <%--<h1>--%>
      <%--<tiles:insertAttribute name="heading" />--%>
    <%--</h1>--%>
    <%--<p>--%>
      <%--<tiles:insertAttribute name="tagline" />--%>
    <%--</p>--%>
  <%--</div>--%>

  <div class="row">
    <tiles:insertAttribute name="content" />
  </div>

  <div class="footer">
    <tiles:insertAttribute name="footer" />
  </div>


</body>
</html>