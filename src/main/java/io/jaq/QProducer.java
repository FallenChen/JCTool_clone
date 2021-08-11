package io.jaq;

/**
 * @ClassName QProducer
 * @Description TODO
 * @Author cy
 * @Date 2021/8/11 11:33
 */
public interface QProducer<E> {

    boolean offer(E e);
}
