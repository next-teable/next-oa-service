# Deps

## Tech Deps

* Mongodb as Database ( Spring Data Mongodb )
* Redis as Simple MQ and Token Cache ( Spring Data Redis & Spring Message )

## Daas Deps

Daas Token as Spring Security Ext
Daas We as Spring Web Exception Ext
Daas Fss DAV as File Storage

And 

Mongodb GridFS is removed from V2


# Quick Start

## Before Start

> Start redis & mongodb first. 

```
> cd devops/dev
> docker-compose up -d
```

## Dev

```
> cd openapi/server
> gradle clean bootRun -Dspring.profiles.active=development 
```

## Prd

```
> cd openapi/server
> gradle clean bootRun -Dspring.profiles.active=production 
```

## Test

```
> cd openapi/server
> gradle clean test
```

## Swagger

```
> cd openapi/apidoc
> gradle clean bootRun
```

Then visit the following url in browser ( Chrome is recommended ).

> http://127.0.0.1:8080/docs.html
