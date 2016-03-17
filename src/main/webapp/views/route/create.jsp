<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

   <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
       <form:form class="contact_form" action="route/create.do" modelAttribute="tripForm">
        <h2 align="center">Please complete this form with your preferences to calculate a route:</h2>
          <br><br>
          <div class="container">
            <div class='col-md-5'>
                <div class="form-group">
                    <div class='input-group date' id='datetimepicker6'>
                        <form:input path="startingDate" class="form-control" placeholder="Enter date..." />
						<form:errors cssClass="error" path="startingDate" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <div class='col-md-5'>
                <div class="form-group">
                    <div class='input-group date' id='datetimepicker7'>
                        <form:input path="endDate" class="form-control" placeholder="Enter date..." />
						<form:errors cssClass="error" path="endDate" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <div class='col-md-2'>
              <div class="input-group">
                <form:input path="cost" class="form-control" placeholder="Budget..." />
				<form:errors cssClass="error" path="cost" />
                 <span class="input-group-addon">
                    <span class="glyphicon glyphicon-euro"></span>
                 </span>
              </div>
            </div>
          </div>
          <div class="container">
            <div class="row" >
            <div class="col-md-2"> 
            </div> 
              <div class="col-md-5">        
                 <div class="checkbox">
                    <label>
                      <input type="checkbox" value="">
                      Museums
                    </label>
                  </div>
                  <div class="checkbox">
                    <label>
                      <input type="checkbox" value="">
                      Churchs
                   </label>
                  </div>
                  <div class="checkbox">
                   <label>
                     <input type="checkbox" value="">
                     Restaurants
                   </label>
                  </div>
                  <div class="checkbox">
                   <label>
                     <input type="checkbox" value="">
                     Drinks
                   </label>
                  </div>
              </div>
              <div class="col-md-5">
                  <div class="checkbox">
                   <label>
                     <input type="checkbox" value="">
                     Monuments
                   </label>
                  </div>
                  <div class="checkbox">
                   <label>
                      <input type="checkbox" value="">
                      Paintings
                   </label>
                  </div>
                  <div class="checkbox">
                   <label>
                     <input type="checkbox" value="">
                     Music
                   </label>
                  </div>
                  <div class="checkbox">
                   <label>
                     <input type="checkbox" value="">
                     Trekking
                   </label>
                  </div>
              </div>
            </div>
          </div>
        <p align="right">
          	<input class="btn btn-lg btn-primary" type="submit" name="create"
					value="<spring:message code="route.createTrip" />" />&nbsp; 
            </p>
       </form:form>
      </div>
      </div> <!-- /container -->


<!--  
<form:form action="route/create.do" modelAttribute="tripForm">


	<form:label path="startingDate">
		<spring:message code="route.startingDate" />:
	</form:label>
	<form:input path="startingDate" />
	<form:errors cssClass="error" path="startingDate" />
	<br />
	
	<form:label path="endDate">
		<spring:message code="route.endDate" />:
	</form:label>
	<form:input path="endDate" />
	<form:errors cssClass="error" path="endDate" />
	<br />
	
	<form:label path="cost">
		<spring:message code="route.cost" />:
	</form:label>
	<form:input path="cost" />
	<form:errors cssClass="error" path="cost" />
	<br/>

	<form:label path="checkCulturalCategory">
		<spring:message code="route.checkCultural" />
	</form:label>
	<form:checkbox path="checkCulturalCategory"/>
	<form:errors class="error" path="checkCulturalCategory" />
	<br />
	
	<form:label path="checkMuseumsCategory">
		<spring:message code="route.checkMuseums" />
		</form:label>
	<form:checkbox path="checkMuseumsCategory"/>
	<form:errors class="error" path="checkMuseumsCategory" />
	<br />
	
	<form:label path="checkRestaurantsCategory">
		<spring:message code="route.checkRestaurants" />
	</form:label>
	<form:checkbox path="checkRestaurantsCategory"/>
	<form:errors class="error" path="checkRestaurantsCategory" />
	<br />
	
	<form:label path="checkChurchesCategory">
		<spring:message code="route.checkChurches" />
	</form:label>
	<form:checkbox path="checkChurchesCategory"/>
	<form:errors class="error" path="checkChurchesCategory" />
	<br />


	<input type="submit" name="create"
		value="<spring:message code="route.createTrip" />" />&nbsp; 


</form:form>

-->