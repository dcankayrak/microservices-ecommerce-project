# SpringBoot-Microservice-Ecommerce-Project
## Tech Stack
- Spring Boot
- Swagger/Open API
- PostgreSQL
- OpenFeign
- ModelMapper
- Jpa Repository
- Redis
- Lombok...

## API
### Product
- GET api/v1/products -> Tüm ürünleri getirir.
- GET api/v1/products/details/{slug} -> Ürün detaylarını getirir.
- GET api/v1/products/order/{orderId} -> İstenilen siparişe ait ürünleri getirir.
- POST api/v1/products/create -> Ürün oluşturmayı sağlar.
- PUT api/v1/products/update -> Ürün bilgilerini günceller.
- DELETE api/v1/products/delete/{productId} -> Id'si verilen ürünü siler.

### Category
- GET api/v1/categories -> Tüm kategorileri getirir.
- GET api/v1/categories/create -> Yeni kategori oluşturur.

### ProductOrder
- GET api/v1/orders/user/{userId} -> İstenen kullanıcının siparişlerini getirir.
- POST api/v1/orders -> Sipariş oluşturur.

## Swagger
- product-service
- user-service
