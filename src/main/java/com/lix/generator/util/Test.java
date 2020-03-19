package com.lix.generator.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lixiang on 2019/8/7 0007.
 */
public class Test {

    public static void main(String[] args) throws Exception {

//        String collect = Stream.of("ffffffff"/*, "aaaaaaaaaaaa", "ssssssssssssss", "vvvvvvvvvvvvv"*/).collect(Collectors.joining(";"));
//        System.out.println(collect);
//        Pattern regex = Pattern.compile("\\<TrnCode\\>([0-9a-zA-Z\\s]+)?\\<\\/TrnCode\\>");
//        Matcher matcher = regex.matcher("<TrnCode>  4f5a 5f4d5a</TrnCode><test>${fds54g}</test>");
//        if(matcher.find()){
//            String group = matcher.group(1);
//            System.out.println(group.trim());
//        }
//        int i = matcher.groupCount();
//        StringBuffer buffer = new StringBuffer(512);
//        while (matcher.find()){
//            matcher.appendReplacement(buffer, "厉害");
//        }
//        matcher.appendTail(buffer);
//        System.out.println(buffer);
        int[] a = new int[]{18,73,59,89,84,84,48,84,54,62,67,27,60,0,61,94,84,55,55,60,76,35,84,28,4,9,86,12,89,41,21,65,33};
        int[] b = new int[]{54,21,73,84,60,18,62,59,89,89,41,55,27,65,94,61,12,76,35,48,0,60,84,9,28,55,4,67,86,33};
        Arrays.sort(a);
        Arrays.sort(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        /*int[] prices = new int[]{1,2,3,4,5};
        // 如果数据不符合业务条件，直接返回
        if(prices == null || prices.length < 2){
            return;
        }
        int result = 0;// 结果
        int max = 0;// 最佳时机值
        int min = prices[0];// 默认数组第一个为最小值
        // 从数组第二个开始处理
        for (int i = 1; i < prices.length; i++) {
            // 如果默认的最小值小于下个值，那么就先将其最大值
            if (min < prices[i]) {
                max = prices[i];
            } else {
                // 如果只有最小值，那么直接返回
                min = prices[i];
                continue;
            }
            result += (max - min);
            // 这里很重要，需要将最小值赋值为当前处理的值（或者说当前的max，只有上面判断为max才会走到这里）
            min = prices[i];
        }
        System.out.println(result);*/
    }


    /**
     * @param text 需要被替换的文本
     * @param value 替换的值
     * @return
     */
    public static String replaceValue(String text, String value){
        // 匹配${xxx}
        Pattern regex = Pattern.compile("\\$\\{([0-9a-zA-Z]+?)\\}(zzz)?");
        Matcher matcher = regex.matcher(text);
        int i = matcher.groupCount();
        StringBuffer buffer = new StringBuffer(512);
        while (matcher.find()){
            matcher.appendReplacement(buffer, value);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

}
