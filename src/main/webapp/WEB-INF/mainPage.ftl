<html>
<head><title>Main Page</title>

<body>
<form name="user" action="view_main" method="post">

    <#list mycourse as course>
        <br>${course}</br>
    </#list>

</form>
</body>
</html>