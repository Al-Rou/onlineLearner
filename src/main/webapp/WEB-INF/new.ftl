<html>
<head><title>New Page</title>

<body>
<form name="hellosayer" action="hi" method="get">
    <#list hellosayers as hellosayer>
        <tr>
            <td>${hellosayer}</td>
        </tr>
    </#list>
</form>
</body>
</html>