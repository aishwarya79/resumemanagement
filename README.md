# Resume Management

**End	Points:**  
1.	Upload	Resume:	  
Method:	Post  
URL:	http://localhost:8080/resumemanagement/api/v1/uploadResumeDetails  
Content-Type:	application/json	
	
Sample	Request:
```	
{
	"name": "James Smith",
	"currentJobTitle": "Software Engineer",
	"jobDesc": "Respponsible for application development and testing",
	"currentCompany": "Grab"
}
```
Sample	Response:  
```
{	"success":	true,
	"resumeId":	1000001	
}
```		
	
2)	Get	Resume	by	Id  
	Method:   Get  
	URL:	http://localhost:8080/resumemanagement/api/v1/getResume/<id>	
	
Sample	Request:	http://localhost:8080/resumemanagement/api/v1/getResume/1000001	


Sample	Response:  
```	
{
	"name": "James Smith",
	"currentJobTitle": "Software Engineer",
	"jobDesc": "Respponsible for application development and testing",
	"currentCompany": "Grab"
}
```