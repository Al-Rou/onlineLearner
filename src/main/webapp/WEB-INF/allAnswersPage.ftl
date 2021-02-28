<html>
<head><title>All answers</title></head>
<body>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>
<#list aufgabe as question>
    <h3>Task:</h3>${question.name}
    <br/>
    <h3>Description:</h3>${question.beschreibung}
</#list>
<table class="answertable">
    <tr>
        <th><h3>Received Answers</h3></th>
    </tr>
    <#list abgabe as answers>
        <tr>
            <td>${answers.abgabeText}<button>Evaluate this answer</button></td>
        </tr>
        ====================================
    </#list>
</table>
</body>
</html>