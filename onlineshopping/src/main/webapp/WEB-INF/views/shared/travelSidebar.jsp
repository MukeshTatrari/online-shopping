
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="list-group">
	<c:forEach items="${places}" var="places">
		<a href="${contextRoot}/travel/show/${places.id}/PlaceDetails"
			class="list-group-item" id="a_${places.name}"> ${places.name} </a>
	</c:forEach>
</div>
<!--  
<div class="list-group">
	<ul class="list-unstyled">
		<li><a href="${contextRoot}/places/placeDetails"
			class="list-group-item" id="a_"> Uttarakhand </a></li>
		<li><a href="${contextRoot}/places/placeDetails"
			class="list-group-item" id="a_"> Rajasthan </a></li>
		<li><a href="${contextRoot}/places/placeDetails"
			class="list-group-item" id="a_"> Gujrat </a></li>
		<li><a href="${contextRoot}/places/placeDetails"
			class="list-group-item" id="a_"> Kerala </a></li>
		<li><a href="${contextRoot}/places/placeDetails"
			class="list-group-item" id="a_"> Sikkim </a></li>

	</ul>
</div>
-->