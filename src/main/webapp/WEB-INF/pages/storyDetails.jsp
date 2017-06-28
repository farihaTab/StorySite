<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 4/30/2017
  Time: 7:32 PM
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
    <title>${story.title}</title>

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
    <div class="row">
        <%--picture column--%>
        <div class="col-sm-offset-1 col-sm-2">
            <img src="<c:url value="/resource/images/storycover/${story.storyid}.jpg"></c:url>" alt="book-cover"  style = "width:200px;height:300px;"/>
        </div>
            <%--story name etc...--%>
        <div class="col-sm-8">
            <%--story name--%>
            <div class="container">
                <h1>${story.title}</h1>
            </div>
                <%--read & vote & chapter coutn--%>
            <div class="container">
                <%--<span data-toggle="tooltip" data-placement="bottom" title="" data-original-title="11,430 Votes">--%>
                <p>
                    ${story.readcount} Reads<span style="padding-left:40px;"></span>
                    ${story.likecount} Likes<span style="padding-left:40px;"></span>
                    ${story.chaptercount} Part Story<span style="padding-left:40px;"></span>
                </p>
                    <span style="display:block; height:50px;"></span>
            </div>
            <%--author name & pic--%>
            <div class="container">
                <div class="media-left">
                    <img src="<c:url value="/resource/images/profilephoto/${story.writerid}.jpg"></c:url>" alt="author-pic" class="media-object img-circle" style="width:60px;height:60px;">
                </div>
                <div class="media-body">
                    <h4 class="media-heading">By <a class="on-navigate" href=" <spring:url value="/user/username?id=${story.writerid}" /> " > ${story.writerid}</a></h4>
                    <p>
                    <c:choose>
                        <c:when test="${story.iscompleted==false}">
                            Ongoing
                        </c:when>
                        <c:otherwise>
                            Completed
                            <br />
                        </c:otherwise>
                    </c:choose>
                    </p>
                </div>
            </div>
                <%--read & + buttons --%>
            <div class="container">

                <c:choose>
                    <c:when test="${not empty reading}">
                        <a class="btn btn-warning" href=" <spring:url value="/story/chapter?id1=${story.storyid}&id2=${reading.readingpace}" /> ">Continue</a>
                    </c:when>
                    <c:otherwise>
                        <form:form  modelAttribute="formReading" method = "POST" action = "/StorySite/story/readstory">
                            <form:input id="readBy" path="readby" type="hidden" value="${username}"/>
                            <form:input id="readStory" path="readstory" type="hidden" value="${story.storyid}"/>
                            <form:input id="readingPace" path="readingpace" type="hidden" value="${story.firstchapter}"/>
                            <input type="submit" name="read" class="btn btn-warning" value ="Read"/>
                        </form:form>
                    </c:otherwise>
                </c:choose>


                    <a href="#" class="btn btn-warning dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <c:forEach items="${list}" var="item">
                            <li><a href="#">
                                ${item.listtitle}
                                <c:if test="${item.isprivate==true}">
                                    (private)
                                </c:if>
                            </a></li>
                        </c:forEach>
                    </ul>
                    <form:form  modelAttribute="formLikes" method = "POST" action = "/StorySite/story/like">
                        <form:input id="likedby" path="likedby" type="hidden" value="${username}"/>
                        <form:input id="likedstory" path="likedstory" type="hidden" value="${story.storyid}"/>
                            <c:choose>
                                <c:when test="${liked==true}">
                                    <form:button type="submit" name="like" value="Liked" class="btn btn-warning">
                                    <span class="glyphicon glyphicon-star" style="color:red"></span>Liked
                                    </form:button>
                                </c:when>
                                <c:otherwise>
                                    <form:button type="submit" name="like" value="Like" class="btn btn-warning">
                                    <span class="glyphicon glyphicon-star-empty" style="color: red"></span> Like
                                    </form:button>
                                </c:otherwise>
                            </c:choose>
                    </form:form>
            </div>
        </div>
    </div>
</div>

