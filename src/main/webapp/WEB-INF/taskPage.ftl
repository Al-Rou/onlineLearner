<html>
<head><title>Create a task</title>

<body>
<h1>Create a new task</h1>
<br/>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>
<#list course as show>
<form name="user" action="new_task?kid=${show.kID}" method="post">
    <h2>A new task for ${show.name}</h2> <br/>
    Name of the task: <input type="text" name="titel"/> <br/>
    Description of the task: <textarea name="descrip" rows="10" cols="40"></textarea><br/>

    <input type="submit" value="create"/>
</form>
</#list>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>