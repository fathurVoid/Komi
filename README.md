# Kopi dan Nyemil (Komi)

Aplikasi mobile Android untuk memesan kopi, minuman, dan makanan ringan. Proyek ini dibuat untuk tugas mata kuliah Pemrograman Mobile berdasarkan wireframe "Kopi dan Nyemil". Seluruh data bersifat statis (dummy) sehingga aplikasi berjalan tanpa database maupun koneksi internet.

## Fitur

- Splash/Welcome dengan tombol untuk memulai aplikasi.
- Sign Up sebagai halaman pilihan menuju Register atau Login.
- Login dengan validasi username dan password.
- Register dengan validasi: semua kolom wajib diisi, password minimal 6 karakter, dan username tidak boleh kembar.
- Home berisi daftar 17 menu dalam tampilan grid dengan foto, dilengkapi pencarian dan filter kategori (Coffee, Drinks, Food).
- Keranjang: jumlah setiap menu bisa ditambah atau dikurangi langsung dari kartu menu. Total item dan harga tampil di bar bawah, dan jumlah item juga muncul sebagai badge pada ikon keranjang di header.
- Order (Alamat Pengiriman) menampilkan ringkasan pesanan serta form nama lengkap, alamat, dan patokan. Jika keranjang kosong, halaman ini menampilkan pesan bahwa keranjang masih kosong.
- Konfirmasi Pesanan setelah pesanan dikirim.
- Profile untuk mengubah nama pengguna dan keluar dari akun.
- Navigasi memakai tombol dan ikon di header Home (keranjang dan profil), tanpa bottom navigation.

## Akun Demo

| Username | Password |
|----------|----------|
| demo     | demo12   |

Akun baru juga bisa dibuat lewat halaman Register. Akun disimpan di memori saja, sehingga hilang saat aplikasi ditutup.

## Teknologi

- Bahasa: Kotlin
- UI: Jetpack Compose dengan Material 3
- Navigasi: Navigation Compose
- Build: Gradle (Kotlin DSL), Android Gradle Plugin 8.5.2, Kotlin 2.0.20
- Min SDK 24, Target dan Compile SDK 34

## Cara Menjalankan

1. Install Android Studio versi terbaru.
2. Buka folder proyek `KopiDanNyemil` melalui File > Open.
3. Tunggu proses Gradle sync sampai selesai.
4. Pilih emulator atau perangkat Android (Android 7.0 / API 24 ke atas).
5. Klik Run.

## Struktur Proyek

```
KopiDanNyemil/
|-- build.gradle.kts
|-- settings.gradle.kts
|-- gradle.properties
|-- gradlew / gradlew.bat
|-- gradle/wrapper/
`-- app/
    |-- build.gradle.kts
    `-- src/main/
        |-- AndroidManifest.xml
        |-- java/com/kopidannyemil/
        |   |-- MainActivity.kt    (setup navigasi dan daftar route)
        |   |-- Screens.kt         (seluruh tampilan / composable)
        |   |-- Data.kt            (data menu dummy, model, logika state)
        |   `-- Theme.kt           (warna dan tema aplikasi)
        `-- res/
            |-- drawable/          (logo dan foto menu)
            |-- mipmap-anydpi-v26/ (ikon launcher)
            `-- values/            (tema dan warna)
```

## Alur Aplikasi

```
Splash -> Sign Up -> Login / Register -> Home

Home -> ikon profil -> Profile -> kembali ke Home / Log Out
Home -> ikon keranjang atau tombol Order -> Order (Alamat) -> Konfirmasi -> Home
```

## Data Menu

Menu terdiri dari tiga kategori dan disimpan sebagai daftar statis di `Data.kt`.

| Kategori | Jumlah | Contoh                           |
|----------|--------|----------------------------------|
| Coffee   | 8      | Espresso, Latte, Cappuccino      |
| Drinks   | 5      | Matcha Green Tea, Taro Latte     |
| Food     | 4      | Butter Croissant, Sourdough Loaf |

Untuk menambah atau mengubah menu, edit daftar `dummyMenu` di `Data.kt` dan letakkan foto baru di `res/drawable`.

## Keterbatasan

- Tidak memakai database, sehingga akun, keranjang, dan nama profil tidak tersimpan permanen.
- Proses pemesanan hanya simulasi dan tidak terhubung ke layanan pengiriman atau pembayaran.



## Tampilan App

Splash

<img width="1080" height="2275" alt="splash" src="https://github.com/user-attachments/assets/fd20baed-7305-4cfd-964f-872a9555c963" />

Sign Up

<img width="1080" height="2275" alt="sign up" src="https://github.com/user-attachments/assets/8ede065b-f4aa-47c1-bef3-6ab2e959c687" />

Register

<img width="1080" height="2275" alt="register" src="https://github.com/user-attachments/assets/5f718e51-ca25-4ac8-921f-941aaa9d91e2" />

Login

<img width="1080" height="2275" alt="login" src="https://github.com/user-attachments/assets/d76e759c-5ebf-422f-87c7-303990e00573" />

Home

<img width="1080" height="2275" alt="home" src="https://github.com/user-attachments/assets/c1743e18-aedb-4135-b670-d943a8ff5bd7" />

Profile

<img width="1080" height="2275" alt="profile" src="https://github.com/user-attachments/assets/af376015-5b57-41c0-b89f-7eaa6f5a0f21" />

Order

<img width="1080" height="2275" alt="order" src="https://github.com/user-attachments/assets/60ef97d8-6a35-4a24-8457-3dac3520abe7" />

Confirm Order

<img width="1080" height="2275" alt="confirm order" src="https://github.com/user-attachments/assets/2480cec7-aa60-44ae-ac8d-ba9c518e592c" />

