package org.lotus.carp.profile.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 5:23 PM
 */
public interface TreeNode <T extends  TreeNode ,K>{
    K getKey();
    K getParentKey();
    List<T> getChildren();

    default T build(List<T> items, K rootKey){
        T root = root();
        Map<K, T> map = new HashMap<>();
        map.put(rootKey, root);
        items.forEach(item -> map.put((K) item.getKey(), item));
        items.forEach(item -> map.get((K)item.getParentKey()).getChildren().add(item));
        return root;
    }

    T root();
}
