package org.example;

import cn.hutool.json.JSONUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: TODO
 * @date : 2023-02-11
 */
public class StreamTest {

    public static void main(String[] args){
        //使用一个容器装载100个数字，通过Stream并行处理的方式将容器中为单数的数字转移到容器parallelList
        List<Integer> integerList= new ArrayList<Integer>();

        for (int i = 0; i <100; i++) {
            integerList.add(i);
        }

        List<Integer> parallelList = new ArrayList<Integer>() ;
        parallelList = integerList.stream()
                .parallel()
                .filter(i->i%2==1).collect(Collectors.toList());
//                .forEach(i->parallelList.add(i));
        System.out.println(JSONUtil.toJsonStr(parallelList));
    }

}
