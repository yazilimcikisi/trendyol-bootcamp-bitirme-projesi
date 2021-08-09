# trendyol-bootcamp-bitirme-projesi
Bu proje Trendyol back-end bootcamp bitirme projesidir.


### Proje Tanımı:
Görevimiz : Trendyol Sepet Sistemini en baştan yazmak 
Eski sepet sistemimiz, gerekli şekilde scale edemediği için, bu sistemi yeniden yazmak ve üzerine yeni özellikler getirmek istiyoruz.

Gereksinim: 
- Sepette, bir veya birden çok ürün olabiliyor. 
- Sepete ürünleri ekleyebiliyoruz. Aynı ürünü tekrar eklersek, sepetteki, o ürün sayısı artıyor. 
- Sepetten ürün çıkartabiliyor ya da sepetteki o ürünün sayısını azaltabiliyoruz. 
- Sepetteki ürünleri dönerken, bir genel toplam bilgisi de dönüyor. Sepetteki ürünlerin sayısı ve fiyatlarına göre kullanıcıların ne kadar ödeyeceği bilgisini gösteriyor. 5 TL olan A ürününden 3, 10 TL olan B ürününden 2 tane varsa sepette, genel toplam bilgisi olarak 5 x 3 + 10 x 2 = 35 TL gösteriyor. 100 TL altındaki her sepet toplamı için de 4,99 kargo bedeli ekliyoruz. Üstteki bu sepet için 35 + 4,99 = 39,99  TL olarak genel toplamı, kargo bilgisni de ekleyerek dönmesini istiyoruz. Örnek json :

        {
        "products": [{
                "id": 1,
                "image": "imageURL",
                "title": "product1",
                "quantity": "3",
                "price": "5"
        }, {
                "id": 2,
                "image": "imageURL",
                "title": "product2",
                "quantity": "2",
                "price": "10"
        }],
        "basketInfo": {
                "Ara toplam": "35 TL",
                "Kargo": "4,99",
                "Toplam": "39,99 TL"
        }


### Gereksinimleri karşılamak için izlenen adımlar
#### 1) Sepete ürün eklenmesi
Trendyol uygulamasını incelediğimde "Sepete Ekle" işlemi ürün detay ekranından yapılmakta ve her seferinde 1 adet ürün eklenmekteydi. Ben de adımlarımı buna istinaden tasarladım. </br>
Bu gereksinim için aşağıdaki mimari oluşturulmuştur. Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepete-urun-ekleme.png)
![sepete-urun-ekleme-diyagram](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepete-urun-ekleme.png)
Sepete ürün eklerken izlenen adımlar: Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepete-urun-ekleme-akis-diyagrami.png)
![sepete-urun-ekleme-akis-diyagrami](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepete-urun-ekleme-akis-diyagrami.png)
Test çıktıları: Görseli yüksek çözünürlükte incelemek için [tıklayınız](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/urun-ekleme-test.png)
![urun-ekleme-test](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/urun-ekleme-test.png)

#### 2) Sepetteki ürünün miktarının değiştirilmesi
Trendyol uygulamasını incelediğimde sepette var olan bir ürünün miktarını artırmak veya azaltmak için sepet ekranına geliniyor ve ilgili ürünün sayısı listeden seçiliyor veya artı eksi tuşları ile değiştiriliyordu. </br>
Ben de ürün miktarını artıkmak ve azaltmak için ayrı ayrı metotlar yazmak yerine "miktar" parametresi alan tek bir metot yazdım.
Bu gereksinim için aşağıdaki mimari oluşturulmuştur. Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetteki-urunun-miktarinin-degistirilmesi.png)
![sepeteki-urun-miktarini-guncelleme-diyagram](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetteki-urunun-miktarinin-degistirilmesi.png)
Sepeteki bir ürünün miktarını değiştirmek için izlenen adımlar: Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetteki-urunun-miktarinin-degistirilmesi-akis-diyagrami.png)
![sepete-urun-miktarini-guncelleme-akis-diyagrami](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetteki-urunun-miktarinin-degistirilmesi-akis-diyagrami.png)
Test çıktıları: Görseli yüksek çözünürlükte incelemek için [tıklayınız](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/urun-miktarini-guncelleme-test.png)
![urun-miktarini-gucelleme-test](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/urun-miktarini-guncelleme-test.png)

#### 3) Sepetteki belirli bir ürünün silinmesi
Bu gereksinim için aşağıdaki mimari oluşturulmuştur. Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetten-urun-silme.png)
![sepeten-urun-silme-diyagram](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetten-urun-silme.png)
Sepeteki bir ürünün miktarını değiştirmek için izlenen adımlar: Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetten-urun-silme-akis-diyagrami.png)
![sepeten-urun-silme-akis-diyagrami](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetten-urun-silme-akis-diyagrami.png)

#### 4) Belirli bir kullanıcının sepet bilgisinin getirilmesi
Bu gereksinim için aşağıdaki mimari oluşturulmuştur. Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepet-bilgilerini-getirme.png)
![sepeti-goruntuleme-diyagram](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepet-bilgilerini-getirme.png)
Sepeteki bir ürünün miktarını değiştirmek için izlenen adımlar: Görseli yüksek çözünürlükte incelemek için [tıklayınız.](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepetten-urun-silme-akis-diyagrami.png)
![sepet-bilgilerini-getirme-akis-diyagrami](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/sepet-bilgilerini-getirme-akis-diyagrami.png)
Test çıktıları: Görseli yüksek çözünürlükte incelemek için [tıklayınız](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/getCustomersBasketInfo-test-result.PNG)
![urun-miktarini-gucelleme-test](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/getCustomersBasketInfo-test-result.PNG)


## Veritabanı
### Proje gereksimleri üzerinden çıkarılan ilk taslak ER diyagramı
Bu diyagram, projede yer alacak olan verilerin neler olduğunu belirlemek için çıkarılmıştır.
![taslak-er-diyagrami](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/senaryo-uzerinden-cikarilan-taslak-er-diyagrami.png)

Yukarıdaki ER diyagramı ve servislere istinaden veritabanı aşağıdaki şekli almıştır. Görseli yüksek çözünürlükte incelemek için [tıklayınız](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/veritabanlari.png)
![veritabanlari](https://github.com/peserey/trendyol-bootcamp-bitirme-projesi/blob/main/images/veritabanlari.png)


