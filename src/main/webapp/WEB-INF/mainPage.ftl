<html>
<head><title>Main Page</title>

<body>
<form name="user" action="hello" method="post">
    Firstname: <input type="text" name="firstname" /> <br/>
    Lastname: <input type="text" name="lastname" /> <br/>
    <input type="submit" value="Save" />
</form>

<table class="datatable">
    <tr>
        <th>Firstname</th>
    </tr>
    <#list mycourse as course>
        <tr>
            <td>${course.name}</td>
        </tr>
    </#list>
</table>
</body>
</html>