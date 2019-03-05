public class TestSort {

    public static void testFindSmallest() {
        String[] input1 = new String[]{"There", "are", "many", "pigs"};
        String expected1 = "are";

        String actual1 = Sort.findMinValue(input1);
        org.junit.Assert.assertEquals(expected1, actual1);
    }

}