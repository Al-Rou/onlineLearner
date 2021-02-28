<html>
<head><title>All answers</title></head>
<body>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>
<#list aufgabe as question>
    <h3>Course:</h3>${question.courseName}
    <br/>
    <h3>Task:</h3>${question.taskName}
    <br/>
    <h3>Description:</h3>${question.taskDescription}
</#list>
<table class="answertable">
    <tr>
        <th><h3>Received Answers</h3></th>
        <br/>
        ====================================
    </tr>
    <#list abgabe as answers>
        <tr>
            <td>${answers.abgabeText}<a href="/assess?kid=${answers.kID}&aid=${answers.aID}&anummer=${answers.aNummer}"><button>Evaluate this answer</button></a></td>
        </tr>
    </#list>
</table>
</body>
</html>