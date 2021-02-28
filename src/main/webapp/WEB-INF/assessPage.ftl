<html>
<head><title>Assess a delivered answer</title>

<body>
<br/>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>

<#list registered as kurs>
        <h3>Course:</h3> ${kurs.courseName} <br/>
        <h3>Task:</h3> ${kurs.taskName} <br/>
        <h3>Description:</h3> ${kurs.taskDescription} <br/>
</#list>
<#list textforassess as text>
    <form name="user" action="view_main" method="post">
        <h3>Delivered Answer:</h3> ${text.abgabeText} <br/>
        <h3>Grade:</h3><input type="radio" name="grade" value="1"/>1
                       <input type="radio" name="grade" value="2">2
                       <input type="radio" name="answer" value="3">3
                       <input type="radio" name="answer" value="4">4
                       <input type="radio" name="grade" value="5">5<br/>
        <h3>Comment:</h3> <textarea name="answer" rows="10" cols="40"></textarea><br/>

        <input type="submit" value="send evaluation"/>
    </form>
</#list>


<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>