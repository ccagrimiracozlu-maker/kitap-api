## Çalıştırma

Projeyi Gradle ile çalıştırmak için:

`.\gradlew bootRun`

API adresi:

`http://localhost:8080/books`

Örnek kitap ekleme isteği:

```http
POST /books
Content-Type: application/json

{
  "name": "Suç ve Ceza",
  "price": 150,
  "stock": 10
}