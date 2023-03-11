package by.x1ss.smev;

import by.x1ss.smev.DTO.ResponseList;
import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseQueue;
import by.x1ss.smev.repository.RequestRepository;
import by.x1ss.smev.repository.ResponseRepository;
import by.x1ss.smev.service.RequestServiceImpl;
import by.x1ss.smev.service.ResponseServiceImpl;
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
    private RequestServiceImpl requestService;
    @Autowired
    private ResponseServiceImpl responseService;
    @Autowired
    private Worker worker;

    @Test
    void testResponseRepository() {
        ResponseQueue responseQueue = ResponseQueue.builder()
                .uuid(UUID.randomUUID())
                .clientIdentifier("1234567890")
                .isJuridical(false)
                .accrualAmount(123)
                .administrativeCode("123")
                .amountPay(123)
                .resolutionNumber(123)
                .build();
        responseQueue.setResolutionDate(LocalDate.now());
        responseRepository.save(responseQueue);
        assertEquals(responseQueue, responseRepository.findByUuid(responseQueue.getUuid()).get(0));
        responseRepository.deleteByUuid(responseQueue.getUuid());
        assertThrows(IndexOutOfBoundsException.class, () -> responseRepository.findByUuid(responseQueue.getUuid()).get(0));
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
        ResponseList responseQueue = responseService.getResponse(requestQueue.getUuid());
        assertNotNull(responseQueue);
        assertEquals(requestQueue.getUuid(), responseQueue.getResponses().get(0).getUuid());
        responseService.confirmResponse(requestQueue.getUuid());
        assertThrows(IndexOutOfBoundsException.class, () -> responseRepository.findByUuid(requestQueue.getUuid()).get(0));
    }

    @Test
    void contextLoads() {
    }

}
