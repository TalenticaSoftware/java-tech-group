package org.talentica.records;

import java.util.List;

record OrderDTO(String orderId, String userId, List<OrderItemDTO> items) {

}
