<html>
<head>
<title>Course Details</title>
</head>
<body>
<h1>Information</h1>
<#list course as details>
    <h2>${details.name}</h2>
    <br/>
    <h3>Producer: ${details.erstellersName}</h3>
    <br/>
    Number of Free Seats: ${details.freiePlaetze}
    <br/>
    <a href="/new_enroll?kid=${details.kID}"><button>Register</button></a>

</#list>
</body>
</html>