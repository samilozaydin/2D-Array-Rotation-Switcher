package Project2;

import java.util.Scanner;

/**
 * @file Project 2
 * @description Program menu tabanli olup bir dizinin kenarlarinda bulunan
 * elamanlari dondurur
 * @assignment Project 2
 * @date 12.12.2020
 * @author Samil Bilal OZAYDIN samilbilal.ozaydin@stu.fsm.edu.tr
 */
public class Project2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] ayarlar = {5, 5, -1, 100, 2};//Programin acilis standart ayarlari bu yonde olacak.
        int[][] matris = new int[ayarlar[0]][ayarlar[1]];
        for (int i = 0; i < ayarlar[0]; i++) {
            for (int j = 0; j < ayarlar[1]; j++) {
                matris[i][j] = (int) (Math.random() * ayarlar[3]);
            }
        }
        System.out.println("************Matrislerin kose elemanlarini dondurme programina hosgeldiniz************");
        System.out.println("1. Uygulamayi calistir");
        System.out.println("2. Matris olustur");
        System.out.println("3. Direction");
        System.out.println("4. Step number");
        System.out.println("5. Exit");
        System.out.print("Secenek: ");
        String secenek = sc.nextLine();
        menuIslem(secenek, ayarlar, matris);//girilen secenegin dogrulugunu kontrol eden (1-5 arasi sayi olmalidir) ve
