package beumbrella.service;

import beumbrella.model.Order;

public interface OrderService extends GeneralService<Order> {
    Iterable<Order> findByUserId(Long id);
}
