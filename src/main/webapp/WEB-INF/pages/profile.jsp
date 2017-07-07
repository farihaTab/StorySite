<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/7/2017
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>First Name Last Name (@username)</title>

    <style>
        textarea {
            height: 1em;
            width: 50%;
            padding: 3px;
            transition: all 0.5s ease;
        }
        textarea:focus {
            height: 6em;
        }
    </style>
</head>
<body>

<div class="jumbotron">
    <img src="<c:url value="/resource/images/profilephoto/${writerProfile.writer}.jpg"></c:url>" alt="author-pic" class="media-object img-circle" style="width:200px;height:200px;margin: auto;">
    <hr>
    <h3 style="text-align: center;">${writerProfile.firstName} ${writerProfile.lastName}</h3>
    <h3 style="text-align: center;">@${writerProfile.writer}</h3>
    <hr>
    <h5 style="text-align: center;"> ${writerProfile.bookcount} works <span style="padding-left:24px;"></span> ${writerProfile.followcount} followers</h5>
    <hr>
    <div align="center">
        <form:form  modelAttribute="formFollow" method = "POST" action = "/StorySite/story/follow">
            <form:input id="follower" path="follower" type="hidden" value="${username}"/>
            <form:input id="followed" path="followed" type="hidden" value="${writerProfile.writer}"/>
            <c:choose>
                <c:when test="${followed==true}">
                    <form:button type="submit" name="like" value="Followed" class="btn btn-warning">
                        <span class="glyphicon glyphicon-check" style="color:red"></span>Followed
                    </form:button>
                </c:when>
                <c:otherwise>
                    <form:button type="submit" name="like" value="Follow" class="btn btn-warning">
                        <span class="glyphicon glyphicon-unchecked" style="color: red"></span> Follow
                    </form:button>
                </c:otherwise>
            </c:choose>
        </form:form>

    </div>

</div>
<div class="container">

</div>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="well">
                <div class="row">
                    <%--<h4>location</h4>--%>
                    <h4>joined ${writerProfile.joinedAt}</h4>
                    <hr>
                    <div style="height: auto; overflow: auto;">
                        <h4>Following</h4>
                        <c:forEach items="${followingList}" var="following">
                        <div style="float: left; font-size: 9pt; text-align: center; width: 30%; margin-right: 1%; margin-bottom: 0.5em;">
                            <img src="<c:url value="/resource/images/profilephoto/${following.username}.jpg"></c:url>" alt="author-pic" title="username" class="media-object img-circle" style="width:60px;height:60px;margin: auto;">
                            <form:form  modelAttribute="formFollow" method = "POST" action = "/StorySite/story/follow">
                                <form:input id="follower" path="follower" type="hidden" value="${username}"/>
                                <form:input id="followed" path="followed" type="hidden" value="${following.username}"/>
                                <c:choose>
                                    <c:when test="${following.followed==true}">
                                        <form:button type="submit" name="like" value="Followed" class="btn btn-warning">
                                            <span class="glyphicon glyphicon-check" style="color:red"></span>Followed
                                        </form:button>
                                    </c:when>
                                    <c:otherwise>
                                        <form:button type="submit" name="like" value="Follow" class="btn btn-warning">
                                            <span class="glyphicon glyphicon-unchecked" style="color: red"></span> Follow
                                        </form:button>
                                    </c:otherwise>
                                </c:choose>
                            </form:form>
                        </div>
                        </c:forEach>
                    </div>
                    <hr>
                    <div style="height: auto; overflow: auto;">
                        <h4>Followed By</h4>
                        <c:forEach items="${followerList}" var="follower">
                        <div style="float: left; font-size: 9pt; text-align: center; width: 30%; margin-right: 1%; margin-bottom: 0.5em;">
                            <img src="<c:url value="/resource/images/profilephoto/${follower.username}.jpg"></c:url>" alt="author-pic" title="username" class="media-object img-circle" style="width:60px;height:60px;margin: auto;">
                            <form:form  modelAttribute="formFollow" method = "POST" action = "/StorySite/story/follow">
                                <form:input id="follower" path="follower" type="hidden" value="${username}"/>
                                <form:input id="followed" path="followed" type="hidden" value="${follower.username}"/>
                                <c:choose>
                                    <c:when test="${follower.followed==true}">
                                        <form:button type="submit" name="like" value="Followed" class="btn btn-warning">
                                            <span class="glyphicon glyphicon-check" style="color:red"></span>Followed
                                        </form:button>
                                    </c:when>
                                    <c:otherwise>
                                        <form:button type="submit" name="like" value="Follow" class="btn btn-warning">
                                            <span class="glyphicon glyphicon-unchecked" style="color: red"></span> Follow
                                        </form:button>
                                    </c:otherwise>
                                </c:choose>
                            </form:form>
                        </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-sm-8">
            <div class="well">
                <h3>About writer</h3>
                <hr>
                ${writerProfile.aboutwriter}
            </div>
            <div class="well">
                <h2>Stories by ${writerProfile.firstName}</h2>
                <div class="well" >
                    <c:forEach items="${works}" var="work">
                        <div class="row">
                                <div class="col-sm-2">
                                    <img src="<c:url value="/resource/images/storycover/${work.storyEntity.storyid}.jpg"></c:url>" alt="book-cover" class="media-object" style="width:80px;height:120px;">
                                </div>
                                <div class="col-sm-6">
                                    <h4 class="media-heading">${work.storyEntity.title}</h4>
                                    <p>${work.storyEntity.description}</p>
                                    <p>
                                        <c:forEach items="${work.tags}" var="tag">
                                                <a class="on-navigate" href=" <spring:url value="/tags/tag?id=${tag}" /> " >
                                                        ${tag}
                                                </a>
                                        </c:forEach>
                                    </p>
                                </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>