//menu iceriklerinin uygulanmasini saglayan metotdur.
    }

    public static void menuIslem(String secenek, int[] ayarlar, int[][] matris) {
        Scanner sc2 = new Scanner(System.in);// Metot icinde input alimini saglar
        boolean kontrolcu = true;
        boolean ayarlarDogrumu = true;

        while (kontrolcu) {//kontrolcu true oldugu muddet menu calismaya devam eder.
            switch (secenek) {
                case "1":
                    ayarlarDogrumu = ayarlarKontrol(ayarlar);//Ayarlar dizisinin icerigi uygulamayi baslatma amaciyla kontrol edilir.
                    if (ayarlarDogrumu == true) {
                        System.out.println("*************************************");
                        System.out.println("Anlik matrisiniz: ");
                        ekranaYansit(matris);//Kullanicin karsisina anlik matrisini cikartir
                        for (int i = 0; i < ayarlar[4]; i++) {//Burada yer degistirme metodunun kac defa tekrarlanacagi belirlenir.
                            matris = islem(ayarlar, matris);//Matris dondurme islemleri burada yapilir.
                        }
                        System.out.println("Degistirilmis matrisiniz: ");
                        ekranaYansit(matris);

                    } else {
                        System.out.print("Secenek: ");
                        secenek = sc2.nextLine();
                    }
                    break;
                case "2":
                    matrisOlustur(ayarlar);//Matrisin sutun,satir ve elemanlari burada olusturulur
                    matris = new int[ayarlar[0]][ayarlar[1]];//olusturulmak istenen matris eleamansiz olarak olustu
                    elemanAtama(matris, ayarlar[3]);//Default olan matris elemanlarini belirlenen araliklardaki sayilarla random doldurur.
                    break;
                case "3":
                    yonAtama(ayarlar);//Matrisin hangi tarafa donduruldugu burada belirlenir.
                    break;
                case "4":
                    adimSayisiAtama(ayarlar);//Kac defa dondurulecegi burada belirlenir.
                    break;
                case "5":
                    System.out.println("Program Sonlandirilmistir...");
                    kontrolcu = false;
                    break;
                default:
                    System.out.println("Lutfen girdiginiz ifade 1-5 araliginda bir rakam olsun");
                    break;
            }
            if (!((int) (secenek.charAt(0)) == (int) ('5'))) {//5 girilmesi durumunda bu noktadan iceri girilemeyecek.
                System.out.println("*****************************************");
                System.out.println("Yeni komunutunuz nedir?: ");
                System.out.println("1. Uygulamayi calistir");
                System.out.println("2. Matris olustur");
                System.out.println("3. Direction");
                System.out.println("4. Step number");
                System.out.println("5. Exit");
                System.out.print("Secenek: ");
                secenek = sc2.nextLine();
            }
        }
    }

    public static boolean ayarlarKontrol(int[] ayarlar) {
        for (int i = 0; i < ayarlar.length; i++) {//Ayarlar dizisini gezmeye yarar. 
            if (ayarlar[i] == 0) {//ayarlar dizisinin 0 olmasi dahilinde false dondurur ve kullaniciya hatalarini gormesi adina anlik durum bildirir
                System.out.println("Hali hazirda bulunan satir-sutun sayisi, dondurulecek yon, matris elemanlari ve donecek miktar 0 olamaz. Ayarlarinizi kontrol ediniz.");
                System.out.println("Anlik durumunuz:");
                System.out.println("Satir/Sutun sayiniz: " + ayarlar[0] + "/" + ayarlar[1]);
                System.out.println("Donulecek yonununz: " + ayarlar[2]);
                System.out.println("Sayi araliginiz 0'dan " + ayarlar[3] + "'a kadardir");
                System.out.println("Matris su kadar dondurulecektir: " + ayarlar[4]);
                System.out.println("*********************************************************");
                return false;
            }
        }
        return true;
    }

    public static void matrisOlustur(int[] ayarlar) {
        Scanner sc3 = new Scanner(System.in);//metot icinde input almayi saglar.
        boolean kontrolcu = true;
        String satir = "";
        String sutun = "";
        String sayiAraligi = "";

        System.out.print("Lutfen satir sayinizi giriniz: ");
        while (kontrolcu) {//kontrolcu true oldugu surece tekrardan deger girilecek. Hatali giris dongunun true dondurulmesine neden olacak.
            satir = sc3.nextLine();
            kontrolcu = satirSutunKontrol(satir);//Girilen degerin uygunlugunu kontrol eder ve buna bagli true/false dondurur
            if (kontrolcu == true) {
                System.out.print("Satir sayinizi tekrardan giriniz: ");
            }
        }

        kontrolcu = true;
        System.out.print("Lutfen sutun sayinizi giriniz: ");
        while (kontrolcu) {//kontrolcu true oldugu surece tekrardan deger girilecek. Hatali giris dongunun true dondurulmesine neden olacak.
            sutun = sc3.nextLine();
            kontrolcu = satirSutunKontrol(sutun);//Girilen degerin uygunlugunu kontrol eder
            if (kontrolcu == true) {
                System.out.print("Sutun sayinizi tekrardan giriniz: ");
            }
        }
        kontrolcu = true;
        ayarlar[0] = sayiyaDondur(satir);
        ayarlar[1] = sayiyaDondur(sutun);

        System.out.print("Matris icindeki elemanlar 0'dan hangi sayiya kadar eleman icersin?: ");
        while (kontrolcu) {//kontrolcu true oldugu surece tekrardan deger girilecek. Hatali giris dongunun true dondurulmesine neden olacak.
            sayiAraligi = sc3.nextLine();
            kontrolcu = sayiAraligiKontrol(sayiAraligi);
            if (kontrolcu == true) {
                System.out.print("Sayi araligini tekrardan giriniz: ");
            }
        }
        ayarlar[3] = sayiyaDondur(sayiAraligi);

        System.out.println("Yeni matrsiniz olusturuldu.");
        System.out.println("Satir sayisi: " + ayarlar[0]);
        System.out.println("Sutun sayisi: " + ayarlar[1]);
        System.out.println("0'dan " + ayarlar[3] + " sayisina kadar eleman barindirir");
        anlikAyarlar(ayarlar);
    }

    public static boolean satirSutunKontrol(String girilen) {//Satir ve sutundeki ifadelerin uygunlugu kontrol edilir.
        for (int i = 0; i < girilen.length(); i++) {//Stringin her karakteri tek tek kontrol edilir.
            if ((int) girilen.charAt(i) < 48 || (int) girilen.charAt(i) > 57) {//Rakam olup olmadigi burada kontrol edilir.
                System.out.println("Girilen ifade rakam harici farkli bir karakter barindirmaktadir.");
                return true;
            }
        }
        int girilenSayi = sayiyaDondur(girilen);//Girilen sayi stringten integer degere dondurulur
        if (girilenSayi < 3) {//Matris boyutu 0'dan kucuk bir sayiya esit olmamalidir.BU SATIR DEGİSTİRİLEBİLİR!
            System.out.println("Girilen sayi 3'den kucuk olmamalidir.");
            return true;
        }
        return false;
    }

    public static int sayiyaDondur(String girilen) {
        int donusum = 0;//dondurulecek olan sayi
        int basamak = girilen.length() - 1;//girilen sayinin kac basamakli oldugunu icerir

        for (int i = 0; i < girilen.length(); i++) {
            donusum += (((int) girilen.charAt(i) - 48) * ((int) (Math.pow(10, basamak))));//Her basamak tek tek islenir
            --basamak;
        }
        return donusum;
    }

    public static boolean sayiAraligiKontrol(String girilen) {// BU ALAN DEGİSTİRİLEBİLİR!! MATRİSİN BOYUTUNU 1X1'DEN FAZLA ALABİLİRSİN. 116.SATİR
        for (int i = 0; i < girilen.length(); i++) {//girilen sayinin her harfi tek tek kontrol edilir. Araya sayidan baska karaker girilmemeli
            if ((int) girilen.charAt(i) < 48 || (int) girilen.charAt(i) > 57) {
                System.out.println("Girilen ifade rakam harici farkli bir karakter barindirmaktadir.");
                return true;
            }
        }
        int girilenSayi = sayiyaDondur(girilen);//Girilen sayi stringten integer degere dondurulur
        if (girilenSayi < 1) {//sayi araligi 0'dan herhangi bir sayiya kadar olmalidir.
            System.out.println("Girilen sayi 0'a esit ya da 0'dan kucuk olmamalidir.");
            return true;
        }
        return false;
    }

    public static void elemanAtama(int[][] matris, int sayiAraligi) {//Default olan matris elemanlarini belirlenen araliklardaki sayilarla random doldurur.
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                matris[i][j] = (int) (Math.random() * sayiAraligi);
            }
        }
    }

    public static void yonAtama(int[] ayarlar) {//Matrisin hangi tarafa donecegini belirleyen metottur.
        Scanner sc4 = new Scanner(System.in);
        String yon = "";
        boolean kontrolcu = true;

        System.out.print("Saat yonune donmek icin '-1' saat yonunun tersine donmek icin '1' giriniz: ");
        while (kontrolcu) {//kontrolcu true oldugu surece tekrardan deger girilecek. Hatali giris dongunun true dondurulmesine neden olacak.
            yon = sc4.nextLine();
            kontrolcu = yonKontrol(yon);//Girilen degerin uygunlugunu kontrol eder ve buna bagli true/false dondurur
            if (kontrolcu == true) {
                System.out.print("Yonunuzu tekrardan giriniz: ");
            }
        }
        ayarlar[2] = yonSayiyaDondur(yon);//String burada integer degere metotla donusturulup sonra atanir.
        System.out.println("Yon " + ayarlar[2] + " tarafina dogru girildi.");
        anlikAyarlar(ayarlar);

    }

    public static boolean yonKontrol(String gelenYon) {//girilen yonun dogrulugu burada kontrol edilir
        int tutucu = 0;
        boolean kontrolcu = true;
        for (int i = 0; i < gelenYon.length(); i++) {
            kontrolcu = true;
            if ((((int) (gelenYon.charAt(i)) == (int) ('1')) || ((int) (gelenYon.charAt(i)) == (int) ('-'))) && gelenYon.length() < 3) {
//girilen ifadenin '-' veya 1'den farkli olmasini engeller ve 2 karakterden fazla karakter girilmesi engellenir..
                if (((int) gelenYon.charAt(i) == (int) ('1'))) {//karsilasilan ifade 1 ise tutucu 1 arttirilir.
                    tutucu++;
                    if (tutucu > 1) {// 2 adet '1' girilmesini engeller.
                        System.out.println("Bu bolumde en fazla 1 veya -1 girebilirsiniz");
                        return true;
                    }
                } else if ((i > 0) && (gelenYon.charAt(i) == '-')) {// '1-' girilmesini engeller.
                    System.out.println("Bu konumda '-' bulunamaz.");
                    return true;
                }
                if (gelenYon.length() == 1 && gelenYon.charAt(i) == '-') {// Sadece '-' girilmesini engeller
                    System.out.println("Bu konumda '-' tek basina bulunamaz.");
                    return true;

                }
            } else {//istenilenden uzun ifade veya farkli karakter girildiginde kontrolcu false olmali.
                kontrolcu = false;
            }
            if (kontrolcu == false) {
                System.out.println("Bu bolumde 2 karakterden fazla ve '-' ile '1' disinda karakter giremezsiniz.");
                return true;
            }
        }
        return false;
    }

    public static int yonSayiyaDondur(String yon) {//yon icin yazilan stringi ("-1" veya "1") integer degere dondurur
        if ((int) (yon.charAt(0)) == (int) ('-')) {
            return -1;
        }
        return 1;
    }

    public static void adimSayisiAtama(int[] ayarlar) {//matrisin kac defa dondurulecegi burada belirlernir.
        Scanner sc5 = new Scanner(System.in);
        String adimSayisi = "";
        boolean kontrolcu = true;

        System.out.print("Kac adimli olmasini istersiniz?: ");
        adimSayisi = sc5.nextLine();
        while (kontrolcu) {//Istenilen aralikta adim sayisi girilmedigi muddet dongu devam eder
            kontrolcu = adimSayisiKontrol(adimSayisi);//Adim sayisinin dogrulugu burada kontrol edilir ve true/false dondurulur.
            if (kontrolcu == true) {//Yanlis girdi sonucu tekrar girilnesi istenir.
                System.out.print("Lutfen adim sayisini tekrar giriniz: ");
                adimSayisi = sc5.nextLine();
            }
        }
        ayarlar[4] = sayiyaDondur(adimSayisi);//Stringe girilen sayi integera doner ve arraya atanir.
        System.out.println("Adim sayisi " + ayarlar[4] + " olarak atandi.");
        anlikAyarlar(ayarlar);
    }

    public static boolean adimSayisiKontrol(String gelenAdimSayisi) {//adim sayisinin dogrulugu burada kontrol edilir
        boolean kontrolcu = true;

        for (int i = 0; i < gelenAdimSayisi.length(); i++) {
            if ((int) gelenAdimSayisi.charAt(i) < 48 || (int) gelenAdimSayisi.charAt(i) > 57) {//Sadece rakamlarin stringte olabilmesini saglar
                System.out.println("Girilen ifade rakam harici farkli bir karakter barindirmaktadir.");
                return true;
            }
        }
        for (int i = 0; i < gelenAdimSayisi.length(); i++) {
            if (gelenAdimSayisi.length() == 1 && (int) gelenAdimSayisi.charAt(0) == (int) ('0')) {//0, degerinin girilmesini engeller
                System.out.println("Girilen sayi 0 olamaz");
                return true;
            } else if (gelenAdimSayisi.length() > 1 && i < (gelenAdimSayisi.length() - 1)) {//00, 000,0000  gibi degerlerin girilmesini engeller
                if (gelenAdimSayisi.charAt(i) == gelenAdimSayisi.charAt(i + 1)) {
                    kontrolcu = false;
                } else {
                    kontrolcu = true;
                    break;
                }
            }
        }
        if (kontrolcu == false) {
            System.out.println("00, 000 gibi degerler girilemeyip girilen deger 0'dan buyuk olmalidir");
            return true;
        }
        return false;
    }

    public static int[][] islem(int[] ayarlar, int[][] matris) {//Matris ayarlar dizisi dogrultusunda dondurme islemi yapar
        int[][] tutucuMatris = new int[matris.length][matris[0].length];

        if (ayarlar[2] == -1) {//Saat yonune dogru dondurulur
            for (int i = 0; i < matris.length; i++) {
                for (int j = 0; j < matris[i].length; j++) {
                    if (i == 0 && j != matris[i].length - 1) {//Ust kisim burada gecici matrise atanir
                        tutucuMatris[i][j + 1] = matris[i][j];
                    }
                    if (i == matris.length - 1 && j != matris[i].length - 1) {//matrisin alt kismi burada gecici matrise atanir
                        tutucuMatris[i][j] = matris[i][j + 1];
                    }
                }
            }
            for (int i = 0; i < matris.length; i++) {
                for (int j = 0; j < matris[i].length; j++) {
                    if (i != matris.length - 1 && j == matris[i].length - 1) {//matirisin sag kismi burada gecici matrise atanir
                        tutucuMatris[i + 1][j] = matris[i][j];
                    }
                    if (i != 0 && j == 0) {//matrisin sol kismi burada gecici matrise atanir
                        tutucuMatris[i - 1][j] = matris[i][j];
                    }
                }
            }
        } else {//Saat yonunun tersine dondurulur
            for (int i = 0; i < matris.length; i++) {
                for (int j = 0; j < matris[i].length; j++) {
                    if (i == 0 && j != 0) {//matrisin ust kismi burada gecici matrise atanir
                        tutucuMatris[i][j - 1] = matris[i][j];
                    }
                    if (i == matris.length - 1 && j != matris[0].length - 1) {//matrisin alt kismi burada gecici matrise atanir
                        tutucuMatris[i][j + 1] = matris[i][j];
                    }
                }
            }
            for (int i = 0; i < matris.length; i++) {
                for (int j = 0; j < matris[i].length; j++) {
                    if (i != matris.length - 1 && j == 0) {//matrisin sol kismi burada gecici matrise atanir
                        tutucuMatris[i + 1][j] = matris[i][j];
                    }
                    if (i != 0 && j == matris[i].length - 1) {//matrisin sag kismi burada gecici matrise atanir
                        tutucuMatris[i - 1][j] = matris[i][j];
                    }
                }
            }
        }
        for (int i = 1; i < matris.length - 1; i++) {//Kenarlari degistirilmis olan ana dizinin icinde kalan bolge doldururlur
            for (int j = 1; j < matris[1].length - 1; j++) {
                tutucuMatris[i][j] = matris[i][j];
            }
        }
        return tutucuMatris;
    }

    public static void ekranaYansit(int[][] matris) {//Elde bulunan matrisi ekrana yazdirmaya saglayan metotdur
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                System.out.print(matris[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void anlikAyarlar(int[] ayarlar) {//Ayarlarin anlik durumunu veren metottur
        System.out.println("Ayarlarin anlik durumu: ");
        System.out.println("{" + ayarlar[0] + "," + ayarlar[1] + "," + ayarlar[2] + "," + ayarlar[3] + "," + ayarlar[4] + ")");
    }
}
