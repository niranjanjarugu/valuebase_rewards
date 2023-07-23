# valuebase_rewards
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 Configured 9191 port number in application.properties file, if wont another port number,
 can change in application.properties file.
  
# API's
  Below are the Reward based API's
 
# Get All Rewards
  Request URL: http://localhost:9191/rewards
  Request Method: GET
  
# Sample Response of Get All Rewards:
[{
"customerId": 1,
"firstMonthRewardPoints": 90.00,
"secondMonthRewardPoints": 0,
"thirdMonthRewardPoints": 0,
"totalRewardPoints": 90.00
}]

# Get Reward By CustomerId
Request URL: http://localhost:9191/rewards/{CUSTOMER_ID}
Path Variable: customerId
Request Method: GET

# Sample Response of et Reward By CustomerId:
{
"customerId": 1,
"firstMonthRewardPoints": 90.00,
"secondMonthRewardPoints": 0,
"thirdMonthRewardPoints": 0,
"totalRewardPoints": 90.00
}


# Get All Transaction
Request URL: http://localhost:9191/transactions
Request Method: GET

# Sample Response of Get All Rewards:
[{
"id": 1,
"customerId": 1,
"amount": 120.00,
"transactionDt": "2023-07-23",
"createdDt": "2023-07-23T16:26:24.162115",
"updateDt": "2023-07-23T16:26:24.162115"
}]


## Note
 As per my understanding this, we don't want /transactions and /rewards API.
 because it will get problem in future. Assume we have 100k customers are there and each customer did more than 
 50 transactions, that time API will get more responses and will may break.


  
  