package io.jaq;

/**
 * @ClassName QConsumer
 * @Description TODO
 * @Author cy
 * @Date 2021/8/11 11:34
 */
public interface QConsumer<E> {

    E poll();
}
