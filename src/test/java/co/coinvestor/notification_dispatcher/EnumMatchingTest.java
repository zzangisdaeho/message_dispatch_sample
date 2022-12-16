package co.coinvestor.notification_dispatcher;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EnumMatchingTest {

    public enum TestEnum1{
        STRATEGY_PURCHASE, STRATEGY_LIKE, NEW_POST, POST_REPLY, POST_LIKE,
        MENTION, FOLLOW;
    }

    public enum TestEnum2{
        STRATEGY_PURCHASE, STRATEGY_LIKE, NEW_POST, POST_REPLY, POST_LIKE,
        MENTION, FOLLOW;

        public static TestEnum2 match(String name){
            return Arrays.stream(TestEnum2.values()).filter(testEnum2 -> testEnum2.name().equals(name)).findAny().get();
        }
    }

    @Test
    public void testMatching(){
        TestEnum1 follow1 = TestEnum1.FOLLOW;

        TestEnum2 follow2 = TestEnum2.FOLLOW;

        TestEnum2 follow = TestEnum2.match(follow1.name());

        System.out.println("follow = " + follow);

        System.out.println(follow2 == follow);
        System.out.println(follow2.equals(follow));

    }
}
