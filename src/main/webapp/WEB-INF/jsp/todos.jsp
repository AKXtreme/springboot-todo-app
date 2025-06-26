<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h2>Your Todos</h2>

    <table class="table table-bordered table-hover bg-white shadow-sm rounded">
        <thead class="thead-dark">
        <tr>
            <th>Description</th>
            <th>Target Date</th>
            <th>Done!</th>
            <th colspan="2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
            <tr class="todo-card">
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.completed}</td>
                <td>
                    <a href="delete-todo?id=${todo.id}" class="btn btn-warning btn-sm">Delete</a>
                </td>
                <td>
                    <a href="update-todo?id=${todo.id}" class="btn btn-success btn-sm">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a href="add-todo" class="btn btn-primary btn-lg shadow">Add New Todo</a>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
