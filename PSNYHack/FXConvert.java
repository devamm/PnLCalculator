package PSNYHack;

public class FXConvert {

  public static double convertFXtoUSD (String CurrencyName) {

      double conversionRatio;

      switch (CurrencyName) { 
        case "USD": 
            conversionRatio = 1.00; 
            break; 
        case "EUR": 
            conversionRatio = 0.86; 
            break; 
        case "JPY": 
            conversionRatio = 112.21; 
            break; 
        case "HKD": 
            conversionRatio = 7.84; 
            break; 
        case "NTD": 
            conversionRatio = 30.91; 
            break; 
        case "KRW": 
            conversionRatio = 1131.35; 
            break; 
        case "CNY": 
            conversionRatio = 6.92; 
            break; 
        default: 
            conversionRatio = 0.00; 
            break; 
        };
    
    return conversionRatio;

  }

}