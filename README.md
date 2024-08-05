# Loan Request Management Application

## Overview

The Loan Request Management Application is designed to streamline the process of handling loan requests within a banking environment. The application allows bank employees to create, manage, and update loan requests and their associated processing steps and loan types. It also supports searching loan requests based on their status.

## Features

- Create new loan requests with specified loan types, customer information, and loan amounts.
- Manage the status of loan requests and their individual processing steps.
- Automatically update loan request status based on the statuses of the associated steps.
- Search for loan requests based on their status.
- Create, update, and delete loan types and processing steps.

## Technologies Used

- Spring Boot
- JPA/Hibernate
- SQLite
- Lombok
- RESTful APIs

## Installation

### Prerequisites

- Java 17 or higher
- Maven
- SQLite database

### Steps

1. **Clone the repository:**
   ```sh
   git clone https://github.com/b-latincic/loan-request-app.git ```
   
2. **Navigate to the project directory:**
	```cd loan-request-management```  

3. **Build the project**	
	```mvn clean install```
	
4. **Run the application**
	```mvn spring-boot:run```
	
5. **Access the application**
	Open your browser and navigate to http://localhost:8080.

## API Documentation

### Loan Type Endpoints

#### Get Loan Types

#### Endpoint:

``` GET /api/v1/loan-type ```

#### Query Parameters:

- `name` (optional)

#### Response:

```
[
    {
        "id": "loan-type-uuid",
        "name": "Cash loan",
        "totalDuration": 15
    }
]
```

#### Create Loan Type

#### Endpoint:

```POST /api/v1/loan-type```

#### Request Body:

```
{
    "name": "Cash loan",
    "processingSteps": [
        {
            "name": "Step 1",
            "orderNo": 1,
            "expectedDuration": 7
        },
        {
            "name": "Step 2",
            "orderNo": 2,
            "expectedDuration": 14
        }
    ]
}
```

#### Response:

```
{
    "id": "loan-type-uuid",
    "name": "Personal Loan",
    "totalDuration": 21
}
```

#### Update Loan Type

#### Endpoint:

```PUT /api/v1/loan-type/{id} ```

#### Request Body:

```
{
    "name": "Updated Personal Loan",
    "processingSteps": [
        {
            "name": "Updated Step 1",
            "orderNo": 1,
            "expectedDuration": 7
        },
        {
            "name": "Updated Step 2",
            "orderNo": 2,
            "expectedDuration": 14
        }
    ]
}
```

#### Response:
```
{
    "id": "loan-type-uuid",
    "name": "Updated Personal Loan",
    "totalDuration": 21
}
```

#### Delete Loan Type

####Endpoint:

``` DELETE /api/v1/loan-type/{id} ```
 
#### Response:

``` 200 OK ```

### Processing Step Endpoints

#### Create Processing Step

#### Endpoint:

```POST /api/v1/processing-steps ```

#### Request Body:

```
{
    "name": "Step 1",
    "orderNo": 1,
    "expectedDuration": 7,
    "loanTypeId": "loan-type-uuid"
}
```

#### Response:

```
{
    "id": "step-uuid",
    "name": "Step 1",
    "orderNo": 1,
    "expectedDuration": 7,
    "loanTypeId": "loan-type-uuid"
}
```


### Loan Request Endpoints

#### Create a Loan Request

#### Endpoint:

```POST /loan-requests```

#### Request Body:

```
	{
		"firstName": "John",
		"lastName": "Doe",
		"loanAmount": 5000.0,
		"loanTypeId": "loan-type-uuid"
	}
```

#### Response: 

```
	{
		"id": "loan-request-uuid",
		"firstName": "John",
		"lastName": "Doe",
		"loanAmount": 5000.0,
		"requestStatus": "PROCESSING",
		"requestSteps": [
			{
				"id": "step-uuid",
				"expectedDurationDays": 7,
				"actualDurationDays": null,
				"status": "PENDING"
			}
		],
		"loanTypeId": "loan-type-uuid"
	}
```

#### Update Loan Request Status

#### Endpoint:

``` PUT /loan-requests/{loanRequestId}/status ```

#### Request Body:

``` 
{
    "newStatus": "APPROVED"
}
```

#### Response:

``` 200 OK ```

#### Update Request Step Status

#### Endpoint:

``` PUT /loan-requests/{loanRequestId}/steps/{stepId}/status ```

#### Request Body:

```
{
    "newStatus": "SUCCESSFUL",
    "actualDurationDays": 5
}
```

#### Response:

``` 200 OK ```

#### Search Loan Requests by Status

#### Endpoint:

``` GET /loan-requests ```

#### Query Parameters:
- `status` (e.g., `PROCESSING`, `APPROVED`, `REJECTED`)

#### Response:

```
[
    {
        "id": "loan-request-uuid",
        "firstName": "John",
        "lastName": "Doe",
        "loanAmount": 5000.0,
        "requestStatus": "PROCESSING",
        "requestSteps": [
            {
                "id": "step-uuid",
                "expectedDurationDays": 7,
                "actualDurationDays": 5,
                "status": "SUCCESSFUL"
            }
        ],
        "loanTypeId": "loan-type-uuid"
    }
]
```

## Usage

### Managing Loan Types and Processing Steps
1. Use the /api/v1/loan-type endpoints to create, update, and delete loan types.
2. Use the /api/v1/processing-steps endpoint to create processing steps associated with loan types.

### Creating a Loan Request

1. Send a POST request to /loan-requests with the necessary loan request details in the body.
2. The response will contain the newly created loan request with its initial status and associated steps.

### Updating Loan Request and Step Status
1. To update the status of a loan request, send a PUT request to /loan-requests/{loanRequestId}/status with the new status in the body.
2. To update the status of a specific step, send a PUT request to /loan-requests/{loanRequestId}/steps/{stepId}/status with the new step status and actual duration in the body.

### Searching Loan Requests
1. Send a GET request to /loan-requests with the desired status as a query parameter.
2. The response will contain a list of loan requests matching the specified status.


## Troubleshooting

### Common Issues
- Database Connection Issues: Ensure SQLite database is properly configured and accessible.
- Dependency Issues: Run mvn clean install to ensure all dependencies are correctly resolved.

## Contributing

If you wish to contribute to this project, please fork the repository and submit a pull request.








