# graphql-java-spring-boot
3 different kinds of architectures that include a GraphQL server:

A. GraphQL server with a connected database
B. GraphQL server that is a thin layer in front of a number of third party or legacy systems and integrates them through a single GraphQL API
C. A hybrid approach of a connected database and third party or legacy systems that can all be accessed through the same GraphQL API
All three architectures represent major use cases of GraphQL and demonstrate the flexibility in terms of the context where it can be used.

This application demonstrates the use cases A and B
Use case A is borrowed from this post https://www.pluralsight.com/guides/java-and-j2ee/building-a-graphql-server-with-spring-boot
Use case B is developed from ground up in this application
Use case C is WIP

You can go to [http://localhost:9090/h2-console/login.jsp](http://localhost:9090/h2-console/login.jsp) and enter the following information:
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: <blank>

To start you will need to get API key from two sources

https://developers.themoviedb.org - Update appplication.properties api.movieDbApi with this key
http://www.omdbapi.com/ - Update appplication.properties api.omDbApi with this key

The Following examples are from original post. This is using the application Database. Added some new queries.
To check the databas or to [http://localhost:9090/graphiql](http://localhost:9090/graphiql) to start executing queries. For example:
```
{
  findAllBooks {
    id
    isbn
    title
    pageCount
    author {
      firstName
      lastName
    }
  }
}
New Query Added
{
  findBookByTitle(title:"Java")
  {
    id
    title
    pageCount
  }
  
}
```

Or:
```
mutation {
  newBook(
    title: "Java: The Complete Reference, Tenth Edition", 
    isbn: "1259589331", 
    author: 1) {
      id title
  }
}
```
The following are the new features that shows power of GraphQl when applied to legacy Rest API
{
  getMovies(title: "Home Alone") {
    title
    year
    imdbID
    imdbRating
    released
    response
    language
    directorName
    director
    {
      name
      placeOfBirth
      biography
      birthday
      popularity
      
    }
    plot
    ratings {
      source
      value
    }
  }
}

This makes a call to http://www.omdbapi.com/ to get the  details of the movie and then goes to themoviedb.org to 
get the directors information and then aggregates them and create a chunky API.
The client however uses the power of GraphQL to filter what is needed by the UI.


# License
MIT
