# tugas-konversi-pam
Tugas konversi bangkit untuk mata kuliah PAM Semester genap 2022-2023

# Cara Menggunakan :
1. Buka Aplikasi 
2. Klik tombol search di bagian top bar
3. Ketikkan nama username github seseorang 
4. Jika username yang diketik sudah tampil dalam bentuk adapter tersebut, klik adapter tersebut.
5. Maka dapat dilihat informasi detail seorang user github

# NOTE :
**Jika aplikasi tidak menampilkan list user yang dicari walaupun koneksi pengguna sedang bagus, mohon cek di kodingan nya di bagian file ApiService atau pathnya app\src\main\java\com\example\myapplication\api\ApiService
karena ditakutkan token API yang saya gunakan habis dan saya menaruh build.gradle nya di bagian build yang ukurannya sangat besar Kalau saya post di github**


# Penjeasan
Aplikasi ini menggunakan api dari github.

Aplikasi ini menggunakan salah satu prinsip Android Architeture Modern yang terdiri dari Activity dan ViewModel, 

Activity:
Activity bertugas untuk menampilkan tampilan yang sudah di atur dalam file XML. Selain itu ada juga fragment yang tugasnya sama seperti activity, tetapi perbedaannya fragment berjalan di atas activity. 

ViewModel :
ViewModel bertugas untuk melakukan fetching api. Pada fetching api digunakan library retrofit2 yang dilakukan secara asynchronus. 

Terdapat beberapa activity dan viewmode dari tugas ini yaitu :

activity :
MainActivity :
Merupakan class yang menjadi portal utama dalam mengakses aplikasi yang sudah diatur di dalam file AndroidManifest. Kelas ini menampilkan tampilan option menu untuk melakukan search username github seseorang dan menampilkan data dari hasil search yang berasal dari api github dengan menggunakan class MainViewModel dan di fetch menggunakan method @GET hasil pencarian ditampilkan berupa list dengan konsep list nya menggunakan adapter. 

DetailActivity :
Class ini berfungsi untuk menampilkan Detail profile user github yang didapat dari list objek yang ditampilkan pada saat melakukan search pada MainActivity. pada Activity ini data yang tealh diambil dari MainActivity yang dioper ke class ini akan diproses lagi dengan menggunakan DetailViewModel untuk memproses API yang di dapat dari objek yang dioper dari MainActivity.
Di dalam Activity ini terdapat fragment yang bertugas unutk memproses api dari objek ynag dari Main Activity untuk menampilkan list Followers dan Following dari seorang github user dengan tampilan list nya menggunakan konsep adapter. 


ViewModel :
MainActivityViewModel :
Berfungsi untuk mengambil data yang diperlukan untuk MainActivity, class ini bertugas untuk menampilkan hasil query yang diproses di MainActivity. 

DetailActivityViewModel :
Berfungsi untuk menampilkan data yang parameter nya berasal dari MainActivity, parameter nya untuk bagian ini adalah nama username yang digunakan untuk menampilkan list following dan menampikan url dari followers untuk menampilkan list followers. 
