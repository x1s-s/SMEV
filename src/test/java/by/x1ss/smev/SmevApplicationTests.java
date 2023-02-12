package by.x1ss.smev;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseQueue;
import by.x1ss.smev.repository.RequestRepository;
import by.x1ss.smev.repository.ResponseRepository;
import by.x1ss.smev.service.RequestService;
import by.x1ss.smev.service.ResponseService;
import by.x1ss.smev.worker.Worker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SmevApplicationTests {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestService requestService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private Worker worker;

    @Test
    void testResponseRepository() {
        ResponseQueue responseQueue = new ResponseQueue(
                RequestQueue.builder()
                        .uuid(UUID.randomUUID())
                        .clientIdentifier("1234567890")
                        .isJuridical(false)
                        .build());
        responseQueue.setResolutionDate(LocalDate.now());
        responseRepository.save(responseQueue);
        assertEquals(responseQueue, responseRepository.findByUuid(responseQueue.getUuid()));
        responseRepository.deleteByUuid(responseQueue.getUuid());
        assertNull(responseRepository.findByUuid(responseQueue.getUuid()));
    }

    @Test
    void testRequestRepository(){
        RequestQueue requestQueue = RequestQueue.builder()
                .uuid(UUID.randomUUID())
                .clientIdentifier("1234567890")
                .isJuridical(false)
                .build();
        requestRepository.save(requestQueue);
        List<RequestQueue> requestQueueList = requestRepository.findAll();
        assertTrue(requestQueueList.stream().anyMatch(temp -> requestQueue.getUuid().equals(temp.getUuid())));
        requestRepository.deleteByUUID(requestQueue.getUuid());
    }

    @Test
    void testRequestService(){
        RequestQueue requestQueue = RequestQueue.builder()
                .uuid(UUID.randomUUID())
                .clientIdentifier("1234567890")
                .isJuridical(false)
                .build();
        requestService.putRequest(requestQueue);
        assertEquals(requestQueue, requestRepository.findByUuid(requestQueue.getUuid()));
        requestRepository.deleteByUUID(requestQueue.getUuid());
    }

    @Test
    void testResponseServiceAndWorker(){
        RequestQueue requestQueue = RequestQueue.builder()
                .uuid(UUID.randomUUID())
                .clientIdentifier("1234567890")
                .isJuridical(false)
                .build();
        requestService.putRequest(requestQueue);
        worker.processRequests();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ResponseQueue responseQueue = responseService.getResponse(requestQueue.getUuid());
        assertNotNull(responseQueue);
        assertEquals(requestQueue.getUuid(), responseQueue.getUuid());
        responseService.confirmResponse(responseQueue.getUuid());
        assertNull(responseRepository.findByUuid(responseQueue.getUuid()));
    }

    @Test
    void contextLoads() {
    }

}
