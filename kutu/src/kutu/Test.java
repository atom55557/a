package kutu;

import java.sql.SQLException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Kitap kitaplar=new Kitap();
		Ogrenci ogrenciler=new Ogrenci();
		Scanner sc=new Scanner(System.in);
		Scanner s=new Scanner(System.in);
		boolean ft=true;
		System.out.println("*****-kutuphaneye hosgeldiniz.******");
		while(ft) {
			System.out.println("*************************");
			System.out.println("1-ogrenci kayit.");
			System.out.println("2-kitap ekle.");
			System.out.println("3-kitap kaldir.");
			System.out.println("4-kitap teslim al.");
        	System.out.println("5-kitap ver.");
        	System.out.println("6-kayitli ogrenci listele.");
        	System.out.println("7-kitap listele.");
        	System.out.println("8-kitap adet arttir.");
        	System.out.println("0-cikis.");
        	int secim=s.nextInt();
        	switch(secim) {
        	case 0:ft=false;
        		break;
        	case 1:ogrenciler.ogrenci_Kayit();
        		break;
        	case 2:kitaplar.kitap_ekle();
        		break;
        	case 3:System.out.println("Silmek istediginiz kitabin ismini girin:");
        		String name=sc.nextLine();
        		kitaplar.kitap_sil(name);
        		break;
        	case 4:ogrenciler.kitap_teslim();
        		break;
        	case 5:ogrenciler.kitap_al();
        		break;
        	case 6:ogrenciler.ogrenci_listele();
        		break;
        	case 7:kitaplar.kitap_listele();
        		break;
        	case 8:System.out.println("eklemek istediginiz kitabin ismini giriniz:");
        		String a=sc.nextLine();
        		System.out.println("adet:");
        		int b=s.nextInt();
        		kitaplar.kitap_adet_arttir(b,a);
        		break;
        	default:System.out.println("1-8 arasi deger girin.");
        		break;
        	}
        }

	}
//kaynakça https://mertcangokgoz.com/temel-mysql-komutlari/
}
