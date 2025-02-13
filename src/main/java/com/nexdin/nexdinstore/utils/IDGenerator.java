package com.nexdin.nexdinstore.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class IDGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final int PROCESS_ID = getProcessID();

    public static synchronized String generateID() {
        long timestamp = System.nanoTime();
        int count = counter.getAndIncrement() & 0xFFF;

        log.info("Generating ID | timestamp: {}, processId: {}, counter: {}", timestamp, PROCESS_ID, count);

        ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.putLong(timestamp);
        buffer.putShort((short) PROCESS_ID);
        buffer.putShort((short) count);

        String id = encodeBase62(buffer.array());

        log.info("Generated ID: {}", id);
        return id;
    }

    private static int getProcessID() {
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        try {
            int pid = Integer.parseInt(processName.split("@")[0]);
            log.info("Retrieved Process ID: {}", pid);
            return pid;
        } catch (Exception e) {
            log.warn("Failed to get process ID, using fallback value.");
            return (int) (System.currentTimeMillis() % 65536);
        }
    }

    private static String encodeBase62(byte[] input) {
        String base62Chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length; i += 2) { // Mỗi lần xử lý 4 byte
            long num = 0;
            int end = Math.min(i + 4, input.length);

            for (int j = i; j < end; j++) {
                num = (num << 8) | (input[j] & 0xFF);
            }

            while (num > 0) {
                sb.append(base62Chars.charAt((int) (num % 62)));
                num /= 62;
            }
        }

        String result = sb.reverse().toString();
        log.debug("Base62 Encoded ID: {}", result);
        return result;
    }

}
