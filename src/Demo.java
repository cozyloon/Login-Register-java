import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
//        System.out.println(String.format("Rs. %1$.2f has been from a/c no AC- %2$04d-xx",1200f ..,123));
/*
        NumberFormat nf=NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(true);
        System.out.println(nf.format(1200));
*/

  /*      Calendar c=Calendar.getInstance();
        c.set(2010,Calendar.MAY,7);

        Date utilDate=c.getTime();
        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
        LocalDate localDate=sqlDate.toLocalDate();
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
*/
  /*
  //////////////////////////////////////////////////////////////////
        double d1 = 0.3;
        double d2 = 0.2;
  */      //System.out.println(d1 - d2);


/*
        BigDecimal b1=BigDecimal.valueOf(d1);
        BigDecimal b2=BigDecimal.valueOf(d2);
        System.out.println(b1.subtract(b2));
*/

/*

        BigDecimal b1 = new BigDecimal("0.3");
        BigDecimal b2 = new BigDecimal("0.2");
        System.out.println(b1.subtract(b2));
*/

    }
}
