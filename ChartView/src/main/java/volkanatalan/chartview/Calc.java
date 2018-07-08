package volkanatalan.chartview;

import java.util.ArrayList;

public class Calc {
  
  public static double round(double value, int decimalNumberAfterDot) {
    ArrayList<String> valueChars = new ArrayList<>();
    int previousNum = 0;
    String valueS = ""+value;
    int indexOfDot = valueS.indexOf(".");
    for (int i = 0; i < valueS.length(); i++) {
      valueChars.add(""+valueS.charAt(i));
    }
    for (int i = valueS.length() - 1; i >= 0; i--) {
      String c = valueChars.get(i);
      if (!c.equals(".")) {
        int num = Integer.valueOf(c);
        
        // on end
        if (i == valueS.length() - 1) {
          previousNum = num;
          valueChars.remove(i);
          
          // on after dot
        }else if (i == indexOfDot + decimalNumberAfterDot) {
          if (previousNum > 4) {
            ++num;
            previousNum = num;
            valueChars.set(i, ""+ (num % 10));
          } else {
            previousNum = num;
          }
        }else if (i < indexOfDot + decimalNumberAfterDot) {
          if (i > 0) {
            if (previousNum > 9) {
              ++num;
              previousNum = num;
              valueChars.set(i, ""+ (num % 10));
            } else {
              previousNum = num;
            }
          } else {
            if (previousNum > 10) {
              ++num;
              valueChars.set(i, ""+ (num % 10));
              if (num > 9) {
                valueChars.add(i, ""+ (num / 10));
              }
            }
          }
          
          // on middle
        }else {
          if (previousNum > 4) {
            ++num;
            previousNum = num;
            valueChars.remove(i);
          } else {
            previousNum = num;
            valueChars.remove(i);
          }
        }
      }
    }
  
    String resultString = "";
    for (String ch : valueChars) resultString += ch;
    return Double.valueOf(resultString);
  }
}
