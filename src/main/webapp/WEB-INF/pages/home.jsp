<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="homeNavigation.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/21/2017
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .carousel-control.left, .carousel-control.right {
            background-image:none;
        }

        .img-responsive{
            width:160px;
            height:250px;
        }
        .carousel-inner{
            width:auto;
            height:250px;
        }

        @media (min-width: 992px ) {
            .carousel-inner .active.left {
                left: -25%;
            }
            .carousel-inner .next {
                left:  25%;
            }
            .carousel-inner .prev {
                left: -25%;
            }
        }

        @media (min-width: 768px) and (max-width: 991px ) {
            .carousel-inner .active.left {
                left: -33.3%;
            }
            .carousel-inner .next {
                left:  33.3%;
            }
            .carousel-inner .prev {
                left: -33.3%;
            }
            .active > div:first-child {
                display:block;
            }
            .active > div:first-child + div {
                display:block;
            }
            .active > div:last-child {
                display:none;
            }
        }

        @media (max-width: 767px) {
            .carousel-inner .active.left {
                left: -100%;
            }
            .carousel-inner .next {
                left:  100%;
            }
            .carousel-inner .prev {
                left: -100%;
            }
            .active > div {
                display:none;
            }
            .active > div:first-child {
                display:block;
            }
        }
    </style>

    <script>
        jQuery(document).ready(function() {

            jQuery('.carousel[data-type="multi"] .item').each(function(){
                var next = jQuery(this).next();
                if (!next.length) {
                    next = jQuery(this).siblings(':first');
                }
                next.children(':first-child').clone().appendTo(jQuery(this));

                for (var i=0;i<2;i++) {
                    next=next.next();
                    if (!next.length) {
                        next = jQuery(this).siblings(':first');
                    }
                    next.children(':first-child').clone().appendTo($(this));
                }
            });

        });
    </script>

    <title>Title</title>
</head>


<body>

<c:choose>
    <c:when test="${not empty recommendedStories}">
