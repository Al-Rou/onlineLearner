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

        Course: <br/>
        Task: <br/>
        Description: <br/>
        Answer: <textarea name="answer" rows="10" cols="40"></textarea>

        <input type="submit" value="deliver"/>
    </form>
</#list>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>