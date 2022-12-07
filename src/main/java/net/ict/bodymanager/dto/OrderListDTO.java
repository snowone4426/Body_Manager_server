package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderListDTO {

    private List<OrderRequestDTO> order_list;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class OrderRequestDTO {

        private String id;

        private String type;

        private String count;

        private String start;

    }


}
