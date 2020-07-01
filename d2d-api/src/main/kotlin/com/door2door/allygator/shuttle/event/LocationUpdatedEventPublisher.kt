package com.door2door.allygator.shuttle.event

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils
import reactor.core.publisher.FluxSink
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.function.Consumer

@Component
class LocationUpdatedEventPublisher(private val executor: Executor) : ApplicationListener<LocationUpdatedEvent>, Consumer<FluxSink<LocationUpdatedEvent>> {

    private val queue: BlockingQueue<LocationUpdatedEvent> = LinkedBlockingQueue()

    override fun onApplicationEvent(event: LocationUpdatedEvent) {
        this.queue.offer(event)
    }

    override fun accept(sink: FluxSink<LocationUpdatedEvent>) {
        this.executor.execute(Runnable {
            while (true)
                try {
                    sink.next(queue.take())
                } catch (e: InterruptedException) {
                    ReflectionUtils.rethrowRuntimeException(e)
                }
        })
    }
}