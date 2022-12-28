package kutu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ogrenci {
	 VeriTabani v=new VeriTabani();
		
		Scanner sc=new Scanner(System.in);
		Scanner s=new Scanner(System.in);
		
		public void ogrenci_Kayit() throws SQLException {
			Statement st=v.bagla();
		    
			
			System.out.println("Ogrenci no giriniz=");
			int ogrenci_no=s.nextInt();
			
			if(ogrenci_var_mi(ogrenci_no)==false) {
			
		    System.out.println("Ogrenci adini giriniz=");
		    String ogrenci_ad=sc.nextLine();
		    
		  
		    
	    
		    
		    String sql=String.format("INSERT INTO ogrenci "
		    		+ "values(%d,'%s',NULL)",ogrenci_no,ogrenci_ad);
		    
		    st.executeUpdate(sql);
		    st.close();
			}else
				System.out.println(ogrenci_no+" nolu ogrenci zaten kayitli.");
		    
		}
		
		public void ogrenci_listele() throws SQLException {
			Statement st=v.bagla();
			ResultSet rs=st.executeQuery("SELECT * FROM ogrenci");
			
			while(rs.next()) {
				System.out.println("Ogrenci adi="+rs.getString(2)+" Ogrenci no="+rs.getInt(1)
				+" Kitap="+rs.getString(3));
			}
			rs.close();
			st.close();
			
		}
		
		public void kitap_al() throws SQLException {
			Statement myst=v.bagla();
			
			ResultSet rs=v.rs("SELECT * FROM kitaplar");
			Kitap k=new Kitap();
			k.kitap_listele();
			
			System.out.println("Secilen kitap ismini giriniz=");
			String secim=sc.nextLine();
			
			if(k.kitap_var_mi(secim)==true) {
			
			System.out.println("Ogrenci no giriniz:");
		    int ogrenci_no=s.nextInt();
		    
		    if(ogrenci_var_mi(ogrenci_no)==true) {
		    
		    if(ogrencide_kitap_var_mi(ogrenci_no,secim)==false) {	
		    
		    while(rs.next()) {
		    	if(secim.equals(rs.getString(1)))
		    		break;
		    }
		    
		    if(rs.getInt("adet")==0)
		    	System.out.println("kitap kalmadi.");
		    else {
			String sql=String.format("UPDATE ogrenci SET kitap='%s' WHERE ogrencino=%d",secim,ogrenci_no);
			myst.executeUpdate(sql);
			myst.close();
			
			k.kitap_adet_eksilt(secim);
			System.out.println(ogrenci_no+" nolu ogrenciye "+secim+" adli kitap verildi.");
		    }
			rs.close();
		    }else
		    	System.out.println("en fazla 1 adet kitap alinabilir.");
		    }else
		    	System.out.println("ogrenci bulunamadi.");
			}else
				System.out.println("kitap bulunamadi.");
		}
		
		public void kitap_teslim() throws SQLException {
			Kitap k=new Kitap();
			System.out.println("Teslim edilen kitap ismini giriniz=");
			String secim=sc.nextLine();
	
			
			if(k.kitap_var_mi(secim)==true) {
			
			System.out.println("Ogrenci no giriniz");
		    int ogrenci_no=s.nextInt();
		    
		    if(ogrenci_var_mi(ogrenci_no)==true) {
		    	
		    if(ogrencide_kitap_var_mi(ogrenci_no,secim)==true)	{
		    	
		    if(teslim_edilen_kitap(ogrenci_no,secim)==true) {
		    
		    Statement myst=v.bagla();
			String sql=String.format("UPDATE ogrenci SET kitap=NULL WHERE ogrencino=%d",ogrenci_no);
			myst.executeUpdate(sql);
			k.kitap_adet_arttir(secim);
			
			System.out.println(ogrenci_no+" nolu ogrenci "+secim+" adli kitabi teslim etti.");
		    }else
		    	System.out.println("Kullanicida bu kitap bulunmamaktadir.");
		    
		    }else
		    	System.out.println("ogrencide kitap bulunmamaktadir.");
		    
			}else
				System.out.println("Ogrenci bulunamadi.");
			}else
				System.out.println("kitap bulunmadi");
		}
		
		public boolean ogrenci_var_mi(int ogrencino) throws SQLException {
			ResultSet rs=v.rs("SELECT * FROM ogrenci");
			while(rs.next()) {
				if(ogrencino==rs.getInt(1))
					return true;
			}
			return false;
		}
		
		public boolean ogrencide_kitap_var_mi(int ogrenci_no,String kitap_adi) throws SQLException {
			ResultSet rs=v.rs("SELECT * FROM ogrenci");
			while(rs.next()) {
				if(ogrenci_no==rs.getInt(1))
					break;
			}
			if(rs.getString(3)!=null)
				return true;
			
			return false;
		}
		
		public boolean teslim_edilen_kitap(int ogrenci_no,String kitap_adi) throws SQLException {
			ResultSet rs=v.rs("SELECT * FROM ogrenci");
			while(rs.next()) {
				if(ogrenci_no==rs.getInt(1))
					break;
			}
			if(kitap_adi.equals(rs.getString(3)))
				return true;
			
			return false;
		}
}
