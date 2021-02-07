<html>
<head><title>Sign Up</title>

<body>

<h1>Welcome</h1>


<form name="user" action="/" method="post">
    <div>
        <h3>${error}</h3><br/>
        Enter your email:
        <input type="text" name="username"/><br/>
        Enter your name:
        <input type="text" name="name"/><br/>
        <input type="submit" value="login" />&nbsp;<a href="/signup"><button>Sign up now!</button></a>
    </div>
</form>

</body>
</html>