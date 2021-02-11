<html>
<head><title>Delete a course</title>

<body>
<br/>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>

<#list registered as kurs>
    <form name="user" action="delete" method="post">

        <h3>Course:</h3> ${kurs.courseName} <br/>
        <h3>Task:</h3> ${kurs.taskName} <br/>
        <h3>Description:</h3> ${kurs.taskDescription} <br/>
        <h3>Answer:</h3> <textarea name="answer" rows="10" cols="40"></textarea><br/>

        <input type="submit" value="deliver"/>
    </form>
</#list>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>