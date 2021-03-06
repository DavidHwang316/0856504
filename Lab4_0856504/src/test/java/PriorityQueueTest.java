import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PriorityQueueTest {

    @BeforeAll
    static void initAll(){
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource("myNormalTestcase")
    @Order(1)
    void NormalTest(int [] random_array, int [] correct_array){
        PriorityQueue testQueue = new PriorityQueue();
        for(int i=0; i<5; i++){
            testQueue.add(random_array[i]);
        }
        for(int i=0; i<5; i++) {
            assertEquals(correct_array[i], testQueue.poll());
        }
    }

    private static Stream<Arguments> myNormalTestcase() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 4, 2, 5}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{-3, -1, -4, 2, -5}, new int[]{-5, -4, -3, -1, 2}),
                Arguments.of(new int[]{130, 256, 377, 498, 101}, new int[]{101, 130, 256, 377, 498}),
                Arguments.of(new int[]{0, 1, 4, 2, 7}, new int[]{0, 1, 2, 4, 7}),
                Arguments.of(new int[]{31, 24, 22, 21, 22}, new int[]{21, 22, 22, 24, 31})
        );
    }

    @Test
    @Order(2)
    void ExceptionTest(){
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue testQueue = new PriorityQueue(0);
        });
        System.out.print("Exception 1: "+exception1+"\n");
        PriorityQueue testQueue = new PriorityQueue();
        Exception exception2 = assertThrows(NullPointerException.class, () -> {
            testQueue.add(null);
        });
        System.out.print("Exception 2: "+exception2+"\n");
        testQueue.add(0);
        testQueue.add(2);
        Exception exception3 = assertThrows(ClassCastException.class, () -> {
            testQueue.add("test");
        });
        System.out.print("Exception 3: "+exception3+"\n");
    }

}
