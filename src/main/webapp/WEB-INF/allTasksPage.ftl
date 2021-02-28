<html>
<head><title>All tasks</title></head>
<body>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>
<#list kurse as course>
    <h3>Click on each task to see all received answers</h3>
    <br/>
    <table class="coursetable">
        <tr>
            <th>List of all tasks for ${course.courseName}</th>
        </tr>
        <tr>
            <td><a href="/all_answers?kid=${course.kID}&anummer=${course.aNummer}">${course.taskName}</a> </td>
        </tr>
    </table>
</#list>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>