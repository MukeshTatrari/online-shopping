<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="shared/header.jsp"%>
<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

<div class="container">
	<c:if test="${not empty message}">
		<div class="row">
			<div class="col-xs-12 col-md-offset-2 col-md-8">
				<div class="alert alert-danger fade in" style="color: green">${message}</div>
			</div>
		</div>
	</c:if>


	<div class="row">

		<h1>Contact Us</h1>
		<hr />
		<div class="col-lg-12">

			<ol class="breadcrumb">

				<li><a href="${contextRoot}/home">Home</a></li>
				<li class="active">ContactUs</li>
			</ol>
		</div>


		<div class="panel-body col-md-8">

			<sf:form method="POST" class="form-horizontal" id="contactUs"
				modelAttribute="contactUs" action="${contextRoot}/contact">


				<div class="form-group">
					<label class="control-label col-md-4" for="name"> Your Name
					</label>
					<div class="col-md-8">
						<sf:input type="text" path="name" class="form-control"
							placeholder="Please Enter Your Name" />
						<sf:errors path="name" cssClass="help-block" element="em" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="email"> Your
						Email </label>
					<div class="col-md-8">
						<sf:input type="text" path="email" class="form-control"
							placeholder="Please Enter Your Email" />
						<sf:errors name="email" cssClass="help-block" element="em" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="Subject">Subject</label>
					<div class="col-md-8">
						<sf:input type="text" path="subject" class="form-control"
							placeholder="Subject" />
						<sf:errors name="subject" cssClass="help-block" element="em" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="phone">Phone
						Number </label>
					<div class="col-md-8">
						<sf:input type="number" path="phone" class="form-control"
							placeholder="Please Enter Your Phone" maxlength="10" />
						<sf:errors name="phone" cssClass="help-block" element="em" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="message">Message</label>
					<div class="col-md-8">
						<sf:textarea path="message" class="form-control"
							placeholder="Please Enter your Message!" />
						<sf:errors path="message" cssClass="help-block" element="em" />
					</div>
				</div>


				<div class="form-group">
					<div class="col-md-8" align="center">
						<button type="submit" id="sendEmail" class="btn btn-primary">
							<span class="glyphicon"></span> Send Message
						</button>
					</div>
				</div>


			</sf:form>


		</div>


		<div class="col-md-4">

			<h4>
				<span class="glyphicon glyphicon-envelope" style="color: blue"></span>
				Email:
			</h4>
			<hr />
			<h6>mukesh.tatrari@gmail.com</h6>

			<hr />


		</div>

		<div class="col-md-4">

			<h4>
				<span class="glyphicon glyphicon-phone-alt" style="color: blue"></span>
				Phone:
			</h4>
			<hr />
			<p>+918745058032</p>
			<p>+91944240103</p>

			<hr />


		</div>


		<div class="col-md-4">

			<h4>
				<span class="glyphicon glyphicon-home" style="color: blue"></span>
				Address:
			</h4>
			<hr />
			<p>1903-B Apex House</p>
			<p>Rajiv Nagar</p>
			<p>Gurgaon</p>
			<p>Haryana</p>
			<p>122001</p>

		</div>

	</div>

	<hr />

</div>