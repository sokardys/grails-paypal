package org.grails.paypal

import java.text.ParseException
import java.text.NumberFormat

class PaymentItem implements Serializable {
	BigDecimal amount
    BigDecimal discountAmount = 0
	String itemName
	String itemNumber
	Integer quantity = 1

	static belongsTo = [payment:Payment]

	static constraints = {
		itemName blank:false
		itemNumber blank:false
		amount bindable:false
		discountAmount bindable:false
	}

	//Metodo que convierte de forma Locale independient String to BigDecimal
	static BigDecimal convertStringToBigDecimal(String numberString){
		BigDecimal dev = null
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        try {
            dev = new BigDecimal(nf.parse(numberString).toString());
        } catch (ParseException e) {
            e.printStackTrace();
            dev = null
        }
        dev
    }

	String toString(){
		"${quantity} (#${itemNumber}) ${itemName} -> ${amount}/${discountAmount}"
	}
}
