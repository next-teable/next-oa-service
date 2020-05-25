# Deps

## Tech Deps

* Mongodb as Database （Spring Data Mongodb)
* Redis as Simple MQ and Token Cache (Spring Data Redis & Spring Message )

## Daas Deps

Daas Token as Spring Security Ext
Daas We as Spring Web Exception Ext
Daas Fss DAV as File Storage

And 

Mongodb GridFS is removed from V2


# Quick Start

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

## Swagger

```
> cd openapi/apidoc
> gradle clean bootRun
```

> http://127.0.0.1:8080/docs.html
