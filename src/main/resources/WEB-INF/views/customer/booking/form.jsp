<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="customer.booking.form.label.locatorCode" path="locatorCode"/>	
	<acme:input-textbox code="customer.booking.form.label.purchaseMoment" path="purchaseMoment"/>	
	<acme:input-select code="customer.booking.form.label.travelClass" path="travelClass" choices="${travelClasses}"/>
	<acme:input-money code="customer.booking.form.label.totalPrice" path="totalPrice" readonly="true"/>
	<acme:input-textbox code="customer.booking.form.label.lastNibble" path="lastNibble"/>
	<jstl:if test="${_command == 'create'}">
    	<acme:input-select 
 code="customer.booking.form.label.flight" 
        path="flight" 
        choices="${flights}" />
</jstl:if>
	

	<jstl:if test="${hasPassengers}">
		<acme:input-textarea code="customer.booking.form.label.passengers" path="passengers" readonly="true"/>
	</jstl:if>

	<jstl:if test="${!hasPassengers}">
	        <p><em>No passengers for this booking.</em></p>
	</jstl:if>	

	<jstl:choose>
	    <jstl:when test="${(_command == 'update' || _command == 'show' || _command == 'publish') && isDraftMode}">
	        <acme:submit code="customer.booking.form.button.save" action="/customer/booking/update"/>
	    	<acme:submit code="customer.booking.form.button.publish" action="/customer/booking/publish"/>
	    	
	    </jstl:when>
	</jstl:choose>
		
	<jstl:if test="${_command == 'create'}">
		  <acme:submit code="customer.booking.form.button.create" action="/customer/booking/create"/>
	</jstl:if>


</acme:form>