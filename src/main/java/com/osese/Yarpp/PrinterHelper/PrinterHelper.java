package com.osese.Yarpp.PrinterHelper;



import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.osese.Yarpp.Order.Order;
import com.osese.Yarpp.OrderLine.OrderLine;
import com.osese.Yarpp.Payment.Payment;
import com.osese.Yarpp.Receipt.Receipt;



public class PrinterHelper {
    
	private String text;
    
	private int linelong = 40;
    public PrinterHelper(){
        text = "";
    }
    
    
    public void setPrinterForKitchen(Order order) {
    	clear();
    	setLeftMargin(0);
    	
    	hl();
    	lf();
    	largeOn();
    		text += order.getDesk().getName();
    	largeOff();
    	lf();
    	text += "Kullanici: " + order.getUser().getName();
    	lf();
    	text += "Tarih: " + order.getStartDate().toLocaleString();
    	lf();
    	hl();
    	int index = 1;
    	
    	for(OrderLine orderline : order.getOrderLines()) {
    		text += index + ". "+orderline.getProductName() + " x" + orderline.getCount() + "...."+ orderline.getTotal();
    		if(orderline.getNote() != null) {
    			lf();
    			text += "Not: " + orderline.getNote();
    		}
    		index++;
    		lf();
    	};
    	
    	lf();
    	feedAndCut();
    }
    
   
    public void setPrinterForKasa(Receipt receipt) {
    	clear();
    	setLeftMargin(0);
    	
    	hl();
    	lf();
    	largeOn();
    		text += receipt.getTableName();
    	largeOff();
    	lf();
    	text += "Kullanici: " + receipt.getUserName();//order.getUser().getName();
    	lf();
    	text += "Tarih: " + receipt.getEndDate().toLocaleString();
    	lf();
    	hl();
    	lf();
    	int index = 1;
    	underLineOn();
    	text += "Siparisler";
    	underLineOff();
    	lf();
    	for(Order order : receipt.getOrders()) {
	    	for(OrderLine orderline : order.getOrderLines()) {
	    		text += index + ". "+orderline.getProductName() + " x" + orderline.getCount() + "...."+ orderline.getTotal();
	    		
	    		index++;
	    		lf();
	    	}
    	}
    	hl();
    	lf();
    	text += "Toplam: " + receipt.getTotal();
    	lf();
    	if(!receipt.getPayments().isEmpty()) {
    		underLineOn();
    		text += "Odemeler";
    		underLineOff();
    		lf();
    		int ind = 1;
	    	for(Payment payment: receipt.getPayments()) {
	    		text += ind + ". " + payment.getAmount() + " TL ödendi.";
	    		lf();
	    	}
	    	hl();
	    	text += "Kalan: " + (receipt.getTotal() - receipt.getPayed());
	    	lf();
    	}
    	lf();
    	lf();
    }
    
    public void printTo(String pos) throws PrintException{
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        InputStream is = new ByteArrayInputStream(text.getBytes());
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet(); 
        
        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor,null);
        
        for(PrintService service : services) {
        	if(service.getName().equals(pos)){
        		DocPrintJob pj = service.createPrintJob();
        		Doc doc = new SimpleDoc(is, flavor, null);
        		pj.print(doc, null);

        		break;

        	}
        }
        
    }
    
    public void printToConsole(){
        System.out.println(text);
    }
    
    
    private String charMul(char x, int mul){
        String s = "";
        for(int i=0; i<mul; i++){
            s +=  Character.toString(x);
        }
        return s;
    }  
    private void clear(){
        text = "";
    }
    private void setLeftMargin(int m){
        text +=   Character.toString((char)29) +  Character.toString((char)76) +  Character.toString((char)20) +  Character.toString((char)m);
    }
    private void largeOn(){
        text += Character.toString((char)27) + Character.toString((char)33) +  Character.toString((char)16) ;
        text += Character.toString((char)27) + Character.toString((char)33) +  Character.toString((char)32) ;
    }
    private void largeOff(){
        text += Character.toString((char)27) + Character.toString((char)33) +  Character.toString((char)0) ;
    }
    
    private void underLineOn() {
    	text += Character.toString((char)27) + Character.toString((char)33) +  Character.toString((char)128);
    }
    private void underLineOff() {
    	text += Character.toString((char)27) + Character.toString((char)33) +  Character.toString((char)0) ;
    }
    
    private void inverseOn(){
        text += Character.toString((char)29) + Character.toString((char)66) + Character.toString((char)1);
    }
    private void inverseOff(){
        text += Character.toString((char)29) + Character.toString((char)66) + Character.toString((char)0);
    }
    private void lf(){
        text += Character.toString((char)10);
    }
    private void lf(int n){
        for(int i=0; i<n; i++){
            lf();
        }
    }
    private void hl(){
        text += charMul('_', 40) + "\n";
    }
    private void hd(){
        text += charMul('.', 40) + "\n";
    }

    private void feedAndCut(){
        lf(); lf(); lf();
        text += Character.toString((char)27) + Character.toString((char)105);
    }
    
}
