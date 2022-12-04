package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
    /*
    Кроме основных операций CRUD (Create, Read, Update, Delete),
    предоставляемых интерфейсом CrudRepository, можно добавить
    другие операции

    В данном случае, мы хотим иметь возможность получить список всех
    заказов, доставленных в заданный район
     */
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    /*
    Более сложная операция:
    Предположим надо запросить все заказы, доставленные в
    определенный район в определенном диапазоне дат
     */
    List<TacoOrder> readOrderByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate
    );
}