<div class="container">
    <h2>Recommended Stories</h2>
    <h4>A fresh set of stories, just for you</h4>

    <span style="display:block; height: 20;"></span> <%--vertical space--%>

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel0">

        <div class="carousel-inner">
            <c:set var="idx" value="${0}"/>
            <c:forEach items="${recommendedStories}" var="story">
                <c:choose>
                    <c:when test="${idx==0}">
                        <div class="item active" >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                ${story.storyEntity.title}</a></h4>
                                        <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                            by ${story.storyEntity.writerid}</a></h5>
                                        <h5>${story.storyEntity.categoryname}</h5>
                                        <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                        <c:choose>
                                            <c:when test="${story.storyEntity.iscompleted==false}">
                                                <p>Ongoing</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>Completed</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p> ${story.storyEntity.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="item" >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                ${story.storyEntity.title}</a></h4>
                                        <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                            by ${story.storyEntity.writerid}</a></h5>
                                        <h5>${story.storyEntity.categoryname}</h5>
                                        <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                        <c:choose>
                                            <c:when test="${story.storyEntity.iscompleted==false}">
                                                <p>Ongoing</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>Completed</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p> ${story.storyEntity.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var="idx" value="${idx+1}"/>
            </c:forEach>
        </div>

        <a class="left carousel-control" href="#myCarousel0" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel0" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
    </c:when>
</c:choose>

<%--<div class="container">
    <h2>Trending</h2>
    <h4>Stories that are gaining popularity</h4>

    <span style="display:block; height: 20;"></span> &lt;%&ndash;vertical space&ndash;%&gt;

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <c:set var="idx" value="${0}"/>
            <c:forEach items="${trendingStories}" var="story">
                <c:choose>
                    <c:when test="${idx==0}">
                        <div class="item active" >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4>${story.title}Story name</h4>
                                        <h5>by someone with link to his profile</h5>
                                        <h5>${story.categoryname}</h5>
                                        <h6> ${story.readcount} reads ${story.likecount} likes ${story.chaptercount} part story</h6>
                                        <p> ${story.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="item " >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4>${story.title}Story name</h4>
                                        <h5>by someone with link to his profile</h5>
                                        <h5>${story.categoryname}</h5>
                                        <h6> ${story.readcount} reads ${story.likecount} likes ${story.chaptercount} part story</h6>
                                        <p> ${story.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var="idx" value="${idx+1}"/>
            </c:forEach>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>--%>
<c:choose>
    <c:when test="${not empty continueReading}">
        <div class="container">
    <h2>Continue Reading</h2>
    <h4>Find more in your library</h4>

    <span style="display:block; height: 20;"></span> <%--vertical space--%>

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel1">

        <div class="carousel-inner">
            <c:set var="idx" value="${0}"/>
            <c:forEach items="${continueReading}" var="story">
                <c:choose>
                    <c:when test="${idx==0}">
                        <div class="item active" >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                ${story.storyEntity.title}</a></h4>
                                        <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                            by ${story.storyEntity.writerid}</a></h5>
                                        <h5>${story.storyEntity.categoryname}</h5>
                                        <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                        <c:choose>
                                            <c:when test="${story.storyEntity.iscompleted==false}">
                                                <p>Ongoing</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>Completed</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p> ${story.storyEntity.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="item " >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                ${story.storyEntity.title}</a></h4>
                                        <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                            by ${story.storyEntity.writerid}</a></h5>
                                        <h5>${story.storyEntity.categoryname}</h5>
                                        <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                        <c:choose>
                                            <c:when test="${story.storyEntity.iscompleted==false}">
                                                <p>Ongoing</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>Completed</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p> ${story.storyEntity.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var="idx" value="${idx+1}"/>
            </c:forEach>
        </div>
        <a class="left carousel-control" href="#myCarousel1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${not empty topStories}">
        <div class="container">
            <h2>Top Stories</h2>
            <h4>Stories liked the most</h4>

            <span style="display:block; height: 20;"></span> <%--vertical space--%>

            <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarouselTopStories">

                <div class="carousel-inner">
                    <c:set var="idx" value="${0}"/>
                    <c:forEach items="${topStories}" var="story">
                        <c:choose>
                            <c:when test="${idx==0}">
                                <div class="item active" >
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="row">
                                            <div class="col-md-5 col-sm-6">
                                                <a href="#">
                                                    <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-sm-6">
                                                <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                        ${story.storyEntity.title}</a>
                                                </h4>
                                                <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                                    by ${story.storyEntity.writerid}</a></h5>
                                                <h5>${story.storyEntity.categoryname}</h5>
                                                <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                                <c:choose>
                                                    <c:when test="${story.storyEntity.iscompleted==false}">
                                                        <p>Ongoing</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p>Completed</p>
                                                    </c:otherwise>
                                                </c:choose>
                                                <p> ${story.storyEntity.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="item" >
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="row">
                                            <div class="col-md-5 col-sm-6">
                                                <a href="#">
                                                    <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-sm-6">
                                                <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                        ${story.storyEntity.title}</a></h4>
                                                <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                                    by ${story.storyEntity.writerid}</a></h5>
                                                <h5>${story.storyEntity.categoryname}</h5>
                                                <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                                <c:choose>
                                                    <c:when test="${story.storyEntity.iscompleted==false}">
                                                        <p>Ongoing</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p>Completed</p>
                                                    </c:otherwise>
                                                </c:choose>
                                                <p> ${story.storyEntity.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="idx" value="${idx+1}"/>
                    </c:forEach>
                </div>

                <a class="left carousel-control" href="#myCarouselTopStories" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
                <a class="right carousel-control" href="#myCarouselTopStories" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

            </div>
        </div>
    </c:when>
</c:choose>

<c:forEach var="storiesbytag" items="${storiesByTags}" varStatus="status">

    <div class="container">
        <h2>&#35${storiesbytag.tag}</h2>
        <h4>A tag you may like</h4>

        <span style="display:block; height: 20;"></span> <%--vertical space--%>

        <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel${storiesbytag.tag}">

            <div class="carousel-inner">
                <c:set var="idx" value="${0}"/>
                <c:forEach items="${storiesbytag.stories}" var="story">
                    <c:choose>
                        <c:when test="${idx==0}">
                            <div class="item active" >
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-md-5 col-sm-6">
                                            <a href="#">
                                                <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                            </a>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                    ${story.storyEntity.title}</a></h4>
                                            <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                                by ${story.storyEntity.writerid}</a></h5>
                                            <h5>${story.storyEntity.categoryname}</h5>
                                            <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                            <c:choose>
                                                <c:when test="${story.storyEntity.iscompleted==false}">
                                                    <p>Ongoing</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Completed</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <p> ${story.storyEntity.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="item " >
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-md-5 col-sm-6">
                                            <a href="#">
                                                <img src="<c:url value="/resource/images/storycover/${story.storyEntity.storyid}.jpg"></c:url>" class="img-responsive">
                                            </a>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h4><a href=" <spring:url value="/story/story?id=${story.storyEntity.storyid}" /> ">
                                                    ${story.storyEntity.title}</a></h4>
                                            <h5><a href=" <spring:url value="/story/profile?username=${story.storyEntity.writerid}" /> ">
                                                by ${story.storyEntity.writerid}</a></h5>
                                            <h5>${story.storyEntity.categoryname}</h5>
                                            <h6> ${story.storyEntity.readcount} reads ${story.storyEntity.likecount} likes ${story.storyEntity.chaptercount} part story</h6>
                                            <c:choose>
                                                <c:when test="${story.storyEntity.iscompleted==false}">
                                                    <p>Ongoing</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Completed</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <p> ${story.storyEntity.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <c:set var="idx" value="${idx+1}"/>
                </c:forEach>
            </div>
            <a class="left carousel-control" href="#myCarousel${storiesbytag.tag}" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel${storiesbytag.tag}" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

        </div>
    </div>

    <div class="container">
        <h2>${trendingWriter[status.index].writerid}</h2>
        <h4>A trending profile you may like</h4>

        <span style="display:block; height: 20;"></span> <%--vertical space--%>

        <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel${trendingWriter[status.index].writerid}">

            <div class="carousel-inner">
                <c:set var="idx" value="${0}"/>
                <c:forEach items="${trendingWriter[status.index].storyEntities}" var="storyentity">
                    <c:choose>
                        <c:when test="${idx==0}">
                            <div class="item active" >
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-md-5 col-sm-6">
                                            <a href="#">
                                                <img src="<c:url value="/resource/images/storycover/${storyentity.storyid}.jpg"></c:url>" class="img-responsive">
                                            </a>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h4><a href=" <spring:url value="/story/story?id=${storyentity.storyid}" /> ">
                                                    ${storyentity.storyid}</a>
                                            </h4>
                                            <h5><a href=" <spring:url value="/story/profile?username=${storyentity.writerid}" /> ">
                                                by ${storyentity.writerid}</a></h5>
                                            <h5>${storyentity.categoryname}</h5>
                                            <h6> ${storyentity.readcount} reads ${storyentity.likecount} likes ${storyentity.chaptercount} part story</h6>
                                            <c:choose>
                                                <c:when test="${storyentity.iscompleted==false}">
                                                    <p>Ongoing</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Completed</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <p> ${storyentity.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="item " >
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-md-5 col-sm-6">
                                            <a href="#">
                                                <img src="<c:url value="/resource/images/storycover/${storyentity.storyid}.jpg"></c:url>" class="img-responsive">
                                            </a>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h4><a href=" <spring:url value="/story/story?id=${storyentity.storyid}" /> ">
                                                    ${storyentity.storyid}</a>
                                            </h4>
                                            <h5><a href=" <spring:url value="/story/profile?username=${storyentity.writerid}" /> ">
                                                by ${storyentity.writerid}</a></h5>
                                            <h5>${storyentity.categoryname}</h5>
                                            <h6> ${storyentity.readcount} reads ${storyentity.likecount} likes ${storyentity.chaptercount} part story</h6>
                                            <c:choose>
                                                <c:when test="${storyentity.iscompleted==false}">
                                                    <p>Ongoing</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Completed</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <p> ${storyentity.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <c:set var="idx" value="${idx+1}"/>
                </c:forEach>
            </div>
            <a class="left carousel-control" href="#myCarousel${trendingWriter[status.index].writerid}" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel${trendingWriter[status.index].writerid}" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

        </div>
    </div>

    <div class="container">
        <h2>${suggestedWriters[status.index].writerid}</h2>
        <h4>A suggested profile for you</h4>

        <span style="display:block; height: 20;"></span> <%--vertical space--%>

        <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel${suggestedWriters[status.index].writerid}">

            <div class="carousel-inner">
                <c:set var="idx" value="${0}"/>
                <c:forEach items="${suggestedWriters[status.index].storyEntities}" var="storyentity">
                    <c:choose>
                        <c:when test="${idx==0}">
                            <div class="item active" >
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-md-5 col-sm-6">
                                            <a href="#">
                                                <img src="<c:url value="/resource/images/storycover/${storyentity.storyid}.jpg"></c:url>" class="img-responsive">
                                            </a>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h4><a href=" <spring:url value="/story/story?id=${storyentity.storyid}" /> ">
                                                    ${storyentity.storyid}</a>
                                            </h4>
                                            <h5><a href=" <spring:url value="/story/profile?username=${storyentity.writerid}" /> ">
                                                by ${storyentity.writerid}</a></h5>
                                            <h5>${storyentity.categoryname}</h5>
                                            <h6> ${storyentity.readcount} reads ${storyentity.likecount} likes ${storyentity.chaptercount} part story</h6>
                                            <c:choose>
                                                <c:when test="${storyentity.iscompleted==false}">
                                                    <p>Ongoing</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Completed</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <p> ${storyentity.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="item " >
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-md-5 col-sm-6">
                                            <a href="#">
                                                <img src="<c:url value="/resource/images/storycover/${storyentity.storyid}.jpg"></c:url>" class="img-responsive">
                                            </a>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h4><a href=" <spring:url value="/story/story?id=${storyentity.storyid}" /> ">
                                                    ${storyentity.storyid}</a>
                                            </h4>
                                            <h5><a href=" <spring:url value="/story/profile?username=${storyentity.writerid}" /> ">
                                                by ${storyentity.writerid}</a></h5>
                                            <h5>${storyentity.categoryname}</h5>
                                            <h6> ${storyentity.readcount} reads ${storyentity.likecount} likes ${storyentity.chaptercount} part story</h6>
                                            <c:choose>
                                                <c:when test="${storyentity.iscompleted==false}">
                                                    <p>Ongoing</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Completed</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <p> ${storyentity.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <c:set var="idx" value="${idx+1}"/>
                </c:forEach>
            </div>
            <a class="left carousel-control" href="#myCarousel${suggestedWriters[status.index].writerid}" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel${suggestedWriters[status.index].writerid}" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

        </div>
    </div>
</c:forEach>


<%--
<div class="container">
    <h2>Recommended profiles</h2>
    <h4>Writers we think you'll love</h4>
&lt;%&ndash;todo: profile design & ndash;%&gt;
    <span style="display:block; height: 20;"></span> &lt;%&ndash;vertical space&ndash;%&gt;

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <c:set var="idx" value="${0}"/>
            <c:forEach items="${recommendedStories}" var="story">
                <c:choose>
                    <c:when test="${idx==0}">
                        <div class="item active" >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4>${story.title}Story name</h4>
                                        <h5>by someone with link to his profile</h5>
                                        <h5>${story.categoryname}</h5>
                                        <h6> ${story.readcount} reads ${story.likecount} likes ${story.chaptercount} part story</h6>
                                        <p> ${story.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="item " >
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="row">
                                    <div class="col-md-5 col-sm-6">
                                        <a href="#">
                                            <img src="<c:url value="/resource/images/storycover/${story.storyid}.jpg"></c:url>" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <h4>${story.title}Story name</h4>
                                        <h5>by someone with link to his profile</h5>
                                        <h5>${story.categoryname}</h5>
                                        <h6> ${story.readcount} reads ${story.likecount} likes ${story.chaptercount} part story</h6>
                                        <p> ${story.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var="idx" value="${idx+1}"/>
            </c:forEach>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
--%>

<%--
<div class="container">
    <h2>#dkjlss</h2>
    <h4>A trending tag</h4>

    <span style="display:block; height: 20;"></span> &lt;%&ndash;vertical space&ndash;%&gt;

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <div class="item active" >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/8.jpg"></c:url>" class="img-responsive" >
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item " >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
--%>



<%--
<div class="container">
    <h2>Stories by Leo tolostoy</h2>
    <h4>A trending profile</h4>

    <span style="display:block; height: 20;"></span> &lt;%&ndash;vertical space&ndash;%&gt;

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <div class="item active" >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/8.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item " >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
--%>


<%--
<div class="container">
    <h2>Stories by Radio active</h2>
    <h4>A profile we think you'll love</h4>

    <span style="display:block; height: 20;"></span> &lt;%&ndash;vertical space&ndash;%&gt;

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <div class="item active" >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/8.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item " >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
--%>

<%--

<div class="container">
    <h2>#tag</h2>
    <h4>A tag you may like</h4>

    <span style="display:block; height: 20;"></span> &lt;%&ndash;vertical space&ndash;%&gt;

    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <div class="item active" >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/8.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item " >
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="row">
                        <div class="col-md-5 col-sm-6">
                            <a href="#">
                                <img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive">
                            </a>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h4>Story name</h4>
                            <h5>by someone with link to his profile</h5>
                            <h5>genre with link</h5>
                            <h6> 0 reads 0 likes 0 part story</h6>
                            <p> description losrnmd peurm susatu usatn uast abskruk rual fuajrnlqu eklalilal iadlyalru udfal oanma aloma  fdjauru</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>
--%>


</body>
</html>
