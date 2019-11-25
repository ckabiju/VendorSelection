This project is a simple CURD Rest webservice application build using springboot and MYSql DB and 
deployed on AWS Elastic BeanStalk through CI/CD using AWS CodePipeline.

API's supported:

	1) Server status check:
			URL: http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/api/init
			Type: GET
		Returns status '200 OK', which confirms that the server deployed on AWS is up and running.
	
	2) User Registration:
			URL: http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/register
			Type: POST
			Body: {"username":"xxUser" ,"password":"xxpw"}
			
		 This API saves the the username and encryped password in database, and when successful returns back the username.
	
	3) User Authentication:
			URL: http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/authenticate
			Type: POST
			Body: {"username":"nttuser" ,"password":"password"}
			
			This API returns a 'Bearer Token' if the user credentials are correct.
	
	4) Upload text file with data is supported format. NPI|NDC|Units|UnitCost
			URL: http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/api/files/qoh
			Type: POST
			Authorization: Enter 'Bearer Token' from step3 
			Body: select 'form-data' and select the file to load, select the key type as 'File'.
			
			This API kicks off the file upload Async process and returns "filename", "status" and "guid". The status will be "processing".
				
	5) Check status of file upload.
			URL: http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/api/files/qoh/{guid from step4}
			Type: GET
			Authorization: Enter 'Bearer Token' from step3 
			
			Returns "fileName","guid","status","totalRecordCount","invalidRecordCount"and validRecordCount"
			
	6) Add Site details.
			URL:  http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/api/sites
			Type: POST
			Authorization: Enter 'Bearer Token' from step3 
			Body: pass  'site' details in json format.
					 Eg:	{
                                 "npi": "00000000014",
                                "siteName": "Healthy Drug #14"

                             }
			
			Returns status 201/Created, and  record which got created.
			
	7) Load Site details.
			URL:  http://vendorselection-env.iacqaieg2y.us-east-1.elasticbeanstalk.com/api/sites/{npi}
			Type: GET
			Authorization: Enter 'Bearer Token' from step3 
			
			Returns site details.
	

Note:	
	1) Database connection Details can be found in Application.properties file in code base.
	2) CI/CD is implemented with AWS CodePipeline and Jenkins. AWS codepipeline downloads the code from Git Repositiry, 
		zips it and copies it to Jenkins Workspace and Unzips it. Code is build using Jenkins and the package send to 
		AWS pipeline which deploys it to AWS Elastic BeanStalk environment.
	3) Latest code is available  on git repository: https://github.com/ckabiju/VendorSelection.git
	
Known Issue: 
	1) on AWS BeanStalk environment we are facing issue with uploading large files (1Mb and above).