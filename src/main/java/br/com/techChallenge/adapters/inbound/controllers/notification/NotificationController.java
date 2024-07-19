package br.com.techChallenge.adapters.inbound.controllers.notification;

import br.com.techChallenge.core.services.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<Void> receiveNotification(
            @RequestParam("id") Long id,
            @RequestParam("topic") String topic,
            @RequestBody String payload) {
        log.debug("Received notification - ID: {}, Topic: {}", id, topic);
        log.debug("Payload: {}", payload);

        if (topic.equals("merchant_order"))
            notificationService.updatePaymentStatus(id);

        return ResponseEntity.ok().build();
    }

}
