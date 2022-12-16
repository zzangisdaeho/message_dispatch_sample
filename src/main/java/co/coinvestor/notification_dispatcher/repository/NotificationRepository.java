package co.coinvestor.notification_dispatcher.repository;

import co.coinvestor.notification_dispatcher.document.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
