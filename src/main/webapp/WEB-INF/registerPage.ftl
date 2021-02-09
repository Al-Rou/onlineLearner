<html>
<head><title>Course Registration</title>

<body>
<br/>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>

<#list registered as kurs>
    <h1></h1><br/>
<form name="user" action="new_enroll" method="post">

    Registration Password: <input type="text" name="pass"/><br/>

    <input type="submit" value="register"/>
</form>
</#list>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>