<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/${theme}.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/view-resume-style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="scrollable-panel">
    <div class="form-wrapper">
        <div class="full-name">${resume.fullName}
            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=${theme}">
                <img src="img/${theme}/edit.svg" alt="">
            </a>
        </div>
        <div class="contacts">
            <c:forEach var="contactEntry" items="${resume.contacts}">
                <jsp:useBean id="contactEntry"
                             type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>

                <div><%=contactEntry.getKey().toHtml(contactEntry.getValue())%>
                </div>
            </c:forEach>
        </div>

        <div class="spacer"></div>

        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
            <div class="section">${type.title}</div>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <c:set var="textOBJECTIVE" value="${section}"/>
                    <jsp:useBean id="textOBJECTIVE" type="com.urise.webapp.model.TextSection"/>
                    <div class="position"><%=textOBJECTIVE.getContent()%>
                    </div>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <c:set var="textPERSONAL" value="${section}"/>
                    <jsp:useBean id="textPERSONAL" type="com.urise.webapp.model.TextSection"/>
                    <div class="qualities"><%=textPERSONAL.getContent()%>
                    </div>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <c:set var="listSection" value="${section}"/>
                    <jsp:useBean id="listSection" type="com.urise.webapp.model.ListSection"/>
                    <ul class="list">
                        <c:forEach var="item" items="<%=listSection.getItem()%>">
                            <li>${item}</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:set var="listOrganization" value="${section}"/>
                    <jsp:useBean id="listOrganization" type="com.urise.webapp.model.OrganizationSection"/>
                    <c:forEach var="org" items="<%=listOrganization.getOrganizations()%>">
                        <div class="section-wrapper">
                            <c:choose>
                                <c:when test="${empty org.homePage.website}">
                                    <div class="job-name">${org.homePage.name}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="job-name"><a class="contact-link"
                                                             href="${org.homePage.website}">${org.homePage.name}</a></div>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="position" items="${org.positions}">
                                <jsp:useBean id="position" type="com.urise.webapp.model.Organization.Position"/>
                                <div class="period-position">
                                    <div class="period"><%=HtmlUtil.formatDates(position)%>
                                    </div>
                                    <div class="position">${position.title}</div>
                                </div>
                                <c:choose>
                                    <c:when test="${empty position.description}">
                                    </c:when>
                                    <c:otherwise>
                                        <div class="description">${position.description}</div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>

        <div class="footer-spacer"></div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
