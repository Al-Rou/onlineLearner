<html>
<head><title>All tasks</title></head>
<body>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>
<h3>Click on each task to see all received answers</h3>
    <table class="coursetable">
        <tr>
            <th>List of all tasks for ${courseName}</th>
        </tr>
        <#list kurse as course>
        <tr>
            <td><a href="/all_answers?kid=${course.kID}&anummer=${course.aNummer}">${course.taskName}</a></td>
            <td>${course.taskDescription}</td>
        </tr>
        </#list>
    </table>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>