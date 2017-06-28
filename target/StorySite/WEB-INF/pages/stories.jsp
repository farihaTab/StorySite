<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 4/30/2017
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Home Page</title>

    <style>
        input[type=text] {
            width: 130px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-image: url('searchicon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }

        input[type=text]:focus {
            width: 100%;
        }
    </style>

</head>
<body>
<section>

    <div class="searchform">
        <br/>
        <form:form method="POST" action="http://localhost:8080/StorySite/search">
            <input type="text" placeholder="Search.." id="searchValue" name="searchValue"/>
        </form:form>
    </div>

    <div class="jumbotron">
        <div class="container">
            <h1>All Stories</h1>
            <p>Stories, just for you ...</p>
        </div>
    </div>
</section>

<section class="container">
    <div class="row">
        <c:forEach items="${stories}" var="story">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <img src="<c:url value="/resource/images/storycover/${story.storyid}.jpg"></c:url>" alt="image"  style = "width:70%;height:70%;"/>
                    <div class="caption">
                        <h3>${story.title}</h3>
                        <p>${story.categoryname}</p>
                        <p><span class="glyphicon glyphicon-thumbs-up">  ${story.likecount} likes</p>
                        <p><span class="glyphicon glyphicon-ok">  ${story.readcount} reads</p>
                        <p>
                            <a href=" <spring:url value="/story/story?id=${story.storyid}" /> " class="btn btn-primary">
                                <span class="glyphicon-info-sign glyphicon"/></span> Details
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>