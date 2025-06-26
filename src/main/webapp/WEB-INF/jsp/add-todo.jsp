<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <div class="card shadow-sm border-0 rounded-3 p-4">
                <h2 class="mb-4 text-center">Enter Todo Details</h2>

                <form:form method="post" modelAttribute="todo">
                    <!-- Description -->
                    <div class="form-group mb-3">
                        <form:label path="description" cssClass="form-label">Description</form:label>
                        <form:input path="description" cssClass="form-control" required="required"/>
                        <form:errors path="description" cssClass="text-danger"/>
                    </div>

                    <!-- Target Date -->
                    <div class="form-group mb-3">
                        <form:label path="targetDate" cssClass="form-label">Target Date</form:label>
                        <form:input path="targetDate" id="datepicker" cssClass="form-control" required="required"/>
                        <form:errors path="targetDate" cssClass="text-danger"/>
                    </div>

                    <!-- Hidden Fields -->
                    <form:input type="hidden" path="id"/>
                    <form:input type="hidden" path="completed"/>
                    <form:input type="hidden" path="username"/>

                    <!-- Submit -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-success btn-lg px-5">Save Todo</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
