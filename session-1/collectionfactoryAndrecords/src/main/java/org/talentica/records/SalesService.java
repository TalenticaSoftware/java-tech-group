package org.talentica.records;

import java.util.List;
import java.util.stream.Collectors;

public class SalesService {

  List<Merchant> findTopMerchants(List<Merchant> merchants, int month) {

    // Local record
    record MerchantSales(Merchant merchant, double sales) { }

    return merchants.stream()
        .map(merchant -> new MerchantSales(merchant, computeSales(merchant, month)))
        .sorted((m1, m2) -> Double.compare(m2.sales(), m1.sales()))
        .map(MerchantSales::merchant)
        .collect(Collectors.toList());
  }

  private double computeSales(Merchant merchant, int month) {
    return 0D;
  }

}