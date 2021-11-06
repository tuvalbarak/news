# News API Application

I used https://newsapi.org to fetch data.

The app is fully working while all the required functionalities are implemented.

Architecture - 
- I wrote the app using the MVVM architecture, thus achieving a total separation of layers, which makes the app scalable and gives me the ability to easily change and test each module. 
- I tried to use as much as I can in the Observer design pattern, to achieve a reusable and readable code.
- The app is written based on the Single-Activity architecture. This helps me to take advantage of the use of fragments as much as possible.

Technologies - 
- The app is written in Kotlin, and it uses Room DB to store favorite articles, LiveData to enable stateless app, Repository to create an abstract layer between the Viewmodel and the DB/API, DAO for accessing the DB, Viewmodel to perform the entire app logic, Flow that create a "pipe" through the app to retrieve data from the DB, Coroutines to achieve better performance with asynchronous functions, NavGraph to easily navigate and send data (SafeArgs) between fragments, and Retrofit to fetch data using API calls.

How to Make It Better -
- In a production app, I would add Pagination and some cache mechanism to enable a faster loading and an offline experience.
- I would create Unit testing.
- I would use not one favorites list, but let the user create as many as he wants.
- I would make the XML more efficient code-wise, means I would use more styles and themes to reduce code repetition.

Note - even though the API service doesn't support a query to get the list of categories (so I created them manually on CategoryViewModel, using the possible categories it provides), I created the entire structure as if the option was available (I wrote comments in the relevant sections).

Below you will find some photos from the app, and a link to a YouTube video I made to demonstrate the app flows.

https://youtu.be/8pKVWtD-hUQ

![image](https://drive.google.com/uc?export=view&id=141l0EwwoJA8mNDdvTBNOTuPOtX4TH0N8)
![image](https://drive.google.com/uc?export=view&id=1x1rphXKBIao7lgz2GlDfyQpP_o5P8Mcf)
![image](https://drive.google.com/uc?export=view&id=1hBrPBp8RQbhiwobqirrXjVwDgMueOBT9)
![image](https://drive.google.com/uc?export=view&id=1nyBefnO9CqwIwiRhC2jirPWd3g3AhCkN)
![image](https://drive.google.com/uc?export=view&id=1XtPCjS39Ak3aIOSn_bg5SQjuXoMgd-aH)
