### Create user

POST http://localhost:8080/api/users
Content-Type: application/json

# como lo que pondría en Postman al hacer una peticion para comprobar
{
  "name" : "Maria",
  "email" : "maria1@email.com",
  "role" : "USER",
  "password" : "abcABC123"
 }

### Login

POST http://localhost:8080/api/users/me/session
Content-Type: application/json

{
  "email" : "maria1@email.com",
  "password" : "abcABC123"
}

### Profile

GET http://localhost:8080/api/users/me
Cookie: session = i05a7ca59-abee-4817-9c51-3f1cc71ed76f