<%--description & others--%>
<div class="container ">
    <div class="row">
        <div class="col-sm-8">
            <div class="jumbotron" id="description">
                <p>
                    Story Description : ${story.description}
                </p>
            </div>
            <div class="jumbotron" id="tags">
                <%--<h3>Category: ${story.categoryname}</h3>--%>
                <p>
                    <span class="label label-warning">${story.categoryname}</span><span style="display:inline-block; width: 20px;"></span>
                    <c:forEach items="${storytags}" var="tag">
                        <span class="label label-warning">
                            <a class="on-navigate" href=" <spring:url value="/tags/tag?id=${tag.tagname}" /> " >
                                ${tag.tagname}
                            </a>
                            <%--<form:form method="POST" action="http://localhost:8080/StorySite/search">--%>
                                <%--<form:button id="searchValue" name="searchValue" value="${tag.tagname}">--%>
                                    <%--${tag.tagname}--%>
                                <%--</form:button>--%>
                            <%--</form:form>--%>
                        </span>
                        <span style="display:inline-block; width: 20px;"></span>
                    </c:forEach>
                </p>
            </div>
            <div class="well" id="active-tabs">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#home">Comments</a></li>
                    <li><a data-toggle="tab" href="#menu1">Reviews</a></li>
                    <li><a data-toggle="tab" href="#menu2">Table of contents </a></li>
                </ul>
                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active">
                        <h3>Comments</h3>
                        <%--Comments--%>
                        <div class="well" id="comments">
                            <%--leave a comment--%>
                            <div class="row">
                                <form:form modelAttribute="formStoryComment" method="POST" action="postCommentOnStory">
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <img src="<c:url value="/resource/images/profilephoto/${username}.jpg"></c:url>" alt="profilepic" class="img-circle" height="65" width="65" alt="Avatar">
                                        </div>
                                        <div class="col-sm-10">
                                            <form:textarea path="scommenttext" class="form-control" rows="1" id="usercomment"  placeholder="Leave a comment"></form:textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2 col-lg-10">
                                            <form:input path="scommentedon" type="hidden" value="${story.storyid}"/>
                                            <form:input path="scommentedby" type="hidden" value="${username}"/>
                                            <form:input path="scommenttype" type="hidden" value="normal"/>
                                            <input type="submit" name="postComment" class="btn btn-primary" value ="Post"/>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                            <!-- Left-aligned media object -->
                            <c:forEach items="${comments}" var="comment">
                                <c:if test="${comment.scommenttype!='review'}">
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="<c:url value="/resource/images/profilephoto/${comment.scommentedby}.jpg"></c:url>" alt="user-profile-photo" class="media-object" style="width:60px">
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">
                                                <a class="on-navigate" href=" <spring:url value="/user/username?id=${story.writerid}" /> " >
                                                    ${comment.scommentedby}
                                                </a>
                                            </h4>
                                            <p>${comment.scommenttext}</p>
                                        </div>
                                    </div>
                                    <hr>
                                </c:if>
                            </c:forEach>

                        </div>
                    </div>
                    <div id="menu1" class="tab-pane fade">
                        <h3>Review</h3>
                        <%--reviews--%>
                        <div class="well" id="reviews">
                            <%--leave a review--%>
                                <div class="row">
                                    <form:form modelAttribute="formStoryReview" method="POST" action="postReviewOnStory">
                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <form:textarea path="scommenttext" id="userReview" class="form-control" rows="1" placeholder="Leave a review"></form:textarea>
                                            </div>
                                            <div class="col-sm-2">
                                                <img src="<c:url value="/resource/images/profilephoto/${username}.jpg"></c:url>" alt="profile-pic" class="img-circle" height="65" width="65" alt="Avatar">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-8 col-lg-10">
                                                <form:input path="scommentedon" type="hidden" value="${story.storyid}"/>
                                                <form:input path="scommentedby" type="hidden" value="${username}"/>
                                                <form:input path="scommenttype" type="hidden" value="review"/>
                                                <input type="submit" name="postReview" class="btn btn-primary" value ="Post"/>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            <%--review--%>
                                <c:forEach items="${comments}" var="comment">
                                    <c:if test="${comment.scommenttype=='review'}">
                                        <div class="media">

                                            <div class="media-body">
                                                <h4 class="media-heading">
                                                    <a class="on-navigate" href=" <spring:url value="/user/username?id=${story.writerid}" /> " >
                                                            ${comment.scommentedby}
                                                    </a>
                                                </h4>
                                                <p>${comment.scommenttext}</p>
                                            </div>
                                            <div class="media-right">
                                                <img src="<c:url value="/resource/images/profilephoto/${comment.scommentedby}.jpg"></c:url>" alt="user-profile-photo" class="media-object" style="width:60px">
                                            </div>
                                        </div>
                                        <hr>
                                    </c:if>
                                </c:forEach>
                        </div>
                            <hr>
                    </div>
                    <div id="menu2" class="tab-pane fade">
                        <h3>Table of Contents</h3>
                        <%--Table of Contents--%>
                        <table class="table table-hover">
                            <%--<thead>--%>
                            <%--<tr>--%>
                                <%--<th>Chapter 1</th>--%>
                            <%--</tr>--%>
                            <%--</thead>--%>
                            <tbody>
                            <c:forEach items="${chapters}" var="chapter">
                                <tr>
                                    <td><a class="on-navigate" href=" <spring:url value="/story/chapter?id1=${story.storyid}&id2=${chapter.chapterid}" /> ">${chapter.title}</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="well" id="details">
                <div class="row">
                    <div >#Category</div>
                </div>
                <div class="row">
                    <div >#Copyright</div>
                </div>
                <div class="row">!Report this story( need to add link)</div>
            </div>
            <div class="well" id="recommendations">
                    <h2>You'll also Like</h2>
                    <div class="well">
                        <div class="media">
                            <div class="media-left">
                                <img src="img_avatar1.png" alt="story cover" class="media-object" style="width:80px;height:120px;">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Story name</h4>
                                <h5> by @authorname</h5>
                                <p>Story description partial</p>
                            </div>
                        </div>
                        <hr>
                        <div class="media">
                            <div class="media-left">
                                <img src="img_avatar1.png" alt="story cover" class="media-object" style="width:80px;height:120px;">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Story name</h4>
                                <h5> by @authorname</h5>
                                <p>Story description partial</p>
                            </div>
                        </div>
                        <hr>
                        <div class="media">
                            <div class="media-left">
                                <img src="img_avatar1.png" alt="story cover" class="media-object" style="width:80px;height:120px;">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Story name</h4>
                                <h5> by @authorname</h5>
                                <p>Story description partial</p>
                            </div>
                        </div>
                        <hr>
                    </div>
            </div>
        </div>
    </div>
</div>

<hr>

<script type="text/javascript">
    var theButton = document.getElementById('review');

    theButton.onclick = function() {
        document.getElementById('comments').style.visibility='hidden';
    }

</script>
</body>
</html>