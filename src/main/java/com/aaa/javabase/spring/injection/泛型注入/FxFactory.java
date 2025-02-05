package com.aaa.javabase.spring.injection.泛型注入;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 FxFactory.java  2025/2/4 22:28
 */
@Component
public class FxFactory {
    /**
     * 代码定位到这里，拉到最后面，可以观察是如何根据泛型注入的
     * org.springframework.core.ResolvableType#isAssignableFrom(org.springframework.core.ResolvableType, java.util.Map)
     *
     * 		if (checkGenerics) {
     * 			// Recursively check each generic
     * 			ResolvableType[] ourGenerics = getGenerics();
     * 			ResolvableType[] typeGenerics = other.as(ourResolved).getGenerics();
     * 			if (ourGenerics.length != typeGenerics.length) {
     * 				return false;
     *                        }
     * 			if (matchedBefore == null) {
     * 				matchedBefore = new IdentityHashMap<>(1);
     *            }
     * 			matchedBefore.put(this.type, other.type);
     * 			for (int i = 0; i < ourGenerics.length; i++) {
     * 				if (!ourGenerics[i].isAssignableFrom(typeGenerics[i], matchedBefore)) {
     * 					return false;
     *                }
     *            }* 		}
     */
    @Autowired
    private FxService<Number> fxService1;

    @Autowired
    private FxService<String> fxService2;

    // public FxFactory(FxService<Number> fxService) {
    //     this.fxService1 = fxService;
    // }

}
