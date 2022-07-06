
#Calorie app

Calorie app for month with it

Application can add food, delete food, caunts how much calories you need per day, by your
weight, heigt, age, gender, activity level, sets all food calories from food.

Also you can update food, find by name.


## What was used in project 

I did use Java 17 and Java framework Spring Boot for this project 

 and Lombok dependency. 


For mapper did use mapstruct dependency. 

 
Did test everything with "Postman" and some Junit test inside app.


## Tech Stack

**Client:** Java, Spring Boot

**Server:** H2


## API endpoints

#### Get Points for food

```http
  GET  
 Port you can change on: application.properties
```
you will get all foods in List: /api/v1.0/foods  

You will Get like this:
```json
[
    {
        "name": "smoked salmon",
        "calories": 200,
        "protein": 20,
        "carbs": 0,
        "fat": 13,
        "fiber": 0
    },
    {
        "name": "chicken",
        "calories": 120,
        "protein": 20,
        "carbs": 0,
        "fat": 4,
        "fiber": 0
    }
]
```
you will get all calories sum from list: /api/v1.0/foods/calories

you will get number:
```json
320
```
you will get food by name : /api/v1.0/foods/chicken
for example:
```json
    {
        "name": "chicken",
        "calories": 120,
        "protein": 20,
        "carbs": 0,
        "fat": 4,
        "fiber": 0
    }

```
you will get from this : /api/v1.0/persons/cal/Henrikas

First you need add Person to get this.

  1. Goal 2. how much was added to list 3. how much left to reach goal
```json
[
    3087,
    320,
    2767
]

```
this Calorie daily goal Canculator will not save data: 

GENDER:
 
man (male)

woman (female)

Activity levels:

sedentary  (little or no exercise) 

lightly (exercise 1-3 days/week) 

moderately (exercise 3-5 days/week) 

active (exercise 6-7 days/week)
 
very (hard exercise 6-7 days/week)

   
   api/v1.0/persons/goalCal
You need to enter data like this to "postMan":
```json
{
        "personName": "Henrikas",
        "gender": "men",
        "age": 25,
        "weight": 85,
        "height": 185,
        "activityLevel": "moderately"
    }

```
You will recive result: 

3087

```

#### POST:


```http
  POST
```
will add calories and count goal, left, and will show humch consumed

GENDER:
 
man (male)

woman (female)

Activity levels:

sedentary  (little or no exercise) 

lightly (exercise 1-3 days/week) 

moderately (exercise 3-5 days/week) 

active (exercise 6-7 days/week)
 
very (hard exercise 6-7 days/week)

/api/v1.0/persons/new
```json
Send like this:
{
        "personName": "Henrikas",
        "gender": "men",
        "age": 25,
        "weight": 85,
        "height": 185,
        "activityLevel": "moderately"
    }
    
```
to add new food :
/api/v1.0/foods/new
```json
Send like this:

    {
        "name": "Egg",
        "calories": 155,
        "protein": 13,
        "carbs": 1,
        "fat": 11,
        "fiber": 5
    }
    
```





#### update
```http
 Put maping:
```
will Update food
/api/v1.0/foods/update/2
send like this:

```json
{
  "name": "Egg",
  "calories": 300,
  "protein": 20,
  "carbs": 1,
  "fat": 11,
  "fiber": 5
}
```
#### Delete
Delete
```
Delete Person
"/api/v1.0/persons/delete/{name}"

Delete food
/api/v1.0/foods/delete/chicken
#### plans for the Future
```

Add login system

Create fron end with React
