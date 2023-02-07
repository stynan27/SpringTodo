<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
  <h1>Welcome ${name}</h1>
  <!-- <div>Your Password: ${password}</div> UNSAFE!!! -->
  <hr>
  <a href="list-todos">Manage</a>
</div>
<%@ include file="common/footer.jspf" %>