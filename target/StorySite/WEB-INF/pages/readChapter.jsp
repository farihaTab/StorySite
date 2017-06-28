<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<title>${story.storyid}-${chapter.chapterid}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    /* Note: Try to remove the following lines to see the effect of CSS positioning */
    .affix {
        top: 0;
        width: 100%;
    }

    .affixd + .container-fluid {
        padding-top: 70px;
    }
</style>
<body >

<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">

    <ul class="nav navbar-nav" >
        <li class="dropdown" ><a class="dropdown-toggle" data-toggle="dropdown" href="#">
            ${story.title}<span class="caret"></span></a>
            <%--<ul class="dropdown-menu">
                <li><a href="#">Page 1-1</a></li>
                <li><a href="#">Page 1-2</a></li>
                <li><a href="#">Page 1-3</a></li>
            </ul>--%>
            <ul class="dropdown-menu">
                <c:forEach items="${chapters}" var="item">
                    <li>
                        <a class="on-navigate" href=" <spring:url value="/story/chapter?id1=${chapter.belongsto}&id2=${item.chapterid}" /> ">${item.title}</a></td>
                    </li>
                </c:forEach>

            </ul>
        </li>
        <li><a href="#">Page 2</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">

        <li style="width:150px">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="glyphicon glyphicon-plus">
                </span>
            </a>
        </li>
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
        <%--<li style="width:150px"><a href="#"><span class="glyphicon glyphicon-star"></span>Vote</a></li>--%>
        <%--<li style="width:150px">
            <form:form  modelAttribute="formLikes" method = "POST" action = "/StorySite/story/like">
                <form:input id="likedby" path="likedby" type="hidden" value="${username}"/>
                <form:input id="likedstory" path="likedstory" type="hidden" value="${story.storyid}"/>
                <c:choose>
                    <c:when test="${liked==true}">
                        <form:button type="submit" name="like" value="Liked" class="btn btn-warning bt-lg">
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
        </li>--%>
    </ul>

</nav>

<div class="container">
    <%--writer details--%>
    <div class="row">
        <div class="col-sm-2">
            <div class="container">
                <div class="media-left">
                    <img src="<c:url value="/resource/images/profilephoto/${story.writerid}.jpg"></c:url>" alt="author-pic" class="media-object img-circle" style="width:60px;height:60px;">
                </div>
                <div class="media-body">
                    <h4 class="media-heading">By <a class="on-navigate" href=" <spring:url value="/user/username?id=${story.writerid}" /> " > ${story.writerid}</a></h4>
                    <div>
                        <form:form  modelAttribute="formFollow" method = "POST" action = "/StorySite/story/follow">
                            <form:input id="follower" path="follower" type="hidden" value="${username}"/>
                            <form:input id="followed" path="followed" type="hidden" value="${story.writerid}"/>
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
            </div>
        </div>

        <div class="col-sm-8">
            <div class="jumbotron" align="center">
                <h2>${chapter.title}</h2>
                <h6> ${story.readcount} reads ${story.likecount} likes ${story.commentcount} comments </h6>
            </div>
            <span style="display:block; height: 30px;"></span>
            <hr/>
            <span style="display:block; height: 30px;"></span>
            <%--chapter body--%>
            <div class="container col-sm-12">
                <p>${chapter.chapterbody}</p>
            </div>

            <span style="display:block; height: 10px;"></span>
            <%--pager--%>
            <div class="container">
                <ul class="pager">
                    <c:if test="${chapter.prevchapter!=0 and chapter.prevchapter!=chapter.chapterid}">
                        <li><a href=" <spring:url value="/story/chapter?id1=${story.storyid}&id2=${chapter.prevchapter}" /> ">Previous</a></li>
                    </c:if>
                    <c:if test="${chapter.nextchapter!=0}">
                        <li><a href=" <spring:url value="/story/chapter?id1=${story.storyid}&id2=${chapter.nextchapter}" /> ">Next</a></li>
                    </c:if>
                </ul>
            </div>

            <hr/>
            <span style="display:block; height: 10px;"></span>

            <%--comments & reviews--%>
            <div class="container col-sm-12">
                <div class="well" id="active-tabs">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#home">Comments</a></li>
                        <li><a data-toggle="tab" href="#menu1">Reviews</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="home" class="tab-pane fade in active">
                            <h3>Comments</h3>
                            <%--Comments--%>
                            <div class="well" id="comments">
                                <%--leave a comment--%>
                                <div class="row">
                                    <form:form modelAttribute="formChapterComment" method="POST" action="postCommentOnChapter">
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                <img src="<c:url value="/resource/images/profilephoto/${username}.jpg"></c:url>" alt="profilepic" class="img-circle" height="65" width="65" alt="Avatar">
                                            </div>
                                            <div class="col-sm-10">
                                                <form:textarea path="ccommenttext" class="form-control" rows="1" id="usercomment"  placeholder="Leave a comment"></form:textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-2 col-lg-10">
                                                <form:input path="ccommentedonstory" type="hidden" value="${story.storyid}"/>
                                                <form:input path="ccommentedonchapter" type="hidden" value="${chapter.chapterid}"/>
                                                <form:input path="ccommentedby" type="hidden" value="${username}"/>
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
                                                <img src="<c:url value="/resource/images/profilephoto/${comment.ccommentedby}.jpg"></c:url>" alt="user-profile-photo" class="media-object" style="width:60px">
                                            </div>
                                            <div class="media-body ">
                                                <h4 class="media-heading">
                                                    <a class="on-navigate" href=" <spring:url value="/user/username?id=${story.writerid}" /> " >
                                                            ${comment.ccommentedby}
                                                    </a>
                                                </h4>
                                                <p>${comment.ccommenttext}</p>
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
                                    <form:form modelAttribute="formChapterComment" method="POST" action="postCommentOnChapter">
                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <form:textarea path="ccommenttext" id="userReview" class="form-control" rows="1" placeholder="Leave a review"></form:textarea>
                                            </div>
                                            <div class="col-sm-2">
                                                <img src="<c:url value="/resource/images/profilephoto/${username}.jpg"></c:url>" alt="profile-pic" class="img-circle" height="65" width="65" alt="Avatar">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-8 col-lg-10">
                                                <form:input path="ccommentedonstory" type="hidden" value="${story.storyid}"/>
                                                <form:input path="ccommentedonchapter" type="hidden" value="${chapter.chapterid}"/>
                                                <form:input path="ccommentedby" type="hidden" value="${username}"/>
                                                <form:input path="scommenttype" type="hidden" value="review"/>
                                                <input type="submit" name="postComment" class="btn btn-primary" value ="Post"/>
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
                                                            ${comment.ccommentedby}
                                                    </a>
                                                </h4>
                                                <p>${comment.ccommenttext}</p>
                                            </div>
                                            <div class="media-right">
                                                <img src="<c:url value="/resource/images/profilephoto/${comment.ccommentedby}.jpg"></c:url>" alt="user-profile-photo" class="media-object" style="width:60px">
                                            </div>
                                        </div>
                                        <hr>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-2">
            <hr/>
        </div>
    </div>
</div>

<%--recommendation--%>

</body>
</html>
