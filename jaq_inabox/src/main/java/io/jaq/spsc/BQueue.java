package io.jaq.spsc;

import io.jaq.util.Pow2;
import io.jaq.util.UnsafeAccess;

/**
 * @author cy
 * @className BQueue
 * @description TODO
 * @date 2021/8/11 13:23
 */

abstract class BQueueL0Pad
{
    protected long p00, p01, p02, p03, p04, p05, p06, p07;
    protected long p10, p11, p12, p13, p14, p15, p16, p17;
}

abstract class BQueueColdFields<E> extends BQueueL0Pad
{
    protected static final int BUFFER_PAD = 32;
    protected static final long ARRAY_BASE;
    protected static final int ELEMENT_SHIFT;
    protected static final int TICKS = Integer.getInteger("spin.ticks", 200);
    static
    {
        final int scale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);

        if(4 == scale)
        {
            ELEMENT_SHIFT = 2;
        }
        else if(8 == scale)
        {
            ELEMENT_SHIFT = 3;
        }else
        {
            throw new IllegalStateException("Unknown pointer size");
        }
        ARRAY_BASE = UnsafeAccess.UNSAFE.arrayBaseOffset(Object[].class)
                + (BUFFER_PAD << ELEMENT_SHIFT);
    }

    protected static final int OFFER_BATCH_SIZE = Integer.getInteger("offer.batch.size", 512);
    protected static final int POLL_MAX_BATCH = Integer.getInteger("poll.batch.size",32);
    protected final int capacity;
    protected final long mask;
    protected final E[] buffer;

    protected BQueueColdFields(int capacity)
    {
        if(Pow2.isPowerOf2(capacity))
        {
            this.capacity = capacity;
        }
        else
        {
            this.capacity = Pow2.findNextPositivePowerOfTwo(capacity);
        }
        mask = this.capacity - 1;
        buffer = (E[])new Object[this.capacity + BUFFER_PAD * 2];
    }
}

abstract class BQueueL1Pad<E> extends BQueueColdFields<E>
{
    protected long p00, p01, p02, p03, p04, p05, p06, p07;
    protected long p10, p11, p12, p13, p14, p15, p16, p17;

    protected BQueueL1Pad(int capacity)
    {
        super(capacity);
    }
}

abstract class BQueueOfferFields<E> extends BQueueL1Pad<E>
{
    protected long tail;
    protected long batchTail;

    protected BQueueOfferFields(int capacity)
    {
        super(capacity);
    }
}

abstract class BQueueL2Pad<E> extends BQueueOfferFields<E>
{
    protected long p00, p01, p02, p03, p04, p05, p06, p07;
    protected long p30, p31, p32, p33, p34, p35, p36, p37;

    public BQueueL2Pad(int capacity)
    {
        super(capacity);
    }
}

abstract class BQueuePollFields<E> extends BQueueL2Pad<E>
{
    protected long head;
    protected long batchHead;
    protected int batchHistory = POLL_MAX_BATCH;
    protected int batchSize;
    public BQueuePollFields(int capacity)
    {
        super(capacity);
    }
}


public class BQueue {

}

