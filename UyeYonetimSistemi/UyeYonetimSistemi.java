//Kütüphaneleri ekliyoruz
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Üye sınıfımızı tanımlıyoruz
class Uye {
	//Üye sınıfının alacağı parametreler
    private String ad;
    private String soyad;
    private String email;

    public Uye(String ad, String soyad, String email) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getEmail() {
        return email;
    }
}

//Üye sınıfından elit üye sınıfı türetiyoruz
class ElitUye extends Uye {
    public ElitUye(String ad, String soyad, String email) {
        super(ad, soyad, email);
    }
}

//üye sınıfından genel üye sınıfı türetiyoruz
class GenelUye extends Uye {
    public GenelUye(String ad, String soyad, String email) {
        super(ad, soyad, email);
    }
}

public class UyeYonetimSistemi {
	//elit üye ve genel üye sınıf verilerinin tutulacağı arraylist tanımlıyoruz
    private static ArrayList<ElitUye> elitUyeler = new ArrayList<ElitUye>();
    private static ArrayList<GenelUye> genelUyeler = new ArrayList<GenelUye>();
	//txt dosyasını tanımlıyoruz
    private static final String dosyaAdi = "uyeler.txt";

    public static void main(String[] args) {
		//kullanıcının klavye girişlerini almak için Scanner tanımlıyoruz
        Scanner scanner = new Scanner(System.in);

        while (true) {
			//ekrana yazdırıyoruz
            System.out.println("1- Elit üye ekleme");
            System.out.println("2- Genel Üye ekleme");
            System.out.println("3- Mail Gönderme");
            System.out.println("4- Çıkış");

			//seçimi yakalamak için değişken
            int secim = scanner.nextInt();

			//kullanıcı seçimine göre sonuç döndürme işlemi
            switch (secim) {
            case 1:
                System.out.print("Elit üye adı: ");
                String elitAd = scanner.next();

                System.out.print("Elit üye soyadı: ");
                String elitSoyad = scanner.next();

                System.out.print("Elit üye email: ");
                String elitEmail = scanner.next();

                ElitUye elitUye = new ElitUye(elitAd, elitSoyad, elitEmail);
                elitUyeler.add(elitUye);
                kaydet();
                break;

            case 2:
                System.out.print("Genel üye adı: ");
                String genelAd = scanner.next();

                System.out.print("Genel üye soyadı: ");
                String genelSoyad = scanner.next();

                System.out.print("Genel üye email: ");
                String genelEmail = scanner.next();

                GenelUye genelUye = new GenelUye(genelAd, genelSoyad, genelEmail);
                genelUyeler.add(genelUye);
                kaydet();
                break;

				case 3:
				//ekrana yaz
				System.out.println("1- Elit üyelere mail");
				System.out.println("2- Genel Üyelere mail");
				System.out.println("3- Tüm üyelere mail");
			
				//mail seçimini yakalama
				int mailSecim = scanner.nextInt();
			
				System.out.print("Mail mesajınız: ");
				String mailMesaj = scanner.next();
				
				//mail seçimine göre sonuç döndürme
				switch (mailSecim) {
					case 1:
						for (ElitUye eUye : elitUyeler) {
							mailGonder(eUye.getEmail(), mailMesaj);
						}
						break;
			
					case 2:
						for (GenelUye gUye : genelUyeler) {
							mailGonder(gUye.getEmail(), mailMesaj);
						}
						break;
			
					case 3:
						for (ElitUye eUye : elitUyeler) {
							mailGonder(eUye.getEmail(), mailMesaj);
						}
			
						for (GenelUye gUye : genelUyeler) {
							mailGonder(gUye.getEmail(), mailMesaj);
						}
						break;
			
					default:
						System.out.println("Geçersiz seçim");
				}
				break;
			
            case 4:
                System.out.println("Programdan çıkılıyor...");
                System.exit(0);
                break;

            default:
                System.out.println("Geçersiz seçim!");
                break;
            }
        }
    }

    private static void mailGonder(String email, String mesaj) {
		// Bu metotda e-mail gönderimi yapılacak.
        System.out.println("Mail gönderildi: " + email + " -> " + mesaj);
    }

    private static void kaydet() { //text dosyasına kaydetme metodu
        try {
            FileWriter writer = new FileWriter(dosyaAdi);
            for (ElitUye elitUye : elitUyeler) {
                writer.write("ElitUye," + elitUye.getAd() + "," + elitUye.getSoyad() + "," + elitUye.getEmail() + "\n");
            }
            for (GenelUye genelUye : genelUyeler) {
                writer.write("GenelUye," + genelUye.getAd() + "," + genelUye.getSoyad() + "," + genelUye.getEmail() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }
}