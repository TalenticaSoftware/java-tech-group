package org.talentica.records;

import java.math.BigDecimal;

record OrderItemDTO(String productId, Long quantity, BigDecimal price) implements
    IOrderItem {


  private static String A_STATIC_FIELD = "Some Field";

  //private int anInstanceVariable = 0;

  @Override
  public BigDecimal total() {
    return BigDecimal.valueOf(quantity).multiply(price);
  }

}
