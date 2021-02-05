<html>
<head><title>New Course</title>

<body>
<h1>Create a new course</h1>
<br/>
<div><h3>${error}</h3></div>
<br/>
<form name="user" action="new_course" method="post">
    Name: <input type="text" name="titel"/> <br/>
    Registration Password: <input type="text" name="pass"/><br/>
    Number of free seats: <input type="number" name="number"/><br/>
    Description: <input type="text" name="des"/><br/>

    <input type="submit" value="create"/>
</form>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>