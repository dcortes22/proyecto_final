# Gestion de Banca

## Endpoints

### Registrar Usuario:

POST http://localhost:8080/api/users/register

```javascript
{
    "name": "David",
    "firstLastName": "Cortes",
    "secondLastName": "Saenz",
    "identification": "111111",
    "identificationType": "NATIONAL",
    "userName": "dcortes",
    "password": "123456"
}
```

Retorna:

```javascript
{
    "id": 11,
    "name": "Pizza",
    "firstLastName": "Gonzalez",
    "secondLastName": "Jimenez",
    "identification": "111111",
    "identificationType": "NATIONAL",
    "userName": "rodo6",
    "password": "$2a$10$sdTgiH4M0eftpujq91grC.nlaiZgjNEgydp8hMrM08rSL.iWFKXFC"
}
```

### Login Usuario:

POST http://localhost:8080/api/users/login

```javascript
{
    "userName": "rodo4",
    "password": "123456"
}
```

Retorna:

JWT Token: 
```javascript
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2RvNCIsInJvbGUiOltdLCJpYXQiOjE3MTA1NzgxNzAsImV4cCI6MTcxMDYxNDE3MH0.Qn2RGtGsy8tIdH2wCRLmL1Y7cP6KUbM5JAprVuY6kw0
```

### Informacion de usuario

GET http://localhost:8080/api/users/#userName

Retorna:

```javascript
{
    "id": 11,
    "name": "Pizza",
    "firstLastName": "Gonzalez",
    "secondLastName": "Jimenez",
    "identification": "111111",
    "identificationType": "NATIONAL",
    "userName": "rodo6",
    "password": "$2a$10$sdTgiH4M0eftpujq91grC.nlaiZgjNEgydp8hMrM08rSL.iWFKXFC"
}
```

### Cuentas

GET http://localhost:8081/api/accounts/user/#userId

Retorna:

```javascript
[
    {
        "id": 4,
        "userId": 10,
        "accountNumber": "6135779967",
        "accountCard": "6682483231077694",
        "accountBalance": 0.0,
        "accountOpeningDate": "2024-03-16T00:00:00.000+00:00"
    }
]
```




