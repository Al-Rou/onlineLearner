<html>
<head><title>Main Page</title>

<body>
<form name="user" action="view_main" method="post">


    <#list mycourse as course>
        <tr>
            <td>${course}</td>
        </tr>
    </#list>

</form>
</body>
</html>