# videos-retrieval-service
An API to fetch latest videos sorted in reverse chronological order of their publishing date-time from YouTube in a paginated dashboard.

Service is deployed on Heroku.

URL: https://videos-retrieval-service.herokuapp.com/fetch-videos

## Features
- Service is calling YouTube API continuously in background (async) with **interval (30 minutes)** for fetching the latest videos for the **search query(Football)** 
- Fields being stored in database:
  - title
  - description
  - channel
  - publishing datetime
  - thumbnails URL alongwith height and width

- A GET API returning the stored video data in a paginated response sorted in descending order of published datetime, and for searching the stored videos with words present in **title**, **channel** and **description** (case insensitive). 
- Application is dockerized and scalable.
- Code and database are highly optimised.


*Note: Details of only 20 videos are fetched initially. 
For fetching older videos by calling Youtube API, **go to the last page and click NEXT button.***

## Specifications
### API Specifications:
- **GET /fetch-videos**:<br>
```/fetch-videos?pageNumber=28&size=5&searchQuery=null```

  **Query params:** 
```
  pageNumber: Page Number
  size: Page Size
  searchQuery: Search Query
```

### Parameter Specifications:
- Tag/Search query: **Football**

- Frequency of hitting Youtube API for fetching new videos: **30 mins**

- Number of video details being fetched per hit: **20**

## How to run

### Pre-requisites:
- Java 8
- Maven 3
- Set environment variable with Google API Key:
  ```export GOOGLE_API_KEY = "<API_KEY>"```
  
  OR,
  
  Add environment variable in ```~/.bash_profile```.
  

### Running application as standalone spring-boot application:
- Compile the project using Maven<br>
```> mvn clean install```
- Run spring boot app<br>
```> mvn spring-boot:run```
- Server will start on port 8081<br>
```[main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''```

### Running application in docker container:
- To create an image from our Dockerfile<br>
```> docker build --tag=videos-retrieval-service-server:latest ```
- Run the container from the created image<br>
```> docker run -p8081:8081 videos-retrieval-service-server:latest```
- Server will start on port 8081<br>
```[main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''```

### Open app in browser:
```http://localhost:8081/fetch-videos```

![image](https://user-images.githubusercontent.com/28265617/140965314-e8d0e2fa-fa29-4caa-b635-ed6668d46a0a.png)


