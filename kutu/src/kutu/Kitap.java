package kutu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Kitap {
	static VeriTabani v=new VeriTabani();
    Scanner say=new Scanner (System.in);
	Scanner sc=new Scanner (System.in);
   
	
	public void kitap_ekle() throws SQLException {
	  
		Statement st=v.bagla();
		
	    
	    System.out.println("Kitap adini giriniz=");
	    String kitap_adi=sc.nextLine();
	    
	    
	    
	    System.out.println("Yazar adini giriniz=");
	    String yazar_adi=sc.nextLine();
	    
	    System.out.println("Kitap adedini giriniz=");
	    int kitap_adet=say.nextInt();
	    
	    if(kitap_var_mi(kitap_adi)==false) {
	    String sql=String.format("INSERT INTO kitaplar "
	    		+ "VALUES('%s','%s',%d)",kitap_adi,yazar_adi,kitap_adet);
	    
	    st.executeUpdate(sql);
	    System.out.println("basariyla eklendi.");
	    }else {
	    	System.out.println(kitap_adi+" isimli kitap zaten bulunmakta adedi kadar ekleniyor.");
	    	kitap_adet_arttir(kitap_adet,kitap_adi);
	    	}
	    
	}
	
	public void kitap_listele() throws SQLException {
		Statement st=v.bagla();
		ResultSet rs=st.executeQuery("SELECT * FROM kitaplar");
		
		while(rs.next()) {
			System.out.println("Kitap adi="+rs.getString(1)+" Yazar adi="+rs.getString(2)+
					" Kitap adedi="+rs.getInt(3));
		}
		rs.close();
		st.close();
		
	}
	
	public void kitap_sil(String ad) throws SQLException {
		Statement st=v.bagla();
		
		if(kitap_var_mi(ad)==true) {
		
		if(kitap_birisinde_bulunuyor_mu(ad)==false) {
		
		String sql=String.format("DELETE FROM kitaplar WHERE kitapAD='%s'",ad);
		st.executeUpdate(sql);
		System.out.println(ad+" adli kitap basariyla silindi");
		st.close();
		}else
			System.out.println("tum kitaplar teslim edilmedigi icin silinemez.");
		}else
			System.out.println("Kitap bulunamadi.");
	}
	
	public void kitap_adet_eksilt(String kitap_ismi) throws SQLException {
		
		Statement st=v.bagla();
		ResultSet rs=st.executeQuery("SELECT * FROM kitaplar");

		while(rs.next()) {
			if(kitap_ismi.equals(rs.getString("kitapAD"))) {

				break;
			}
				}
		
		if(rs.getInt(3)==0) {
		
			System.out.println("Kitap adedi eksiltilmedi");
		}else {

		String sql=String.format("UPDATE kitaplar SET adet=adet-1 WHERE kitapAD='%s'",kitap_ismi);
		st.executeUpdate(sql);
		rs.close();
		st.close();
		System.out.println("Kitap adedi eksiltildi.");
		}
	}
	
	public void kitap_adet_arttir(String kitap_ismi) throws SQLException {
		Statement st=v.bagla();
        if(kitap_var_mi(kitap_ismi)==true) {

		
		String sql="UPDATE kitaplar SET adet=adet+1 WHERE kitapAD='"+kitap_ismi+"'";
		
		st.executeUpdate(sql);
	
		st.close();
		System.out.println("Kitap adedi arttirildi.");
		}else
			System.out.println("Kitap bulunamadi.");
	}
	
	public void kitap_adet_arttir(int adet,String kitap_ismi) throws SQLException {
		Statement st=v.bagla();
        if(kitap_var_mi(kitap_ismi)==true) {

		
		String sql=String.format("UPDATE kitaplar SET adet=adet+%d WHERE kitapAD='%s'",adet,kitap_ismi);
		
		st.executeUpdate(sql);
	
		st.close();
		System.out.println("Kitap adedi "+adet +" kadar arttirildi.");
		}else
			System.out.println("Kitap bulunamadi.");
	}
	
	public boolean kitap_var_mi(String kitap_ismi) throws SQLException {
		ResultSet rs=v.rs("SELECT * FROM kitaplar");
		while(rs.next()) {
			if(kitap_ismi.equals(rs.getString(1)))
				return true;

		}
		return false;
		
	}
	
	public boolean kitap_birisinde_bulunuyor_mu(String kitap_ismi) throws SQLException{
		ResultSet rs=v.rs("SELECT * FROM ogrenci");
		while(rs.next()) {
			if(kitap_ismi.equals(rs.getString(3))) {
				return true;
			}
		}
		
		
		return false;
	}
}
