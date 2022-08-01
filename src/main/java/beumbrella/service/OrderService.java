package beumbrella.service;

import beumbrella.model.Order;

public interface OrderService extends IGeneralService<Order> {
    Iterable<Order> findByUserId(Long id);
}
