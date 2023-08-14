package order;

import java.util.List;

public class OrderData {
    public static Order getOrderData(){
        return new Order(List.of("61c0c5a71d1f82001bdaaa6d",
                "61c0c5a71d1f82001bdaaa7a",
                "61c0c5a71d1f82001bdaaa79",
                "61c0c5a71d1f82001bdaaa78"));
    }

    public static Order getOrderDataWrongIngredients(){
        return new Order(List.of("61c0c5a71d1f82001bd12345",
                "61c0c5a71d1f82001bd12345",
                "61c0c5a71d1f82001bd12345",
                "61c0c5a71d1f82001bd12345"));
    }

    public static Order getOrderDataWithoutIngredients(){
        return new Order(List.of());
    }
}
