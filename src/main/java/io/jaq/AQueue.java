package io.jaq;

/**
 * @ClassName AQueue
 * @Description TODO
 * @Author cy
 * @Date 2021/8/11 11:35
 */
public interface AQueue<E> {

    QConsumer<E> consumer(int index);

    QProducer<E> producer(int index);
}
