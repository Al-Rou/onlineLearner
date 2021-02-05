<html>
<head><title>Main Page</title>

<body>
<form name="user" action="view_main" method="post">

    <h1>My Courses:</h1>
    <br/>
    <div>
        <#list myowncourse as owncourse>
            <li>Title:&nbsp;<a href="/view_course?kid=${owncourse.kID}">${owncourse.name}</a></li>
            <li>Producer: ${owncourse.ersteller}</li>
            <li>Free Seats: ${owncourse.freiePlaetze}</li>
            <li>***********************************</li>
        </#list>
    </div>
    <br/>
    <h1>Available Courses:</h1>
    <br/>
    <div>
    <#list mycourse as course>
        <li>Title:&nbsp;<a href="/view_course?kid=${course.kID}">${course.name}</a></li>
        <li>Producer: ${course.ersteller}</li>
        <li>Free Seats: ${course.freiePlaetze}</li>
        <li>***********************************</li>
    </#list>
    </div>

</form>
</body>
</html>