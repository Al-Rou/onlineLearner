<html>
<head><title>Main Page</title>

<body>
<form name="user" action="view_main" method="post">

    <h1>My Courses:</h1>
    <br/>
    <div>
        <#list myowncourse as course>
            <li>Title: ${course.name}</li>
            <li>Producer: ${course.ersteller}</li>
            <li>Free Seats: ${course.freiePlaetze}</li>
            <li>***********************************</li>
        </#list>
    </div>
    <br/>
    <h1>Available Courses:</h1>
    <br/>
    <div>
    <#list mycourse as course>
        <li>Title: ${course.name}</li>
        <li>Producer: ${course.ersteller}</li>
        <li>Free Seats: ${course.freiePlaetze}</li>
        <li>***********************************</li>
    </#list>
    </div>

</form>
</body>
</html>