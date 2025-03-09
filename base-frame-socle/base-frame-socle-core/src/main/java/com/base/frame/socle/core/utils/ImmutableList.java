package com.base.frame.socle.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Alassani
 */
public class ImmutableList {
    public  static List<String> of(String... s){
      return   Arrays.stream(s).collect(Collectors.toList());
    }
}
