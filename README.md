## demo

> Миграция базы данных (Flyway):

[demo.flyway-migration.webm](https://github.com/user-attachments/assets/c87fa06f-ae5c-4e78-a0bf-b78b8e41f947)

> Функциональность API + JWT Авторизация:

[demo.restful-api+jwt-auth.webm](https://github.com/user-attachments/assets/bf2948c1-67ae-44cd-aa20-484e49bded1b)

![image](https://github.com/user-attachments/assets/45d5c6e9-8188-4cb3-9ca2-43f3bf241f2d)

## docs

Всего 3 сервиса

1. **`car-crud`**: сервис с реализацией основных _RESTful API_ + _Hibernate Validator_ + _Лог_ / _Обработка ошибок_ через _@RestControllerAdvice_

2. **`flyway-migration`**: сервис для управления миграциями бд
   
3. **`auth-security`**: сервис для настройки безопасности
    

## quickstart

<details>
  <summary><b><code>docker-compose</code></b></summary>

1. Запуситите `docker-compose.yaml` 

    ![img_1.png](img_1.png)

2. Подключитесь к бд через `DataGrip` (`IntelliJ IDEA Ultimate`)

    ![img.png](img.png)


</details>


<details>
  <summary><b><code>миграция бд</code></b></summary>

1. Запустите сервис `flyway-migration` (`FlywayMigration.class`)
2. В `resources/db/migration` определены версии миграции бд

    ![img_2.png](img_2.png)

    Где определена таблица `Cars`:
    ```postgresql
    CREATE TABLE cars (
         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
         make VARCHAR(255) NOT NULL,
         model VARCHAR(255) NOT NULL,
         year INT NOT NULL,
         price NUMERIC NOT NULL,
         vin VARCHAR(17) NOT NULL UNIQUE
    );
    
    -- нужно для gen_random_uuid()
    CREATE EXTENSION IF NOT EXISTS pgcrypto;
    ```
      
    И в которых определены `init` значения:
    ```postgresql
    INSERT INTO cars (make, model, year, price, vin)
    VALUES
            ('Toyota', 'Corolla', 1991, 20000.50, '1HGBH41JXMN109186'),
            ('Hyundai', 'Elantra', 2024, 15000.00, '1FADP3F29JL235973'),
            ('Honda', 'Civic', 2021, 22000.75, '19XFC1F56GE200815'),
            ('BMW', 'X5', 2015, 35000.30, '5UXKR6C59K0W97452'),
            ('Audi', 'A4', 2000, 27000.90, 'WAUZVAFB5CN071113');
    ```

</details>





## api

<details>
  <summary><b><code>car-crud</code></b></summary>

1. <details>

    <summary>GET /cars</summary>
    
    **_Request:_**

      ```
      http://localhost:8080/car-crud/api/cars
      ```

    **_Response:_**
    
    ```yaml
    [
        {
            "id": "ad118fab-767c-4762-a0f4-d2197123c9f9",
            "make": "Toyota",
            "model": "Corolla",
            "year": 1991,
            "price": 20000.5,
            "vin": "1HGBH41JXMN109186"
        },
        {
            "id": "dc0265a0-5b7a-4cb7-985b-7801edfe3696",
            "make": "Hyundai",
            "model": "Elantra",
            "year": 2024,
            "price": 15000.0,
            "vin": "1FADP3F29JL235973"
        },
        {
            "id": "1cf3c9c8-c722-41a6-b7a3-2dc5b591c20a",
            "make": "Honda",
            "model": "Civic",
            "year": 2021,
            "price": 22000.75,
            "vin": "19XFC1F56GE200815"
        },
        {
            "id": "4f4f9ae2-e76c-43fe-bedf-dfd061a31696",
            "make": "BMW",
            "model": "X5",
            "year": 2015,
            "price": 35000.3,
            "vin": "5UXKR6C59K0W97452"
        },
        {
            "id": "60ee985f-9b3c-48d6-96a9-761a1924b22b",
            "make": "Audi",
            "model": "A4",
            "year": 2000,
            "price": 27000.9,
            "vin": "WAUZVAFB5CN071113"
        },
        {
            "id": "a8c94763-7bd8-422c-9d1a-29521bf46e71",
            "make": "updated",
            "model": "updated",
            "year": 2024,
            "price": 20000.5,
            "vin": "1HGBH41JXMN109181"
        },
        {
            "id": "98c7f549-b6d6-4ce3-9280-b4675651f15a",
            "make": "new_test",
            "model": "new_test",
            "year": 1886,
            "price": 20000.5,
            "vin": "1HGBH41JXMN109182"
        }
    ]
    ```

    </details>



2. <details>

    <summary>GET /cars/{id}</summary>

   **_Request:_**

      ```
      http://localhost:8080/car-crud/api/cars/ad118fab-767c-4762-a0f4-d2197123c9f9
      ```
   
      >  `ad118fab-767c-4762-a0f4-d2197123c9f9` id от  init записей при миграции в бд

   **_Response:_**

    ```yaml
    {
        "id": "ad118fab-767c-4762-a0f4-d2197123c9f9",
        "make": "Toyota",
        "model": "Corolla",
        "year": 1991,
        "price": 20000.5,
        "vin": "1HGBH41JXMN109186"
    }
    ```

    </details>



3. <details>

    <summary>POST /cars</summary>

   **_Request:_**

      ```
      http://localhost:8080/car-crud/api/cars
      ```

    ```yaml
    {
        "make": "test",
        "model": "test",
        "year": 2000,
        "price": 100.0,
        "vin": "A0000000000000001"
    }
    ```

   **_Response:_**

    ```yaml
    successfully saved
    ```

    </details>



4. <details>

    <summary>PUT /cars/{id}</summary>

   **_Request:_**

      ```
      http://localhost:8080/car-crud/api/cars/{подставьте id от элемента добавленного через POST}
      ```

    ```yaml
    {
        "make": "update",
        "model": "update",
        "year": 2000,
        "price": 100.0,
        "vin": "A0000000000000002"
    }
    ```

   **_Response:_**

    ```yaml
    successfully updated
    ```

    </details>



5. <details>

    <summary>DELETE /cars/{id}</summary>

   **_Request:_**

      ```
      http://localhost:8080/car-crud/api/cars/{подставьте id от элемента добавленного через POST}
      ```

   **_Response:_**

    ```yaml
    successfully deleted
    ```

    </details>

</details>


<details>
  <summary><b><code>auth-security</code></b></summary>

1. <details>

    <summary>POST /auth/sign-in</summary>

   **_Request:_**

      ```
      http://localhost:8080/auth/sign-in
      ```

    ```yaml
    {
       "name": "qwert",
       "password": "qwert",
       "email": "qwert",
       "role": "ROLE_USER"
    }
    ```

   **_Response:_**

    ```yaml
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxd2VydCIsImlhdCI6MTczNDUyMjI1NCwiZXhwIjoxNzM0NTI0MDU0fQ.zEj-QNrg9UKBg6WhU8iM748HETT80mGLsZZx1BOTgjE
    ```
   > Это не актуальный jwt (для теста нужно будет сгенерировать локально)

    </details>

</details>
