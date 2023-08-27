# ecommerce-project

❗Bu proje Bursa Büyükşehir Belediyesi ve Kodluyoruz işbirliğinde gerçekleştirilen 6 haftalık eğitimin bitirme projesi olarak bootcamp sürecinde öğrendiğimiz/kendimize kattığımız bilgilerle geliştirilmiştir.

🎯Bu projeyle mikroservis mimarileri, cache işlemleri, dışarıdaki bir API/service'den veri çekmek, Spring Security ile API'larımızdaki güvenliği sağlamak ve JWT tokenlar hakkında daha fazla bilgi edinmek ve kendimin en çok nereye gidebileceğini merak ederek geliştirdiğim bir proje. Bu projede son derece SOLID kurallarını da hesaba katarak geliştirilmeye çalıştım. Web geliştirme projelerinin en kapsamlı projelerinden birisi olan E-Ticaret projesini biraz daha basite indirgeyerek, temel işlevleri içeren bir uygulama çıkardım. Bu projenin ön yüzü de bulunmakta. Ona aşağıdan erişebilirsiniz.

### 🏛 Mimari Hakkında
❗Normalde bir projeye direkt olarak mikroservis mimarisi uygulanarak başlanmaz. Öncelikle monolith olarak geliştirilir, ihtiyaçlara karşılık veremeyeceği zaman mikroservis çözümü uygulanabilir. Bu projedeki amacım mikroservis mimarileri hakkında daha derin bilgiye ulaşmak, arkasında yatan mantığı anlamaktır.

📌 Projemi mikroservis mimarisine göre dizayn etmek istedim. Bunu da 2 farklı spring projesi olarak ayırarak gerçekleştirdim. Proje ana hatlarıyla Product-service ve User-service olmak üzere 2'ye ayrılıyor. User-service kısımlarında Spring Security, JWT tokenlar, User işlemlerini kapsarken, Product-Service ise isminden de anlaşılacağı üzere product ve product'in işlemlerini içermekte. Mikroservisler arası iletişim OpenFeign kütüphanesi vasıtasıyla gerçekleşmekte.

### ⚙ Kullanılan Teknolojiler
- Spring Boot
- Spring Security
- JWT Tokens
- Authentication
- Swagger/Open API
- PostgreSQL
- OpenFeign
- ModelMapper
- Jpa Repository
- Lombok
- Microservices

### 💫 Projenin diğer projelerden farklı kılan özellikleri
- API'larımı user friendly olarak dizayn ettim. (/product/xiaomi-redmi-note12 vb.)
- Hibernate N+1 problemine özellikle dikkat ettim. Lazy olarak tanımladığımız field'ların birden fazla istek atmasını engellemek için HQL query'leri ile verilerimi çektim.
- API'larda direkt olarak veritabanı tablo nesnelerimi dönmedim. Bunun yerine DTO'ları kullandım.

## 📡 API
### 📌 Product
- `GET api/v1/products` -> Tüm ürünleri getirir.
- `GET api/v1/products/details/{slug}` -> Ürün detaylarını getirir.
- `GET api/v1/products/order/{orderId}` -> İstenilen siparişe ait ürünleri getirir.
- `POST api/v1/products/create` -> Ürün oluşturmayı sağlar.
- `PUT api/v1/products/update` -> Ürün bilgilerini günceller.
- `DELETE api/v1/products/delete/{productId}` -> Id'si verilen ürünü siler.

### 📌 Category
- `GET api/v1/categories` -> Tüm kategorileri getirir.
- `POST api/v1/categories/create` -> Yeni kategori oluşturur.
- `PUT api/v1/categories/update/{categoryId}` -> Varolan kategoriyi düzenler.
- `DELETE api/v1/categories/delete/{categoryId}` -> Kategoriyi siler.

### 📌 ProductOrder
- `GET api/v1/orders/user/{userId}` -> İstenen kullanıcının siparişlerini getirir.
- `POST api/v1/orders` -> Sipariş oluşturur.

### Projeden Alıntılar & Video
![image](https://github.com/DCanKayrak/SpringBoot-Microservice-Ecommerce-Project/assets/94143272/bb1ac59f-c40f-4968-91e9-355230060143)
![image](https://github.com/DCanKayrak/SpringBoot-Microservice-Ecommerce-Project/assets/94143272/83203da4-72b8-4b88-a20c-cb38a97be041)
![image](https://github.com/DCanKayrak/SpringBoot-Microservice-Ecommerce-Project/assets/94143272/c1977a50-d803-46cd-9b9f-9b1d55ddcd0c)
![image](https://github.com/DCanKayrak/SpringBoot-Microservice-Ecommerce-Project/assets/94143272/e95a30ae-74ae-43d5-b34f-6d70efce074b)
![image](https://github.com/DCanKayrak/SpringBoot-Microservice-Ecommerce-Project/assets/94143272/53813d92-b946-4095-a266-d7d8cdccc8ec)

[Video Link](https://www.youtube.com/watch?v=q2C4jaFmWdI)

### ☎️ İletişim
---
[Frontend Projesinin Linki](https://github.com/DCanKayrak/SpringBoot-Microservice-Ecommerce-Project-Frontend)

[Github Hesabım](https://github.com/DCanKayrak)
[LinkedIn Hesabım](https://www.linkedin.com/in/dcankayrak/)
[Mail Adresim](dancankan@gmail.com)
